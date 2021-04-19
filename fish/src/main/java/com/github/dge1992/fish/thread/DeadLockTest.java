package com.github.dge1992.fish.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author dge
 * @version 1.0
 * @date 2021-04-19 21:55
 * 死锁学习
 */
public class DeadLockTest {

    private static String strOne = "strOne";
    private static String strTwo = "strTwo";

    public static void main(String[] args) {

        new Thread(() -> {
            try {
                synchronized (DeadLockTest.strOne){
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(strOne + "被锁了");
                    synchronized (DeadLockTest.strTwo){
                        System.out.println(strTwo + "被锁了");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                synchronized (DeadLockTest.strTwo){
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(strTwo + "被锁了");
                    synchronized (DeadLockTest.strOne){
                        System.out.println(strOne + "被锁了");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
