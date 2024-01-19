package com.elliot.scheduling.springquartz.configue;

import com.elliot.scheduling.springquartz.job.FirstJob;
import org.quartz.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnProperty(prefix = "spring.quartz", name = "open", havingValue = "true")
@Configuration
public class QuartzConfigure {

    @Bean
    public JobDetail firstJobDetail(){
        return JobBuilder.newJob(FirstJob.class)
                .withIdentity("firstJobDetail")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger firstTrigger(JobDetail firstJobDetail){
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5)
                .repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(firstJobDetail)
                .withIdentity("firstTrigger")
                .withSchedule(simpleScheduleBuilder)
                .build();
    }
}
