package com.github.dge1992.fish.async.impl;

import com.github.dge1992.fish.async.ListenableFutureService;
import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * @author dge
 * @version 1.0
 * @date 2021-01-25 13:45
 */
@Service
public class ListenableFutureServiceImpl implements ListenableFutureService {

    @Override
    public void testListenableFuture() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0L,
                TimeUnit.MICROSECONDS, new LinkedBlockingDeque<>(1024), namedThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(threadPoolExecutor);

        final ListenableFuture<Integer> listenableFuture = executorService.submit(() -> {
            System.out.println("call execute..");
            TimeUnit.SECONDS.sleep(1);
            return 7;
        });

        final ListenableFuture<Integer> listenableFuture2 = executorService.submit(() -> {
            System.out.println("call execute..");
            TimeUnit.SECONDS.sleep(1);
            return 8;
        });

        Futures.addCallback(listenableFuture, new FutureCallback<Integer>() {

            @Override
            public void onSuccess(@Nullable Integer integer) {
                System.out.println("listen integer:" + integer);

                Futures.addCallback(listenableFuture2, new FutureCallback<Integer>() {
                    @Override
                    public void onSuccess(@Nullable Integer integer2) {
                        System.out.println("listen integer:" + integer2);
                        System.out.println("sum is:" + (integer + integer2));
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }, threadPoolExecutor);
            }

            @Override
            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();
            }
        }, threadPoolExecutor);
    }
}
