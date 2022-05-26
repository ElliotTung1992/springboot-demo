package com.github.dge.algorithm.math;

/**
 * @author dge
 * @version 1.0
 * @date 2022-05-26 4:44 下午
 * https://leetcode.com/problems/factorial-trailing-zeroes/description/
 * https://leetcode.cn/problems/factorial-trailing-zeroes/description/
 * 阶乘后的零
 */
public class FactorialTrailingZeroes {

    public static void main(String[] args) {
        FactorialTrailingZeroes zeroes = new FactorialTrailingZeroes();
        int i = zeroes.trailingZeroes(30);
        System.out.println(i);
    }

    public int trailingZeroes2(int n) {
        int count = 0;
        for (int i = 5; i <= n; i += 5) {
            for (int j = i; j % 5 == 0 ; j /= 5) {
                count++;
            }
        }
        return count;
    }

    public int trailingZeroes(int n) {
        int ans = 0;
        while (n != 0) {
            n /= 5;
            ans += n;
        }
        return ans;
    }
}
