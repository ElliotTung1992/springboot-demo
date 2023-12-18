package com.elliot.scheduling.spring;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SpringScheduleSample {

    @Scheduled(cron = "0/3 * * * * ?")
    public void sampleOne(){
        try {
            Thread.sleep(1000000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(LocalDateTime.now() + "Hello Spring schedule");
    }
}
