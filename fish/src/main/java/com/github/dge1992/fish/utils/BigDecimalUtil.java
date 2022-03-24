package com.github.dge1992.fish.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dge
 * @version 1.0
 * @date 2021-09-27 18:19
 */
public class BigDecimalUtil {

    public static void main(String[] args) {
        List<BigDecimal> list = new ArrayList<>();
        list.add(new BigDecimal("6.00"));
        list.add(new BigDecimal("2.22"));
        System.out.println(list.contains(new BigDecimal("6")));
    }
}
