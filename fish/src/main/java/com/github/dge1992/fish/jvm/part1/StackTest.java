package com.github.dge1992.fish.jvm.part1;

import java.util.concurrent.TimeUnit;

/**
 * @author dge
 * @version 1.0
 * @date 2021-05-12 22:08
 */
public class StackTest {

    public static void main(String[] args) {
        int i = 2;
        int j = 3;
        int z = 4;
        int k = i + j;

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("hello");
    }
}
