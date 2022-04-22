package com.github.dge.algorithm.dynamicprogramming;

/**
 * @author dge
 * @version 1.0
 * @date 2022-04-22 4:04 下午
 * https://leetcode.com/problems/perfect-squares/description/
 * https://leetcode-cn.com/problems/perfect-squares/description/
 * 完全平方数
 */
public class PerfectSquares {

    public static void main(String[] args) {
        PerfectSquares perfectSquares = new PerfectSquares();
        int i = perfectSquares.numSquares(12);
        System.out.println(i);
    }

    public int numSquares(int n) {
        int[] container = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int sum = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                sum = Math.min(sum, container[i - j * j]);
            }
            container[i] = sum + 1;
        }
        return container[n];
    }
}
