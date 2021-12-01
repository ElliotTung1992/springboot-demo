package com.github.dge1992.fish.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dge
 * @version 1.0
 * @date 2021-11-23 10:39 AM
 */
public class ForeachTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        List<Integer> collect = list.stream()
                .peek(i -> {
                    if (i == 2) {
                        return;
                    }
                }).collect(Collectors.toList());

        System.out.println(collect);
    }
}
