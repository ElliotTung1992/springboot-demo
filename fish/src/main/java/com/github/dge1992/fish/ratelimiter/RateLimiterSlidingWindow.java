package com.github.dge1992.fish.ratelimiter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class RateLimiterSlidingWindow implements IRateLimiter {

    private Integer QPS = 2;

    private List<RateLimiter> list = new ArrayList<>(10);

    private Long start;

    public RateLimiterSlidingWindow(Long start){
        this.start = start;
        for (int i = 0; i <10; i++) {
            RateLimiter rateLimiter = new RateLimiter();
            rateLimiter.setTime(start);
            rateLimiter.setCount(0);
            list.add(rateLimiter);
        }
    }

    @Override
    public synchronized boolean acquire() {
        long current = System.currentTimeMillis();
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            RateLimiter rateLimiter = list.get(i);
            Long time = rateLimiter.getTime();
            if(current - time > 1000){
                rateLimiter.setCount(0);
                rateLimiter.setTime(current);
            }else{
                sum += rateLimiter.getCount();
            }
        }
        if(sum >= QPS){
            return false;
        }
        long millisecond = current % 1000;
        int index = (int) ((10 * millisecond)/1000);
        RateLimiter rateLimiter = list.get(index);
        rateLimiter.setTime(current);
        rateLimiter.setCount(rateLimiter.getCount() + 1);
        return true;
    }

    @Data
    class RateLimiter {
        private Long time;
        private Integer count;
    }
}
