package com.github.dge1992.fish.function;

import java.util.function.Consumer;

/**
 * @author dge
 * @version 1.0
 * @date 2021-04-01 10:02
 */
public class ConsumerTest {

    public static void main(String[] args) {
        /*Consumer<String> consumer = new Consumer<>(){

            @Override
            public void accept(String str) {
                System.out.println("hello " + str);
            }
        };*/

        Consumer<String> consumer = str -> System.out.println("hello " + str);

        consumer.accept("dge");
    }
}
