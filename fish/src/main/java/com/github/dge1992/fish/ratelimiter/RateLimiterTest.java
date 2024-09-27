package com.github.dge1992.fish.ratelimiter;

import java.util.concurrent.TimeUnit;

public class RateLimiterTest {

    public static void main(String[] args) {
        RateLimiterTest rateLimiterTest = new RateLimiterTest();

        long startTime = System.currentTimeMillis();
        System.out.println(startTime);

        // IRateLimiter rateLimiter = new RateLimiterSimpleWindow(System.currentTimeMillis());
        // IRateLimiter rateLimiter = new RateLimiterSlidingWindow(startTime);
        IRateLimiter rateLimiter = new RateLimiterSlidingLog(startTime);
        // IRateLimiter rateLimiter = new RateLimiterSlidingWindowQueue(startTime);
        // rateLimiterTest.demo1(rateLimiter);
        // rateLimiterTest.demo2(rateLimiter);
        rateLimiterTest.demo3(rateLimiter);
    }

    public void demo1(IRateLimiter rateLimiter){
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                print(rateLimiter.acquire());
            }).start();
        }
    }

    public void demo2(IRateLimiter rateLimiter){
        try {
            TimeUnit.MILLISECONDS.sleep(700);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < 10; i++) {
            if(i == 5){
                try {
                    TimeUnit.MILLISECONDS.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            new Thread(() -> {
                print(rateLimiter.acquire());
            }).start();
        }
    }

    public void demo3(IRateLimiter rateLimiter){
        for (int i = 0; i < 10; i++) {
            int j = i % 4;
            if(j == 0){
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if(j == 1){
                try {
                    TimeUnit.MILLISECONDS.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if(j == 2){
                try {
                    TimeUnit.MILLISECONDS.sleep(400);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if(j == 3){
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            new Thread(() -> {
                print(rateLimiter.acquire());
            }).start();
        }
    }

    private void print(boolean acquire){
        String str = acquire ? "welcome friend" : "Sorry!";
        System.out.println(System.currentTimeMillis() + str);
    }
}
