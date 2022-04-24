package com.github.dge.algorithm.dynamicprogramming;

/**
 * @author dge
 * @version 1.0
 * @date 2022-04-22 5:02 下午
 * https://leetcode.com/problems/decode-ways/description/
 * https://leetcode-cn.com/problems/decode-ways/description/
 * 解码方法
 */
public class DecodeWays {

    public static void main(String[] args) {
        DecodeWays decodeWays = new DecodeWays();
        int i = decodeWays.numDecodings("123123");
        System.out.println(i);
    }

    public int numDecodings(String s) {
        int n = s.length();
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            if (s.charAt(i - 1) != '0') {
                f[i] += f[i - 1];
            }
            if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26)) {
                f[i] += f[i - 2];
            }
        }
        return f[n];
    }
}
