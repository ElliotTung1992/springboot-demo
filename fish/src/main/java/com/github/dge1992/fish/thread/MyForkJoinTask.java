package com.github.dge1992.fish.thread;

import java.util.concurrent.RecursiveTask;

/**
 * @author dge
 * @version 1.0
 * @date 2021-04-02 17:31
 */
public class MyForkJoinTask extends RecursiveTask<Long> {

    private Long start;
    private Long end;
    private Long median;

    public MyForkJoinTask(Long start, Long end) {
        this.start = start;
        this.end = end;
        this.median = start / Runtime.getRuntime().availableProcessors();
    }

    @Override
    protected synchronized Long compute() {
        if((end - start) <= median){
            long sum = 0L;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }else{
            Long m = (end + start)/2;
            MyForkJoinTask task1 = new MyForkJoinTask(start, m);
            MyForkJoinTask task2 = new MyForkJoinTask(m + 1, end);
            /*task1.fork();
            task2.fork();*/
            invokeAll(task1, task2);
            return task1.join() + task2.join();
        }
    }
}
