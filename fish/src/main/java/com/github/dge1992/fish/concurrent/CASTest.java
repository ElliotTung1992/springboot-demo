package com.github.dge1992.fish.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CASTest {

    // 定义一个共享的计数器
    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {
        // 使用线程池来模拟多线程
        int poolSize = 100; // 线程池大小
        ExecutorService executor = Executors.newFixedThreadPool(poolSize);

        try {
            // 向线程池提交200个任务来模拟超发
            for (int i = 0; i < 10; i++) {
                executor.submit(() -> {
                    // 模拟每个线程执行任务时对count进行更新
                    for (int j = 0; j < 1000; j++) {  // 每个线程增加count 1000次
                        count.incrementAndGet();
                    }
                });
            }
        } finally {
            // 关闭线程池
            executor.shutdown();
        }

        // 等待所有线程完成并打印最终的count值
        try {
            if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                System.out.println("Timeout occurred before all tasks finished.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 输出最终的count值，通常这不是期望的值，因为没有同步控制
        System.out.println("Final count value: " + count);
    }
}
