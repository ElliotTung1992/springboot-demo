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
        //test.testFive();
        //test.testSix();
        test.testSeven();
    }

    /**
     * 公平锁和非公平锁
     * @author dge
     * @date 2021-04-20 22:12
     */
    private void testSeven() {
        // 非公平锁
        Lock lock = new ReentrantLock();
        // 公平锁
        Lock lock2 = new ReentrantLock(true);
    }

    /**
     * 测试不可重入锁
     * @author dge
     * @date 2021-04-19 22:53
     */
    private void testSix() {
        SpinLock2 spinLock = new SpinLock2();

        new Thread(() -> {
            spinLock.lock();
            System.out.println("haha");
            spinLock.lock();
            System.out.println("hehe");

            spinLock.unlock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLock.unlock();
        }).start();

        new Thread(() -> {
            spinLock.lock();
            System.out.println("xixi");
        }).start();
    }

    /**
     * 测试不可重入锁
     * @author dge
     * @date 2021-04-19 22:53
     */
    private void testFive() {
        SpinLock spinLock = new SpinLock();

        new Thread(() -> {
            spinLock.lock();
            System.out.println("haha");
            spinLock.lock();
            System.out.println("hehe");
        }).start();
    }

    /**
     * 自旋锁测试
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
     * 可重入锁测试
     * @author dge
     * @date 2021-04-14 22:15
     */
    private void testTwo(AllLock test) {
        test.haha();
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

    private synchronized void haha(){
        System.out.println("start haha");
        hehe();
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
 * 自旋锁 - 不可重入锁
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

/**
 * 自旋锁 - 可重入锁
 * @author dge
 * @date 2021-04-14 22:27
 */
class SpinLock2{

    AtomicReference<Thread> atomicReference = new AtomicReference<>(null);

    private int state = 0;

    public void lock(){
        Thread thread = Thread.currentThread();
        if(atomicReference.get() == thread){
            state++;
            return;
        }
        while (!atomicReference.compareAndSet(null, thread)){

        }
    }

    public void unlock(){
        Thread thread = Thread.currentThread();
        if(thread == atomicReference.get()){
            if(state > 0){
                state--;
            }else{
                atomicReference.compareAndSet(thread, null);
            }
        }
    }
}


