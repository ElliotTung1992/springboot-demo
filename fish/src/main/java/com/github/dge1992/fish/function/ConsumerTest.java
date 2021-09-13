package com.github.dge1992.fish.function;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @author dge
 * @version 1.0
 * @date 2021-04-01 10:02
 */
public class ConsumerTest {

    public static void main(String[] args) {

        Consumer<String> consumer = str -> System.out.println("hello " + str);
        consumer.accept("dge");

        ConsumerTest test = new ConsumerTest();
        test.caseOne();
    }

    private void caseOne() {
        List<String> list = new ArrayList<>();
        BiConsumer<List, String> accumulator = List::add;
        accumulator.accept(list, "aaa");
        System.out.println(list);
    }
}
