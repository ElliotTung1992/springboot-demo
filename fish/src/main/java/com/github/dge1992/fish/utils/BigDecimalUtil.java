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
        BigDecimal receiptBillAmount = new BigDecimal("10000.00");
        BigDecimal remainingAmount = new BigDecimal("10000.00");
        BigDecimal receiptConsumingAmount = new BigDecimal("0");
        BigDecimal multiply = receiptBillAmount.subtract(receiptConsumingAmount);
        int i = multiply.compareTo(remainingAmount);

        System.out.println(new BigDecimal("-1").compareTo(BigDecimal.ZERO));

        List<BigDecimal> list = new ArrayList<>();
        list.add(new BigDecimal("6.00"));
        list.add(new BigDecimal("2.22"));
        System.out.println(list.contains(new BigDecimal("6")));
    }


}
