package com.github.dge1992.fish.thread;

import java.util.concurrent.TimeUnit;

/**
 * 守护线程
 */
public class DaemonThread {

    public static void main(String[] args) {

        // 用户线程
        Thread userThread = new Thread(() -> {
            int i = 0;
            while (i < 2) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("userThread" + "执行了");
                i++;
            }
        });

        // 守护线程
        Thread daemonThread = new Thread(() -> {
            int i = 0;
            while (i < 4) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("daemonThread" + "执行了");
                i++;
            }
        });

        daemonThread.setDaemon(true);

        userThread.start();
        daemonThread.start();
        System.out.println("结束案例");
    }
}
