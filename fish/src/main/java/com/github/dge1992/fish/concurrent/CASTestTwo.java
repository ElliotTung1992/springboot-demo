package com.github.dge1992.fish.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class CASTestTwo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        int andAdd = atomicInteger.getAndAdd(10);
        System.out.println(andAdd);
        int result = atomicInteger.get();
        System.out.println(result);

        boolean b = atomicInteger.compareAndSet(11, 10);
        System.out.println(b);

        AtomicReference<CASTestDomain> atomicReference = new AtomicReference<>();
        CASTestDomain casTestDomain = new CASTestDomain();
        casTestDomain.setName("Elliot");
        casTestDomain.setAge(11);
        atomicReference.set(casTestDomain);

        CASTestDomain casTestDomain2 = new CASTestDomain();
        casTestDomain2.setName("Bruce");
        casTestDomain2.setAge(21);

        boolean b1 = atomicReference.compareAndSet(casTestDomain, casTestDomain2);
        System.out.println(b1);
    }
}


