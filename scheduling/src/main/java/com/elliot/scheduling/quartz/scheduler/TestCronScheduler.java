package com.elliot.scheduling.quartz.scheduler;

import com.elliot.scheduling.quartz.job.TestJob;
import com.elliot.scheduling.quartz.listener.TestJobListener;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.EverythingMatcher;

/**
 * 测试调度器 - SimpleTrigger
 */
public class TestCronScheduler {

    public static void main(String[] args) throws SchedulerException {
        // new scheduler
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        // new job
        JobDetail jobDetail = JobBuilder.newJob(TestJob.class)
                .withIdentity("testJob", "testGroup")
                .usingJobData("count", 0)
                .build();
        // new trigger
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("testTrigger", "testGroup")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ?"))
                .build();

        scheduler.scheduleJob(jobDetail, trigger);

        scheduler.start();
    }
}
