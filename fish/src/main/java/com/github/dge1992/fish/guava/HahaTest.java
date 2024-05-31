package com.github.dge1992.fish.guava;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class HahaTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list = ImmutableList.of("aa", "bb");
        System.out.println(list);
    }
}
