package com.github.dge1992.fish.function;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * @author dge
 * @version 1.0
 * @date 2021-04-01 09:47
 */
public class FunctionTest {

    public static void main(String[] args) {
        Function<String, Integer> function = (str) -> str.length();
        System.out.println(function.apply("hello"));

        FunctionTest test = new FunctionTest();
        test.caseOne();
    }

    private void caseOne() {
        List<String> listOne = new ArrayList<>();
        listOne.add("aaa");
        List<String> listTwo = new ArrayList<>();
        listTwo.add("bbb");
        BinaryOperator<List> combiner = (left, right) -> { left.addAll(right); return left; };
        combiner.apply(listOne, listTwo);
        System.out.println(listOne);
    }
}
