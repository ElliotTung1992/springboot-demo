package com.github.dge1992.fish.ratelimiter;

/**
 * 滚动窗口
 */
public class RateLimiterSimpleWindow implements IRateLimiter {

    private Integer QPS = 2;

    private Integer count = 0;

    private Long startTime;

    RateLimiterSimpleWindow(Long startTime){
        this.startTime = startTime;
    }

    @Override
    public synchronized boolean acquire(){
        long now = System.currentTimeMillis();
        if(now - startTime > 1000L){
            startTime = now;
            count = 1;
            return true;
        }
        if(count >= QPS){
            return false;
        }
        count++;
        return true;
    }
}
