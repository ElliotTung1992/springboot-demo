package com.github.dge.everyday;

import java.util.StringJoiner;

public class SumOfDigitsOfStringAfterConvert {

    public static void main(String[] args) {
        SumOfDigitsOfStringAfterConvert s = new SumOfDigitsOfStringAfterConvert();
        int zbax = s.getLucky("zbax", 2);
        System.out.println(zbax);
    }

    public int getLucky(String s, int k) {
        String str = null;
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            sb.append(aChar - 'a' + 1);
        }
        str = sb.toString();
        int sum = 0;
        for (int i = 0; i < k; i++) {
            char[] chars1 = str.toCharArray();
            sum = 0;
            for (char c : chars1) {
                sum += Integer.valueOf(String.valueOf(c));
            }
            str = String.valueOf(sum);
        }
        return sum;
    }
}
