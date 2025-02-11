package com.github.dge1992.fish.ratelimiter;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 滑动窗口 - 队列实现
 */
public class RateLimiterSlidingWindowQueue implements IRateLimiter {

    Queue<Long> queue = new ArrayBlockingQueue(2);

    public RateLimiterSlidingWindowQueue(Long startTime){

    }

    @Override
    public synchronized boolean acquire() {
        long currentTimeMillis = System.currentTimeMillis();
        while (!queue.isEmpty() && currentTimeMillis - queue.peek() > 1000){
            queue.poll();
        }
        if(queue.size() >= 2){
            return false;
        }
        queue.add(currentTimeMillis);
        return true;
    }
}
