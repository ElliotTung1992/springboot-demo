package com.github.dge1992.fish.collection.map;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dge
 * @version 1.0
 * @date 2022-08-05 4:50 PM
 */
public class HashMapTest {

    public static void main(String[] args) {
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < 11; i++) {
            String key = String.valueOf(i % 2);
            List<String> strings = map.computeIfAbsent(key, k -> Lists.newArrayList());
            strings.add(String.valueOf(i));
        }
        map.forEach((k, v) -> {
            System.out.println(k);
            System.out.println(v);
        });
    }
}
