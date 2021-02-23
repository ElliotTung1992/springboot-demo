package com.github.dge1992.fish.async.impl;

import com.github.dge1992.fish.FishApplicationTests;
import com.github.dge1992.fish.async.CompletableFutureService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author dge
 * @version 1.0
 * @date 2021-01-25 14:21
 */
class CompletableFutureServiceImplTest extends FishApplicationTests {

    @Autowired
    private CompletableFutureService completableFutureService;

    @Test
    void testCompletableFuture() throws InterruptedException {
        completableFutureService.testCompletableFuture();
        TimeUnit.SECONDS.sleep(5);
    }
}