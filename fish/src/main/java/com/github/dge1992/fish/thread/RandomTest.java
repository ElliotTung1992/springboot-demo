package com.github.dge1992.fish.thread;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomTest {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        // 多线程环境下, 获取newSeed的时候使用CAS比较, 使用的是自旋锁
        // 当线程竞争压力大的时候存在CPU资源消耗
        Random random = new Random();
        int i = random.nextInt(10);
        System.out.println(i);

        // 使用随机通用算法, 且种子只存在各自的线程中, 不同线程之间互补影响
        ThreadLocalRandom current = ThreadLocalRandom.current();
        int i1 = current.nextInt(10);
        System.out.println(i1);

        // 在Linux环境下存在性能问题
        SecureRandom instanceStrong = SecureRandom.getInstanceStrong();
        int i2 = instanceStrong.nextInt(10);
        System.out.println(i2);
    }
}
