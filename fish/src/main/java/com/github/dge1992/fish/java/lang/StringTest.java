package com.github.dge1992.fish.java.lang;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author dge
 * @version 1.0
 * @date 2021-07-02 09:49
 */
public class StringTest {

    public static void main(String[] args) {
        testTwo();
    }

    private static void testOne() {
        System.out.println(UUID.randomUUID().toString().replace("-", ""));
    }

    private static void testTwo() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        for (Integer integer : list) {
            System.out.println(integer);
            if(integer.equals(1)){
                list.remove(integer);
            }
        }

        /*List<Integer> list = new ArrayList<>();
        list.add(1);
        System.out.println(list.get(-1));*/
    }
}
