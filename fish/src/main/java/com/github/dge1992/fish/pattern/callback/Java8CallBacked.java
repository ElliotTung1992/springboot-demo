package com.github.dge1992.fish.pattern.callback;

/**
 * @author dge
 * @version 1.0
 * @date 2021-02-04 15:09
 */
public class Java8CallBacked {

    public static void main(String[] args) {
        Java8CallBacker java8CallBacker = new Java8CallBacker();
        java8CallBacker.b(a -> {
            System.out.println(a);
            return "嘻嘻";
        });
    }
}
