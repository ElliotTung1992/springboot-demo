package com.github.dge1992.fish.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dge
 * @version 1.0
 * @date 2021-04-14 22:06
 * 各种锁
 */
public class AllLock {

    private Lock lockOne = new ReentrantLock();
    private Lock lockTwo = new ReentrantLock();

    public static void main(String[] args) {

        AllLock test = new AllLock();
        //test.testOne();
        //test.testTwo(test);
        //test.testThree();
        //test.testFour();
    }

    /**
     * 自旋锁
     * @author dge
     * @date 2021-04-14 22:34
     */
    private void testFour() {
        SpinLock spinLock = new SpinLock();
        new Thread(() -> {
            spinLock.lock();
            try {
                System.out.println("haha");
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                spinLock.unlock();
            }

        }).start();

        new Thread(() -> {
            spinLock.lock();
            try {
                System.out.println("hehe");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                spinLock.unlock();
            }
        }).start();
    }

    /**
     * 可重入锁
     * @author dge
     * @date 2021-04-14 22:23
     */
    private void testThree() {

        new Thread(() -> {
            xixi();
        }, "A").start();

        new Thread(() -> {
            wuwu();
        }, "B").start();
    }

    /**
     * 可重入锁
     * @author dge
     * @date 2021-04-14 22:15
     */
    private void testTwo(AllLock test) {
        AllLock test2 = new AllLock();
        test.haha(test2);
    }

    /**
     * 公平锁和非公平锁
     * @author dge
     * @date 2021-04-14 22:07
     */
    private void testOne() {
        // 默认就是非公平锁
        Lock lock = new ReentrantLock();

        // 创建的是公平锁
        Lock lock2 = new ReentrantLock(true);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                lock.lock();
                System.out.println(Thread.currentThread().getName());
                lock.unlock();
            }, String.valueOf(i)).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("============================");

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                lock2.lock();
                System.out.println(Thread.currentThread().getName());
                lock2.unlock();
            }, String.valueOf(i)).start();
        }
    }

    private synchronized void haha(AllLock allLock){
        System.out.println("start haha");
        allLock.hehe();
        System.out.println("end haha");
    }

    private synchronized void hehe(){
        System.out.println("start hehe");
        System.out.println("end hehe");
    }

    private void xixi(){
        lockOne.lock();
        try {
            System.out.println(Thread.currentThread().getName());
            wuwu();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lockOne.unlock();
        }
    }

    private void wuwu(){
        lockTwo.lock();
        try {
            System.out.println(Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lockTwo.unlock();
        }
    }
}

/**
 * 自旋锁
 * @author dge
 * @date 2021-04-14 22:27
 */
class SpinLock{

    AtomicReference<Thread> atomicReference = new AtomicReference<>(null);

    public void lock(){
        Thread thread = Thread.currentThread();
        while (!atomicReference.compareAndSet(null, thread)){

        }
    }

    public void unlock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
    }
}


