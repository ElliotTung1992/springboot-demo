package com.github.dge1992.fish.collection.set;

import java.util.HashSet;
import java.util.Set;

/**
 * @author dge
 * @version 1.0
 * @date 2022-07-25 9:50 AM
 */
public class SetTest {

    public static void main(String[] args) {
        Set<String> setOne = new HashSet<>();
        setOne.add("a");
        setOne.add("b");
        setOne.add("c");
        Set<String> setTwo = new HashSet<>();
        setTwo.add("a");
        setTwo.add("c");
        setTwo.add("d");

        setOne.addAll(setTwo);

        System.out.println(setOne);

    }
}
