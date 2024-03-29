package com.elliot.scheduling.springquartz.configue;

import com.elliot.scheduling.springquartz.job.SecondJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@ConditionalOnProperty(prefix = "spring.quartz", name = "open", havingValue = "true")
@Component
public class QuartzRunner implements ApplicationRunner {

    @Autowired
    private Scheduler scheduler;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        JobDetail firstJob = JobBuilder.newJob(SecondJob.class)
                .withIdentity("secondJobDetail")
                .storeDurably()
                .build();

        CronScheduleBuilder cronScheduleBuilder =
                CronScheduleBuilder.cronSchedule("0/6 * * * * ? *");

        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("secondCronTrigger")
                .withSchedule(cronScheduleBuilder)
                .startNow()
                .build();

        scheduler.scheduleJob(firstJob, trigger);
    }
}
