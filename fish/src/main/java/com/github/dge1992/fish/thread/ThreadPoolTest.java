package com.github.dge1992.fish.thread;

import java.util.concurrent.*;

/**
 * @author dge
 * @version 1.0
 * @date 2021-03-31 21:15
 */
public class ThreadPoolTest {

    public static void main(String[] args) {

        // ExecutorService executorService = Executors.newSingleThreadExecutor(); 只有一个线程的线程池
        // ExecutorService executorService = Executors.newCachedThreadPool(); 容量是Integer.MAX_VALUE的线程池，容易造成OOM
        // ExecutorService executorService = Executors.newFixedThreadPool(5); // 固定大小的线程池

        System.out.println(Runtime.getRuntime().availableProcessors());

        ExecutorService executorService = new ThreadPoolExecutor(2, 5, 5, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        try {
            for (int i = 1; i <= 20; i++) {
                int finalI = i;
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "执行了" + finalI);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }

        Thread thread = new Thread(() -> {
            System.out.println("xi");
        });

    }
}
