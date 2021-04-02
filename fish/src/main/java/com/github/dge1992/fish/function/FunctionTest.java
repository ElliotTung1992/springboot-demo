package com.github.dge1992.fish.function;

import java.util.function.Function;

/**
 * @author dge
 * @version 1.0
 * @date 2021-04-01 09:47
 */
public class FunctionTest {

    public static void main(String[] args) {

        /*Function<String, Integer> function = new Function<String, Integer>() {
            @Override
            public Integer apply(String str) {
                return str.length();
            }
        };*/

        Function<String, Integer> function = (str) -> str.length();

        System.out.println(function.apply("hello"));
    }
}
