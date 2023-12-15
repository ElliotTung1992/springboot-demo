package com.elliot.scheduling.quartz.scheduler;

import com.elliot.scheduling.quartz.job.SimpleJob;
import com.elliot.scheduling.quartz.job.TestJob;
import com.elliot.scheduling.quartz.listener.TestJobListener;
import com.elliot.scheduling.quartz.listener.TestSchedulerListener;
import com.elliot.scheduling.quartz.listener.TestTriggerListener;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.EverythingMatcher;
import org.quartz.impl.matchers.KeyMatcher;

/**
 * 测试调度器 - SimpleTrigger
 */
public class TestScheduler {

    public static void main(String[] args) throws SchedulerException {
        // 获取调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // 创建任务实例
        JobDetail jobDetail = JobBuilder.newJob(SimpleJob.class)
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
                        withRepeatCount(0))   // 循环10次,每次间隔3s
                .usingJobData("message", "调度上下文传递Trigger的数据") // 调度上下文传递数据
                .build();

        // 调度器关联任务及触发器
        scheduler.scheduleJob(jobDetail, trigger);

        // 设置任务监听器
        // scheduler.getListenerManager().addJobListener(new TestJobListener(), EverythingMatcher.allJobs());
        // 设置触发器监听器
        /*scheduler.getListenerManager().addTriggerListener(new TestTriggerListener(),
                KeyMatcher.keyEquals(TriggerKey.triggerKey("testJob", "testGroup")));*/
        // 设置执行期监听器
        scheduler.getListenerManager().addSchedulerListener(new TestSchedulerListener());

        scheduler.start();
    }
}
