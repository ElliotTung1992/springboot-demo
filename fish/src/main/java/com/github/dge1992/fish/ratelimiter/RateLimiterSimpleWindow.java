package com.github.dge1992.fish.ratelimiter;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 固定窗口时间
 */
public class RateLimiterSimpleWindow {

    private static Integer QPS = 2;

    private static Long TIME_WINDOWS = 1000L;

    private static AtomicInteger atomicInteger = new AtomicInteger();

    private static Long START_TIME = System.currentTimeMillis();

    public synchronized static boolean tryAcquire(){
        if(System.currentTimeMillis() - START_TIME > TIME_WINDOWS){
            atomicInteger.set(0);
            START_TIME = System.currentTimeMillis();
        }
        return atomicInteger.incrementAndGet() <= QPS;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            TimeUnit.MILLISECONDS.sleep(250);
            long now = System.currentTimeMillis();
            if(tryAcquire()){
                System.out.println(now + "正常运行");
            }else {
                System.out.println(now + "限流了");
            }
        }
    }
}
