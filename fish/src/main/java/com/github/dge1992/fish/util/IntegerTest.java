package com.github.dge1992.fish.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class IntegerTest {

    public static void main(String[] args) {

        Long currentDate = System.currentTimeMillis();
        long l = TimeUnit.HOURS.toMillis(10);
        System.out.println(currentDate + l);
    }


}
