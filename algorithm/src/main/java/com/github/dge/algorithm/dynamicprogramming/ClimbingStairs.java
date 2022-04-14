package com.github.dge.algorithm.dynamicprogramming;

/**
 * @author dge
 * @version 1.0
 * @date 2022-04-14 5:47 下午
 * https://leetcode.com/problems/climbing-stairs/description/
 * https://leetcode-cn.com/problems/climbing-stairs/description/
 * 爬楼梯
 */
public class ClimbingStairs {

    public static void main(String[] args) {
        ClimbingStairs climbingStairs = new ClimbingStairs();
        int i = climbingStairs.climbStairs(4);
        System.out.println(i);
    }

    public int climbStairs(int n) {
        int[][] q = {{1, 1}, {1, 0}};
        int[][] res = pow(q, n);
        return res[0][0];
    }

    public int[][] pow(int[][] a, int n) {
        int[][] ret = {{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = multiply(ret, a);
            }
            n >>= 1;
            a = multiply(a, a);
        }
        return ret;
    }

    public int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }

    public int climbStairs3(int n) {
        int a , b = 0, c = 1;
        for (int i = 1; i <= n; i++) {
            a = b;
            b = c;
            c = a + b;
        }
        return c;
    }

    private int count;

    public int climbStairs2(int n) {
        if(n == 0){
            count++;
        }
        for (int i = 1; i <= 2; i++) {
            if(n - i >= 0){
                climbStairs(n - i);
            }
        }
        return count;
    }

}
