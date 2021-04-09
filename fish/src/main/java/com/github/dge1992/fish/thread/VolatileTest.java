package com.github.dge1992.fish.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dge
 * @version 1.0
 * @date 2021-04-08 21:45
 */
public class VolatileTest {

    private volatile Integer num = 0;

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        VolatileTest test = new VolatileTest();
        test.atomicity();
        //test.visibility();
    }

    /**
     * 测试可见性
     * @author dge
     * @date 2021-04-09 10:12
     */
    public void visibility(){

        new Thread(() -> {
            while (num == 0){

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            num = 1;
        }).start();
    }

    /**
     * 测试原子性
     * @author dge
     * @date 2021-04-09 09:44
     */
    public void atomicity(){
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    add();
                    add2();
                }
            }).start();
        }
        // 等待上面的线程都执行完 默认有两个线程main线程和gc线程
        while (Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println(num);
        System.out.println(atomicInteger.get());
    }

    public void add(){
        num++;
    }

    public void add2(){
        atomicInteger.incrementAndGet();
    }
}
