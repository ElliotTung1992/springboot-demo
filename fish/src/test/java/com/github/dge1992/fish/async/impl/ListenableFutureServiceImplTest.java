package com.github.dge1992.fish.async.impl;

import com.github.dge1992.fish.FishApplicationTests;
import com.github.dge1992.fish.async.ListenableFutureService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author dge
 * @version 1.0
 * @date 2021-01-25 14:01
 */
class ListenableFutureServiceImplTest extends FishApplicationTests {

    @Autowired
    private ListenableFutureService listenableFutureService;

    @Test
    void testListenableFuture() throws InterruptedException {
        listenableFutureService.testListenableFuture();
        TimeUnit.SECONDS.sleep(4);
    }
}