package com.github.dge1992.fish.async.impl;

import com.github.dge1992.fish.async.CompletableFutureService;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author dge
 * @version 1.0
 * @date 2021-01-25 14:14
 */
@Service
public class CompletableFutureServiceImpl implements CompletableFutureService {

    @Override
    public void testCompletableFuture() {

        long start = System.currentTimeMillis();

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        });
        CompletableFuture<String> completableFutureTwo = completableFuture.thenApplyAsync(e -> {
            try {
                TimeUnit.SECONDS.sleep(3L);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            return e + " world";
        });

        long end = System.currentTimeMillis();

        System.out.println(end - start);

        try {
            String s = completableFutureTwo.get();
            System.out.println(s);

            end = System.currentTimeMillis();
            System.out.println(end - start);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}