package com.github.dge1992.fish.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest2 {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(5000),
                (r, executor) -> {
                    try {
                        if (!executor.getQueue().offer(r, 5, TimeUnit.SECONDS)) {
                            // 持久化处理
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });

        // 查看队列长度
        threadPoolExecutor.getQueue().size();
        // 查看线程活跃数
        threadPoolExecutor.getActiveCount();
    }
}
