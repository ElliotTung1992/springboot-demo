package com.github.dge.algorithm.dynamicprogramming;

/**
 * @author dge
 * @version 1.0
 * @date 2022-04-21 9:49 上午
 * https://leetcode.com/problems/integer-break/description/
 * https://leetcode-cn.com/problems/integer-break/description/
 * 整数拆分
 */
public class IntegerBreak {

    public static void main(String[] args) {
        IntegerBreak integerBreak = new IntegerBreak();
        int sum = integerBreak.integerBreak2(10);
        System.out.println(sum);
    }

    public int integerBreak(int n) {
        int[] container = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            int sum = 0;
            for (int j = 1; j < i; j++) {
                sum = Math.max(Math.max(j * (i - j), j * container[i - j]), sum);
            }
            container[i] = sum;
        }
        return container[n];
    }

    public int integerBreak2(int n) {
        if (n < 4) {
            return n - 1;
        }
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(Math.max(2 * (i - 2), 2 * dp[i - 2]), Math.max(3 * (i - 3), 3 * dp[i - 3]));
        }
        return dp[n];
    }
}
