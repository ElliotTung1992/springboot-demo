package com.github.dge1992.fish.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dge
 * @version 1.0
 * @date 2021-04-13 22:35
 */
public class CASTest {

    AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) {
        CASTest test = new CASTest();
        test.testOne();

        /*Integer i = 99;
        Integer j = i;
        j = j + 0;

        System.out.println(i == j);
        System.out.println(i.equals(j));*/

    }

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
