package com.elliot.scheduling.quartz.scheduler;

import com.elliot.scheduling.quartz.job.TestJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * 调度器测试二
 */
public class CaseTwoScheduler {

    public static void main(String[] args) throws SchedulerException {

        // create Scheduler
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // create job
        JobDetail jobDetail = JobBuilder.newJob(TestJob.class)
                .withIdentity("testJob", "testGroup")
                .usingJobData("count", 0)
                .build();

        // create trigger
        Date startData = new Date();
        startData.setTime(startData.getTime() + 3000);
        Date endData = new Date();
        endData.setTime(endData.getTime() + 10000);

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("testJob", "testGroup")
                .startNow() // 立刻启动
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().
                        withIntervalInSeconds(3).
                        withRepeatCount(10))
                .startAt(startData)
                .endAt(endData)
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }
}
