package com.github.dge1992.fish.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author dge
 * @version 1.0
 * @date 2021-03-29 21:49
 */
public class ReadWriteLockTest {

    public static void main(String[] args) {
        MyCache cache = new MyCache();

        Random random = new Random();

        for (int i = 1; i < 10; i++) {
            final int temp = i;
            new Thread(() -> {
                cache.put(String.valueOf(1), temp);
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i < 6; i++) {
            new Thread(() -> {
                cache.get(String.valueOf(random.nextInt(3)));
            }, String.valueOf(i)).start();
        }
    }

}

class MyCache {

    Map<String, Object> map = new HashMap<>();

    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value){
        readWriteLock.writeLock().lock();
        try {
            System.out.println("MyCache 写开始" + Thread.currentThread().getName());
            map.put(key, value);
            System.out.println("MyCache 写结束" + Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public Object get(String key){
        readWriteLock.readLock().lock();
        try {
            System.out.println("MyCache 读开始" + Thread.currentThread().getName());
            Object value = map.get(key);
            System.out.println("MyCache 读结束" + Thread.currentThread().getName());
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
        return null;
    }
}