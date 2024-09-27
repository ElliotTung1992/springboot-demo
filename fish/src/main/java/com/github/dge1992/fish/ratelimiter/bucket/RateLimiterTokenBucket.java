package com.github.dge1992.fish.ratelimiter.bucket;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RateLimiterTokenBucket {

    private static Long startTime;

    static Queue<Long> queue = new ArrayBlockingQueue<>(10);

    static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public RateLimiterTokenBucket(Long startTime){
        this.startTime = startTime;
    }


    public synchronized void put(){
        if(queue.size() >= 10){
            System.out.println("满啦");
            return;
        }
        queue.add(System.currentTimeMillis());
    }

    public synchronized boolean get(){
        if(!queue.isEmpty()){
            queue.poll();
            return true;
        }
        return false;
    }


    public static void main(String[] args) throws InterruptedException {
        RateLimiterTokenBucket rateLimiterTokenBucket = new RateLimiterTokenBucket(System.currentTimeMillis());


        new Thread(() -> {
            while (true){
                rateLimiterTokenBucket.put();
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();


        for (int i = 0; i < 100; i++) {
            boolean b = rateLimiterTokenBucket.get();
            if(b){
                System.out.println("拿到了");
            }else{
                System.out.println("没拿到");
            }
            TimeUnit.MILLISECONDS.sleep(250);
        }
    }
}
