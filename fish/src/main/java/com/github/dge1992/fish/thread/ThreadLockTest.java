package com.github.dge1992.fish.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dge
 * @version 1.0
 * @date 2021-03-16 22:07
 */
public class ThreadLockTest {

    public static void main(String[] args) {

        ThreadLockTest test = new ThreadLockTest();

        //caseOne();

        //test.caseTwo();

        //test.caseThree();

        //test.caseFour();

        //test.caseFive();

        //test.testSix();

        //test.testSeven();

        testEight();
    }

    private static void testEight() {
        new Thread(() -> ThreadLockTest.sendMessage()).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> ThreadLockTest.call()).start();
    }

    /**
     * 案例锁的调用者对象
     * @author dge
     * @date 2021-03-22 23:09
     */
    private void testSeven() {
        Phone phone = new Phone();

        new Thread(() -> phone.sendMessage()).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> phone.call()).start();
    }

    private void testSix() {

        Data3 data3 = new Data3();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data3.printA();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data3.printB();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data3.printC();
            }
        }, "C").start();
    }

    private void caseFive() {
        Data2 data2 = new Data2();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data2.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data2.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data2.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data2.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }

    /**
     * 生产者和消费者问题
     * @author dge
     * @date 2021-03-18 22:13
     */
    private void caseFour() {
        Data data = new Data();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "a").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "b").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "c").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "d").start();
    }

    /**
     * Lock锁模拟多线程
     * @author dge
     * @date 2021-03-18 21:26
     */
    private void caseThree() {
        Tickets2 tickets = new Tickets2();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                tickets.sell();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                tickets.sell();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                tickets.sell();
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                tickets.sell();
            }
        }, "D").start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                tickets.sell();
            }
        }, "E").start();
    }

    /**
     * 模拟多线程场景
     * @author dge
     * @date 2021-03-17 22:24
     */
    private void caseTwo() {
        Tickets tickets = new Tickets();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                tickets.sell();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                tickets.sell();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                tickets.sell();
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                tickets.sell();
            }
        }, "D").start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                tickets.sell();
            }
        }, "E").start();
    }

    /**
     * 获取计算机的cpu核数
     * @author dge
     * @date 2021-03-16 22:08
     */
    private static void caseOne() {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println(availableProcessors);

        // 获取线程的状态
        Thread.currentThread().getState();

        ThreadLockTest test = new ThreadLockTest();
        try {
            test.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class Tickets{

        private Integer num = 10;

        public synchronized void sell(){
            if(num > 0){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "卖出第" + (num--) + "张票，" + "剩余" + num + "张票");
            }
        }
    }

    class Tickets2{

        private Integer num = 10;

        Lock lock = new ReentrantLock();

        public void sell(){
            lock.lock();
            try {
                if(num > 0){
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "卖出第" + (num--) + "张票，" + "剩余" + num + "张票");
                }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                lock.unlock();
            }


        }
    }

    class Data {

        private Integer num = 0;

        public synchronized void increment() throws InterruptedException {
            if (num == 1){
                this.wait();
            }
            num ++;
            System.out.println(Thread.currentThread().getName() + "->" + num);
            this.notifyAll();
        }

        public synchronized void decrement() throws InterruptedException {
            if (num < 1){
                this.wait();
            }
            num --;
            System.out.println(Thread.currentThread().getName() + "->" + num);
            this.notifyAll();
        }

    }

    class Data2 {

        private Integer num = 0;

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        public void increment() throws InterruptedException {
            lock.lock();
            try {
                while (num > 0) {
                    condition.await();
                }
                System.out.println(Thread.currentThread().getName() + " num is " + (num++));
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void decrement() throws InterruptedException {
            lock.lock();
            try {
                while (num < 1){
                    condition.await();
                }
                System.out.println(Thread.currentThread().getName() + " num is " + (num--));
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    class Data3 {

        private Lock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();
        private Integer num = 1;

        public void printA() {
            lock.lock();
            try {
                // 判断 业务 通知
                while (num != 1){
                    conditionA.await();
                }
                System.out.println(Thread.currentThread().getName() + " num is " + num);
                num = 2;
                conditionB.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void printB(){
            lock.lock();
            try {
                // 判断 业务 通知
                while (num != 2){
                    conditionB.await();
                }
                System.out.println(Thread.currentThread().getName() + " num is " + num);
                num = 3;
                conditionC.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void printC(){
            lock.lock();
            try {
                // 判断 业务 通知
                while (num != 3){
                    conditionC.await();
                }
                System.out.println(Thread.currentThread().getName() + " num is " + num);
                num = 1;
                conditionA.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    class Phone{

        public synchronized void sendMessage() {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("发短信");
        }

        public synchronized void call() {
            System.out.println("打电话");
        }
    }

    public static synchronized void sendMessage() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public static synchronized void call() {
        System.out.println("打电话");
    }
}
