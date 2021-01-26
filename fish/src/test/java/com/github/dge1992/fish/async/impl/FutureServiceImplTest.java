package com.github.dge1992.fish.async.impl;

import com.github.dge1992.fish.FishApplicationTests;
import com.github.dge1992.fish.async.FutureService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author dge
 * @version 1.0
 * @date 2021-01-25 11:26
 */
class FutureServiceImplTest extends FishApplicationTests {

    @Autowired
    private FutureService futureService;

    @Test
    void testFuture() {
        futureService.testFuture();
    }
}