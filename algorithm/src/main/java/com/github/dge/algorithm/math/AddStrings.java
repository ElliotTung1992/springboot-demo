package com.github.dge.algorithm.math;

/**
 * @author dge
 * @version 1.0
 * @date 2022-06-01 2:41 下午
 * https://leetcode.com/problems/add-strings/description/
 * https://leetcode.cn/problems/add-strings/description/
 * 字符串相加
 */
public class AddStrings {

    public static void main(String[] args) {
        AddStrings addStrings = new AddStrings();
        String s = addStrings.addStrings("11", "123");
        System.out.println(s);
    }

    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int n = Math.max(num1.length(), num2.length()), arr = 0;
        for (int i = 0; i < n; i++) {
            arr += num1.length() > i ? num1.charAt(num1.length() - i - 1) - '0': 0;
            arr += num2.length() > i ? num2.charAt(num2.length() - i - 1) - '0': 0;
            sb.append(arr % 10);
            arr /= 10;
        }
        if(arr > 0){
            sb.append(arr);
        }
        return sb.reverse().toString();
    }
}
