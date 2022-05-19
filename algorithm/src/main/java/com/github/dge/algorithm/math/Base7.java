package com.github.dge.algorithm.math;

/**
 * @author dge
 * @version 1.0
 * @date 2022-05-19 4:59 下午
 * https://leetcode.cn/problems/base-7/description/
 * 七进制数
 */
public class Base7 {

    public static void main(String[] args) {
        Base7 base7 = new Base7();
        String s = base7.convertToBase7(14);
        System.out.println(s);
    }

    public String convertToBase7(int num) {
        if(num == 0){
            return "0";
        }
        boolean isNegative = false;
        if(num < 0){
            isNegative = true;
            num = Math.abs(num);
        }
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(num % 2);
            num /= 2;
        }
        if(isNegative) {
            sb.append("-");
        }
        return sb.reverse().toString();
    }
}
