package com.github.dge1992.fish.async.impl;

import com.github.dge1992.fish.async.SyncService;
import org.springframework.stereotype.Service;

/**
 * @author dge
 * @version 1.0
 * @date 2021-01-25 10:52
 */
@Service
public class SyncServiceImpl implements SyncService {

    @Override
    public void testSync() {
        long start = System.currentTimeMillis();

        methodOne();
        methodOne();
        methodOne();

        long end = System.currentTimeMillis();
        System.out.println("cost:" + (end - start));
    }

    void methodOne(){
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
