package com.github.dge1992.fish.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * java 关键字synchronized
 * Java并发编程的艺术
 */
@Slf4j
public class SynchronizedTest {


    public static void main(String[] args) {
        SynchronizedTest synchronizedTest = new SynchronizedTest();
        // synchronizedTest.caseOne();
        // synchronizedTest.caseTwo();
        // synchronizedTest.caseThree();
        // synchronizedTest.caseFour();
        // synchronizedTest.caseFive();
        synchronizedTest.caseSix();
    }

    private void caseOne() {
        // 对象锁 同个实例之间相互影响
        SynchronizedTest test = new SynchronizedTest();
        new Thread(() -> {
            test.addList(1);
        }).start();
        new Thread(() -> {
            test.addList(2);
        }).start();
    }

    private void caseTwo() {
        // 对象锁 不同实例之间互不影响
        SynchronizedTest test1 = new SynchronizedTest();
        SynchronizedTest test2 = new SynchronizedTest();

        new Thread(() -> {
            test1.addList(1);
        }).start();
        new Thread(() -> {
            test2.addList(1);
        }).start();
    }

    private void caseThree() {
        // 对象锁实现二 锁指定对象
        SynchronizedTest test = new SynchronizedTest();
        new Thread(() -> {
            test.addList2(1);
        }).start();
        new Thread(() -> {
            test.addList2(2);
        }).start();
    }

    private void caseFour() {
        // 对象锁实现二 不同实例之间互不影响
        SynchronizedTest test1 = new SynchronizedTest();
        SynchronizedTest test2 = new SynchronizedTest();

        new Thread(() -> {
            test1.addList2(1);
        }).start();
        new Thread(() -> {
            test2.addList2(1);
        }).start();
    }

    private void caseFive() {
        // 类锁的实现方式一
        SynchronizedTest test1 = new SynchronizedTest();
        SynchronizedTest test2 = new SynchronizedTest();

        new Thread(() -> {
            test1.addList3(1);
        }).start();
        new Thread(() -> {
            test2.addList3(1);
        }).start();
    }

    private void caseSix() {
        // 类锁的实现方式二
        SynchronizedTest test1 = new SynchronizedTest();
        SynchronizedTest test2 = new SynchronizedTest();

        new Thread(() -> {
            test1.addList4(1);
        }).start();
        new Thread(() -> {
            test2.addList4(1);
        }).start();
    }

    static List<Integer> list = new ArrayList<>();

    /**
     * 对象锁的第一种实现方式: synchronized修饰普通方法 锁的是当前对象this
     * @param i i
     */
    public synchronized void addList(Integer i){
        log.info("start add...");
        list.add(i);
        try {
            Thread.sleep(5000);
            log.info("waiting...");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("end add...");
    }

    Object lock = new Object();

    /**
     * 对象锁的第二种实现方式 同步代码块锁锁指定对象
     * @param i i
     */
    public void addList2(Integer i){
        synchronized (lock){
            log.info("start add...");
            list.add(i);
            try {
                Thread.sleep(5000);
                log.info("waiting...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("end add...");
        }
    }

    /**
     * 类锁的第一种实现方式 synchronized修饰静态方法
     * 指定锁对象为当前类的Class对象
     * @param i i
     */
    public static synchronized void addList3(Integer i){
        log.info("start add...");
        list.add(i);
        try {
            Thread.sleep(5000);
            log.info("waiting...");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("end add...");
    }

    /**
     * 类锁的第二种实现方式 同步代码块指定锁对象为Class对象
     * @param i i
     */
    public void addList4(Integer i){
       synchronized (Object.class){
           log.info("start add...");
           list.add(i);
           try {
               Thread.sleep(5000);
               log.info("waiting...");
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
           log.info("end add...");
       }
    }
}
