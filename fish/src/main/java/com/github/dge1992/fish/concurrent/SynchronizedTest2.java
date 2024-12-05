package com.github.dge1992.fish.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class SynchronizedTest2 {

    private Integer start = 0;

    public static void main(String[] args) {
        String key = "test";
        SynchronizedTest2 synchronizedTest2 = new SynchronizedTest2();
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                boolean tryLock = false;
                tryLock = synchronizedTest2.add(key);
                if(tryLock){

                }
            }).start();
        }
    }

    public boolean add(String key){
        synchronized (key){
            start = start + 1;
            System.out.println(start);
            return true;
        }
    }
}
