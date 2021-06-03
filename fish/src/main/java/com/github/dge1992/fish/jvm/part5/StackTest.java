package com.github.dge1992.fish.jvm.part5;

/**
 * @author dge
 * @version 1.0
 * @date 2021-05-31 17:47
 */
public class StackTest {

    private static int count = 1;

    // 18550
    // 7483
    public static void main(String[] args) {
        System.out.println(count);
        count++;
        main(args);
    }
}
