package com.github.dge1992.fish.async.impl;

import com.github.dge1992.fish.FishApplicationTests;
import com.github.dge1992.fish.async.SyncService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author dge
 * @version 1.0
 * @date 2021-01-25 10:57
 */
class SyncServiceImplTest extends FishApplicationTests {

    @Autowired
    private SyncService syncService;

    @Test
    void testSync(){
        syncService.testSync();
    }
}