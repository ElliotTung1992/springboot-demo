package com.elliot.scheduling.quartz.scheduler;

import com.elliot.scheduling.quartz.job.TestJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 测试调度器
 */
public class TestScheduler {

    public static void main(String[] args) throws SchedulerException {
        // 获取调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // 创建任务实例
        JobDetail jobDetail = JobBuilder.newJob(TestJob.class)
                .withIdentity("testJob", "testGroup") // 设置name和group
                .usingJobData("message", "调度上下文传递数据") // 调度上下文传递数据
                .usingJobData("count", 0)
                .build();

        // 创建触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("testJob", "testGroup")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().
                        withIntervalInSeconds(3).
                        withRepeatCount(10))   // 循环10次,每次间隔3s
                .usingJobData("message", "调度上下文传递Trigger的数据") // 调度上下文传递数据
                .build();

        // 调度器关联任务及触发器
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }
}
