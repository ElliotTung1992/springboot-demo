package com.github.dge1992.fish.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dge
 * @version 1.0
 * @date 2021-07-21 16:19
 */
public class BatchUtil {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("1", "2", "3");
        String s = strings.stream().collect(Collectors.joining(","));
        System.out.println(s);
    }
}
