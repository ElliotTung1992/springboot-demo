package com.github.dge.everyday;

import com.google.common.base.Strings;
import org.checkerframework.checker.units.qual.A;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
        /*List<Integer> originalList = new ArrayList<>(); // 原始的List集合
        // 添加1200个元素到原始List集合中
        for (int i = 0; i < 1250; i++) {
            originalList.add(i);
        }

        int groupSize = 100; // 子集合的大小
        List<List<Integer>> groupedLists = originalList.stream()
                .collect(Collectors.groupingBy(index -> index / groupSize))
                .values()
                .stream()
                .map(group -> group.stream().collect(Collectors.toList()))
                .collect(Collectors.toList());

        for (List<Integer> groupedList : groupedLists) {
            System.out.println(groupedList);
        }*/

        List<Object> list = new ArrayList<>(); // 假设已经初始化了一个长度为1200的List对象数组

        for (int i = 0; i < 1250; i++) {
            list.add(i);
        }

        List<List<Object>> partitionedList = new ArrayList<>(); // 用于存储分组后的集合组

        int size = list.size();
        int batchSize = 100; // 每个集合组的大小为100

        for (int i = 0; i < size; i += batchSize) {
            int endIndex = Math.min(size, i + batchSize);
            List<Object> subList = list.subList(i, endIndex);
            partitionedList.add(subList);
        }
        System.out.println(partitionedList);


        /*List<Object> list = new ArrayList<>(); // 假设已经初始化了一个长度为1200的List对象数组

        int batchSize = 100; // 每个集合组的大小为100

        List<List<Object>> partitionedList = list.stream()
                .collect(Collectors.groupingBy(element -> (list.indexOf(element) / batchSize)))
                .values()
                .stream()
                .map(batch -> batch.stream().collect(Collectors.toList()))
                .collect(Collectors.toList());

        System.out.println(partitionedList);*/
    }
}
