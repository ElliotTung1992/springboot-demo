package com.github.dge1992.fish.stream;

import org.apache.commons.collections4.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * @author linjingze
 * @date 2021/3/16 1:27 下午
 */
public class BigDecimalUtils {


    public static BigDecimal mul(BigDecimal v1, BigDecimal v2) {
        return mul(v1, v2, 2);
    }

    public static BigDecimal mulKeep4Scale(BigDecimal v1, BigDecimal v2) {
        return mul(v1, v2, 4);
    }

    public static BigDecimal mulKeep2Scale(BigDecimal v1, BigDecimal v2) {
        return mul(v1, v2, 2);
    }

    public static BigDecimal add(BigDecimal v1, BigDecimal v2) {
        return toBigDecimalStr(v1).add(toBigDecimalStr(v2));
    }

    public static BigDecimal subtract(BigDecimal v1, BigDecimal v2) {
        return toBigDecimalStr(v1).subtract(toBigDecimalStr(v2));
    }

    public static BigDecimal mul(BigDecimal v1, BigDecimal v2, int scale) {
        return toBigDecimalStr(v1).multiply(toBigDecimalStr(v2)).setScale(scale, RoundingMode.HALF_UP);
    }

    private static BigDecimal toBigDecimalStr(BigDecimal val) {
        return val == null ? BigDecimal.ZERO : new BigDecimal(String.valueOf(val.doubleValue()));
    }

    public static Long toLong(BigDecimal val) {
        return val == null ? 0L : val.longValue();
    }

    public static BigDecimal toBigDecimal(Long val) {
        return val == null ? BigDecimal.ZERO : new BigDecimal(val);
    }


    public static BigDecimal toKeep2Scale(BigDecimal val) {
        return toBigDecimalStr(val).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * 不处理 / by zero
     */
    public static BigDecimal div(BigDecimal v1, BigDecimal v2) {
        return toBigDecimalStr(v1).divide(toBigDecimalStr(v2), 2, RoundingMode.HALF_UP);
    }

    public static BigDecimal divKeep8Scale(BigDecimal v1, BigDecimal v2) {
        return toBigDecimalStr(v1).divide(toBigDecimalStr(v2), 8, RoundingMode.HALF_UP);
    }

    public static BigDecimal divKeep6Scale(BigDecimal v1, BigDecimal v2) {
        return toBigDecimalStr(v1).divide(toBigDecimalStr(v2), 6, RoundingMode.HALF_UP);
    }

    public static BigDecimal divKeep4Scale(BigDecimal v1, BigDecimal v2) {
        return toBigDecimalStr(v1).divide(toBigDecimalStr(v2), 4, RoundingMode.HALF_UP);
    }

    public static BigDecimal[] divideAndRemainder(BigDecimal v1, BigDecimal v2) {
        return toBigDecimalStr(v1).divideAndRemainder(toBigDecimalStr(v2));
    }

    public static boolean equals(BigDecimal v1, BigDecimal v2) {
        return v1.compareTo(v2) == 0;
    }

    public static boolean notEquals(BigDecimal v1, BigDecimal v2) {
        return v1.compareTo(v2) != 0;
    }
    
    public static boolean greater(BigDecimal v1, BigDecimal v2){
        return v1.compareTo(v2) > 0;
    }

    public static boolean greaterThanOrEqual(BigDecimal v1, BigDecimal v2) {
        return v1.compareTo(v2) >= 0;
    }

    public static boolean greaterThanZero(BigDecimal v) {
        return v.compareTo(BigDecimal.ZERO) > 0;
    }

    public static boolean greaterEqZero(BigDecimal v) {
        return v.compareTo(BigDecimal.ZERO) >= 0;
    }

    public static boolean lessEqZero(BigDecimal v) {
        return v.compareTo(BigDecimal.ZERO) <= 0;
    }

    public static boolean eqZero(BigDecimal v) {
        return equals(v, BigDecimal.ZERO);
    }

    public static boolean lessThanZero(BigDecimal v) {
        return v.compareTo(BigDecimal.ZERO) < 0;
    }

    public static boolean lessThan(BigDecimal v1, BigDecimal v2) {
        return v1.compareTo(v2) < 0;
    }
    public static boolean lessThanOrEqual(BigDecimal v1, BigDecimal v2) {
        return v1.compareTo(v2) <= 0;
    }

    /**
     * 获取小数点
     */
    public static int getNumberOfDecimalPlace(BigDecimal value) {
        final String s = value.toPlainString();
        final int index = s.indexOf('.');
        if (index < 0) {
            return 0;
        }
        return s.length() - 1 - index;
    }

    public static String toPlainString(BigDecimal val) {

        return "";
    }
    
    public static String toStringDefault(BigDecimal val, String defaultVal){
        return val == null ? defaultVal : toPlainString(val);
    }
    
    public static BigDecimal add(List<BigDecimal> bigDecimalList) {
        BigDecimal start = BigDecimal.ZERO;
        if (CollectionUtils.isEmpty(bigDecimalList)) {
            return start;
        }
        for (BigDecimal bigDecimal : bigDecimalList) {
            start = toBigDecimalStr(start).add(toBigDecimalStr(bigDecimal));
        }
        return start;
    }


    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("1.00");
        System.out.println(BigDecimalUtils.getNumberOfDecimalPlace(a.stripTrailingZeros()));
    }

}
