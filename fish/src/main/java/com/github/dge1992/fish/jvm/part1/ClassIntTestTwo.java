package com.github.dge1992.fish.jvm.part1;

/**
 * @author dge
 * @version 1.0
 * @date 2021-05-19 21:38
 */
public class ClassIntTestTwo {

    private static int num = 10;

    static {
        number = 30;
        num = 40;
    }

    private static int number = 20;

    public static void main(String[] args) {
        System.out.println(ClassIntTestTwo.num);
        System.out.println(ClassIntTestTwo.number);
    }
}
