package com.github.dge1992.fish.thread;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author dge
 * @version 1.0
 * @date 2021-03-25 16:31
 * 线程工具类
 */
public class ThreadUtils {

    public static void main(String[] args) {
        ThreadUtils utils = new ThreadUtils();
        //utils.testOne();
        //utils.testTwo();
        utils.testThree();
    }

    /**
     * 信号量Semaphore使用
     * @author dge
     * @date 2021-03-25 17:34
     */
    private void testThree() {
        // 创建信号量
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i < 10; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "获取位置");
                    Random random = new Random();
                    TimeUnit.SECONDS.sleep(random.nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println(Thread.currentThread().getName() + "离开位置");
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }

    /**
     * 计数器CyclicBarrier使用
     * @author dge
     * @date 2021-03-25 17:28
     */
    private void testTwo() {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("可以许一个愿望");
        });

        for (int i = 1; i < 8; i++) {
            final Integer temp = i;
            new Thread(() -> {
                System.out.println("集齐第" + temp + "龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    /**
     * CountDownLatch计数器使用
     * @author dge
     * @date 2021-03-25 16:47
     */
    private void testOne() {

        CountDownLatch countDownLatch = new CountDownLatch(7);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " go");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        try {
            countDownLatch.await(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("关门");
    }


}
