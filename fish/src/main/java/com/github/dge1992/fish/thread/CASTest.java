package com.github.dge1992.fish.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dge
 * @version 1.0
 * @date 2021-04-13 22:35
 */
public class CASTest {

    AtomicInteger atomicInteger = new AtomicInteger();

    private volatile Integer i = 0;

    private static final Integer ROUND = 10000;

    private CountDownLatch countDownLatch = new CountDownLatch(ROUND);

    public static void main(String[] args) {
        CASTest test = new CASTest();

        //test.testOne();
        //test.two();
        test.three();
    }

    /**
     * 模拟多线程原子性问题
     * @author dge
     * @date 2021-04-20 22:46
     */
    private void three() {
        for (int j = 0; j < ROUND; j++) {
            new Thread(() -> {
                for (int k = 1; k <= 100; k++) {
                    add();
                }
                countDownLatch.countDown();
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(atomicInteger.get());
        System.out.println(i);

    }

    public void add(){
        atomicInteger.incrementAndGet();
        i++;
    }

    /**
     * 线程工具方法使用
     * @author dge
     * @date 2021-04-20 22:45
     */
    private void two() {
        Thread.currentThread().getThreadGroup().list();
        System.out.println(Thread.activeCount());
    }

    /**
     * 创建api使用
     * @author dge
     * @date 2021-04-20 22:45
     */
    private void testOne() {
        //System.out.println(atomicInteger.get());

        atomicInteger.compareAndSet(0, 2020);
        System.out.println(atomicInteger.get());

        atomicInteger.compareAndSet(5, 2021);
        System.out.println(atomicInteger.get());

        System.out.println(atomicInteger.incrementAndGet());
        System.out.println(atomicInteger.get());

    }
}
