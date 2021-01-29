package com.github.dge1992.fish.java.util.concurrent.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author dge
 * @version 1.0
 * @date 2021-01-27 16:49
 */
public class LockSupportTest {

    static class TestThread extends Thread{

        @Override
        public void run() {
            LockSupport.park();
            System.out.println("TestThread run");
            super.run();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestThread testThread = new TestThread();
        testThread.start();
        System.out.println("哈哈");
        TimeUnit.SECONDS.sleep(2L);
        System.out.println("呵呵");
        LockSupport.unpark(testThread);
    }
}
