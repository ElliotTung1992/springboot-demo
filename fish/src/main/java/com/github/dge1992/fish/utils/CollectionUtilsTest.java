package com.github.dge1992.fish.utils;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dge
 * @version 1.0
 * @date 2022-03-18 5:33 下午
 */
public class CollectionUtilsTest {

    public static void main(String[] args) {
        CollectionUtilsTest test = new CollectionUtilsTest();
        test.containsAny();
    }

    private void containsAny(){
        List<String> listOne = new ArrayList<>();
        listOne.add("a");
        listOne.add("b");
        listOne.add("c");
        List<String> listTwo = new ArrayList<>();
        listTwo.add("b");
        listTwo.add("d");
        boolean b = CollectionUtils.containsAny(listOne, listTwo);
        System.out.println(b);
    }
}
