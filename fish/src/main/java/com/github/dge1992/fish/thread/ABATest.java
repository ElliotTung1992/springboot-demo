package com.github.dge1992.fish.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author dge
 * @version 1.0
 * @date 2021-04-14 21:46
 */
public class ABATest {

    AtomicInteger atomicInteger = new AtomicInteger(10);
    AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(10, 1);

    public static void main(String[] args) {
        ABATest test = new ABATest();
        //test.testOne(test);
        test.testTwo(test);
    }

    /**
     * 通过邮票去判断当前值有没有被修改 - 相当于乐观锁
     * @param test test
     * @author dge
     * @date 2021-04-14 21:59
     */
    private void testTwo(ABATest test) {
        new Thread(() -> {
            test.atomicStampedReference.compareAndSet(10, 11, 1, 1 + 1);
            System.out.println(test.atomicStampedReference.getReference());
            int stamp2 = test.atomicStampedReference.getStamp();
            test.atomicStampedReference.compareAndSet(11, 10, stamp2, stamp2 + 1);
            System.out.println(test.atomicStampedReference.getReference());
        }).start();

        new Thread(() -> {
            System.out.println(test.atomicStampedReference.compareAndSet(10, 11, 1, 1 + 1));
            System.out.println(test.atomicStampedReference.getReference());
        }).start();
    }

    /**
     * ABA现象 - 后面的线程判断的已经是前线线程修改过的
     * @param test test
     * @author dge
     * @date 2021-04-14 21:50
     */
    private void testOne(ABATest test) {

        new Thread(() -> {
            test.atomicInteger.compareAndSet(10, 20);
            test.atomicInteger.compareAndSet(20, 10);
            System.out.println(test.atomicInteger.get());
        }).start();


        new Thread(() -> {
            test.atomicInteger.compareAndSet(10, 21);
            System.out.println(test.atomicInteger.get());
        }).start();
    }
}
