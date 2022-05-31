package com.github.dge.algorithm.math;

/**
 * @author dge
 * @version 1.0
 * @date 2022-05-31 5:00 下午
 * https://leetcode.com/problems/add-binary/description/
 * https://leetcode.cn/problems/add-binary/description/
 * 二进制求和
 */
public class AddBinary {

    public static void main(String[] args) {
        AddBinary addBinary = new AddBinary();
        String s = addBinary.addBinary("1010", "1011");
        System.out.println(s);
    }

    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int n = Math.max(a.length(), b.length()), arr = 0;
        for (int i = 0; i < n; i++) {
            arr += a.length() > i ? a.charAt(a.length() - 1 - i) - '0': 0;
            arr += b.length() > i ? b.charAt(b.length() - 1 - i) - '0': 0;
            sb.append(arr % 2);
            arr /= 2;
        }
        if(arr > 0){
            sb.append("1");
        }
        return sb.reverse().toString();
    }

}
