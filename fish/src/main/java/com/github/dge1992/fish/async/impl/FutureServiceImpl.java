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



}
