package com.github.dge1992.fish.function;

import java.util.function.Predicate;

/**
 * @author dge
 * @version 1.0
 * @date 2021-04-01 09:56
 */
public class PredicateTest {

    public static void main(String[] args) {
        /*Predicate<String> predicate = new Predicate<String>(){

            @Override
            public boolean test(String str) {
                return str.isBlank();
            }
        };*/

        Predicate<String> predicate = str -> str.isBlank();

        System.out.println(predicate.test(""));
    }
}
