package com.github.dge1992.fish.structure.queue;

import cn.hutool.core.date.DateUtil;

import java.util.*;

public class QueueTest {

    public static void main(String[] args) {

        // testCaseOne();
        testCaseTwo();
    }

    private static void testCaseTwo() {
        long halfAnHourSeconds = DateUtil.betweenMs(DateUtil.beginOfDay(new Date()), DateUtil.endOfDay(new Date())) / 2;
        System.out.println("半天秒数为：" + halfAnHourSeconds);
    }

    private static void testCaseOne() {
        Deque<Integer> deque = new LinkedList<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addLast(3);

        System.out.println(deque.getFirst());
    }
}
