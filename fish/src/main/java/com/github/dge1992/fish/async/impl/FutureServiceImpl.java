package com.github.dge1992.fish.async.impl;

import com.github.dge1992.fish.async.FutureService;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * @author dge
 * @version 1.0
 * @date 2021-01-25 11:05
 */
@Service
public class FutureServiceImpl implements FutureService {

    @Override
    public void testFuture() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 3, 0L,
                TimeUnit.MICROSECONDS, new LinkedBlockingDeque<>(1024),
                namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        long start = System.currentTimeMillis();

        Future<String> submit = threadPoolExecutor.submit(() -> {
            Thread.sleep(2000L);
            return "hello world";
        });

        Future<String> submit2 = threadPoolExecutor.submit(() -> {
            Thread.sleep(2000L);
            return "hello world";
        });

        try {
            String result = submit.get();
            System.out.println(result);
            String result2 = submit2.get();
            System.out.println(result2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println(end - start);

    }

    public static void main(String[] args) throws Exception {

        /**
         *
         * 为什么出现Future
         * 传统方式运行线程，创建线程并实现Runnable接口
         * 但是这种方式有个缺点就是没有返回值
         *
         * 之后在java 1.5推出了Future和Callable
         * 通过它们可以在任务执行结束后获得响应值
         * FutureTask实现了RunnableFuture接口
         * RunnableFuture接口继承了Runnable接口和Future接口
         *
         */

        // 传统方式
        new Thread(() -> System.out.println("传统方式启动线程")).start();

        // 有返回值的线程
        FutureTask<Integer> futureTask = new FutureTask<>(() -> 10);
        new Thread(futureTask).start();
        Integer integer = futureTask.get();
        System.out.println("返回值integer:" + integer);

        //FutureTask构造方法
        FutureTask<Integer> futureTask1 = new FutureTask<>(() -> 10);
        FutureTask<Integer> futureTask2 = new FutureTask<>(() -> System.out.println("hello"), 10);

        new Thread(futureTask1).start();
        new Thread(futureTask2).start();

        System.out.println(futureTask1.get());
        System.out.println(futureTask2.get());
    }

}
