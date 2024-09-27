package com.github.dge1992.fish.ratelimiter.bucket;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RateLimiterLeakyBucket {

    private static Long startTime;

    static Queue<Runnable> queue = new ArrayBlockingQueue<>(10);

    static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public RateLimiterLeakyBucket(Long startTime){
        this.startTime = startTime;
    }


    public synchronized void put(Runnable runnable){
        if(queue.size() >= 10){
            System.out.println("满啦");
            return;
        }
        queue.add(runnable);
    }

    public void get(){
        if(!queue.isEmpty()){
            Runnable poll = queue.poll();
            executorService.submit(poll);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        RateLimiterLeakyBucket rateLimiterLeakyBucket = new RateLimiterLeakyBucket(System.currentTimeMillis());

        new Thread(() -> {
            while (true){
                rateLimiterLeakyBucket.put(() -> {
                    System.out.println(System.currentTimeMillis() + "haha");
                });
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        for (int i = 0; i < 100; i++) {
            TimeUnit.MILLISECONDS.sleep(500);
            rateLimiterLeakyBucket.get();
        }
    }



}
