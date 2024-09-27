package com.github.dge1992.fish.ratelimiter;

import java.util.concurrent.Semaphore;

public class RateLimiterSemaphore implements IRateLimiter {

    Semaphore semaphore = new Semaphore(10);

    @Override
    public boolean acquire() {
        return semaphore.tryAcquire();
    }

    public static void main(String[] args) {
        RateLimiterSemaphore rateLimiterSemaphore = new RateLimiterSemaphore();
        for (int i = 0; i < 100; i++) {
            boolean acquire = rateLimiterSemaphore.acquire();
            if(acquire){
                System.out.println("拿到了");
            }else {
                System.out.println("没拿到");
            }

        }
    }
}
