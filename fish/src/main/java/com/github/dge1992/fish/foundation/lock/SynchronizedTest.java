package com.github.dge1992.fish.foundation.lock;

public class SynchronizedTest {

    public static void main(String[] args) {
        // testOne();
        // testTwo();
        // testThree();
        // testFour();
        testFive();
    }

    // 类锁 - synchronized修饰同步代码块, 锁定的对象为当前class类
    private static void testFive() {
        SynchronizedObjectLockFour synchronizedObjectLock = new SynchronizedObjectLockFour();
        SynchronizedObjectLockFour synchronizedObjectLock2 = new SynchronizedObjectLockFour();
        Thread thread = new Thread(synchronizedObjectLock);
        Thread thread2 = new Thread(synchronizedObjectLock2);
        thread.start();
        thread2.start();
    }

    // 类锁 - synchronized修饰静态方法
    private static void testFour() {
        SynchronizedObjectLockFour synchronizedObjectLock = new SynchronizedObjectLockFour();
        SynchronizedObjectLockFour synchronizedObjectLock2 = new SynchronizedObjectLockFour();
        Thread thread = new Thread(synchronizedObjectLock);
        Thread thread2 = new Thread(synchronizedObjectLock2);
        thread.start();
        thread2.start();
    }

    // 对象锁 - synchronized修饰普通方法
    private static void testThree() {
        SynchronizedObjectLockThree synchronizedObjectLock = new SynchronizedObjectLockThree();
        SynchronizedObjectLockThree synchronizedObjectLock2 = new SynchronizedObjectLockThree();
        Thread thread = new Thread(synchronizedObjectLock);
        Thread thread2 = new Thread(synchronizedObjectLock);
        thread.start();
        thread2.start();

    }

    // 对象锁 - synchronized修饰同步代码块, 锁对象为自定义对象
    private static void testTwo() {
        SynchronizedObjectLockTwo synchronizedObjectLock = new SynchronizedObjectLockTwo();
        SynchronizedObjectLock synchronizedObjectLock2 = new SynchronizedObjectLock();
        Thread thread = new Thread(synchronizedObjectLock);
        Thread thread2 = new Thread(synchronizedObjectLock2);
        thread.start();
        thread2.start();
    }

    // 对象锁 - synchronized修饰同步代码块，锁定对象为this
    private static void testOne() {
        SynchronizedObjectLock synchronizedObjectLock = new SynchronizedObjectLock();
        SynchronizedObjectLock synchronizedObjectLock2 = new SynchronizedObjectLock();
        Thread thread = new Thread(synchronizedObjectLock);
        Thread thread2 = new Thread(synchronizedObjectLock2);
        thread.start();
        thread2.start();
    }
}

class SynchronizedObjectLock implements Runnable {

    @Override
    public void run() {
        synchronized (this){
            System.out.println(Thread.currentThread().getName() + "线程开始运行");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "线程运行结束");
        }
    }
}

class SynchronizedObjectLockTwo implements Runnable {

    Object lock1 = new Object();
    Object lock2 = new Object();

    @Override
    public void run() {
        synchronized (lock1){
            System.out.println(Thread.currentThread().getName() + "线程开始运行");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "线程运行结束");
        }

        synchronized (lock2){
            System.out.println(Thread.currentThread().getName() + "线程开始运行");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "线程运行结束");
        }
    }
}

class SynchronizedObjectLockThree implements Runnable {

    @Override
    public void run() {
        test();
    }

    private synchronized void test() {
        System.out.println(Thread.currentThread().getName() + "线程开始运行");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + "线程运行结束");
    }
}

class SynchronizedObjectLockFour implements Runnable {

    @Override
    public void run() {
        test();
    }

    private synchronized static void test() {
        System.out.println(Thread.currentThread().getName() + "线程开始运行");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + "线程运行结束");
    }
}

class SynchronizedObjectLockFive implements Runnable {

    @Override
    public void run() {
        test();
    }

    private static void test() {
        synchronized (SynchronizedObjectLockFive.class) {
            System.out.println(Thread.currentThread().getName() + "线程开始运行");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "线程运行结束");
        }
    }
}


