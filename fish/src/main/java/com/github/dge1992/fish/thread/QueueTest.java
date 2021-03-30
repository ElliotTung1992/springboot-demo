package com.github.dge1992.fish.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author dge
 * @version 1.0
 * @date 2021-03-30 17:41
 */
public class QueueTest {

    public static void main(String[] args) throws InterruptedException {
        QueueTest queueTest = new QueueTest();
        // queueTest.testOne();
        // queueTest.testTwo();
        // queueTest.testThree();
        // queueTest.testFour();
        queueTest.testFive();
    }

    /**
     * 阻塞报异常
     * @author dge
     * @date 2021-03-30 17:45
     */
    public void testOne(){
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);

        blockingQueue.add("a");
        blockingQueue.add("b");
        blockingQueue.add("c");
        //blockingQueue.add("d");

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        //System.out.println(blockingQueue.remove());
    }

    /**
     * 阻塞就结束
     * @author dge
     * @date 2021-03-30 17:57
     */
    public void testTwo(){
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);

        blockingQueue.offer("a");
        blockingQueue.offer("b");
        blockingQueue.offer("c");
        //blockingQueue.offer("d");

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }

    /**
     * 阻塞就一直阻塞等待
     * @author dge
     * @date 2021-03-30 17:55
     */
    public void testThree() throws InterruptedException {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);

        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        //blockingQueue.put("d");

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
    }

    /**
     * 超时阻塞
     * @author dge
     * @date 2021-03-30 18:00
     */
    public void testFour() throws InterruptedException {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);

        blockingQueue.offer("a", 2, TimeUnit.SECONDS);
        blockingQueue.offer("b", 2, TimeUnit.SECONDS);
        blockingQueue.offer("c", 2, TimeUnit.SECONDS);
        //blockingQueue.offer("d", 2, TimeUnit.SECONDS);

        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
    }

    public void testFive() throws InterruptedException {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println("开始添加数据" + i);
                    synchronousQueue.put(String.valueOf(i));
                    System.out.println("结束添加数据" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println("开始获取数据" + i);
                    synchronousQueue.take();
                    System.out.println("结束获取数据" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
