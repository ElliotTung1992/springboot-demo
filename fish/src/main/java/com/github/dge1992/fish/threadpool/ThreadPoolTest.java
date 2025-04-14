package com.github.dge1992.fish.threadpool;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.*;

public class ThreadPoolTest {

    public static void main(String[] args) throws Exception {
        ThreadPoolTest threadPool = new ThreadPoolTest();
        // threadPool.demoOne();
    }

    private void demoTwo() {

    }

    private void demoOne() throws ExecutionException, InterruptedException {
        Callable<Integer> callable = () -> 1 + 1;
        Runnable runnable = () -> {int i = 1 + 1;};

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<Integer> future = executorService.submit(callable);
        System.out.println(future.get());

        Future<?> future2 = executorService.submit(runnable);

        Runnable runnable2 = () -> {
            System.out.println("000");
            int i = 10 / 0;
            File file = new File("aaa");
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("111");
        };

        executorService.submit(runnable2);

        Callable<Integer> callable2 = () -> {
            int i = 10 / 0;
            return 0;
        };

        Future<Integer> future3 = executorService.submit(callable2);
        Integer integer = future3.get();
    }
}
