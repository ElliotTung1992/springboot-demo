package com.github.dge1992.fish.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @author dge
 * @version 1.0
 * @date 2021-04-02 17:30
 */
public class ForkJoinPoolTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 常规方案
        long startOne = System.currentTimeMillis();
        long sum1 = 0L;
        for (long i = 0; i <= 10_0000_0000L; i++) {
            sum1 += i;
        }
        System.out.println(sum1 + "|" + (System.currentTimeMillis() - startOne));

        // ForkJoinPool
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        long startTwo = System.currentTimeMillis();
        MyForkJoinTask forkJoinTask = new MyForkJoinTask(0L, 10_0000_0000L);
        ForkJoinTask<Long> task = forkJoinPool.submit(forkJoinTask);
        System.out.println(task.get() + "|" + (System.currentTimeMillis() - startTwo));

        // 并行流
        long startThree = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0, 10_0000_0000L).parallel().reduce(0, Long::sum);
        System.out.println(sum + "|" + (System.currentTimeMillis() - startThree));


    }
}
