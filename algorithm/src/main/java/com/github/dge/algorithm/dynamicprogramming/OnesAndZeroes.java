package com.github.dge.algorithm.dynamicprogramming;

import java.util.Arrays;

/**
 * @author dge
 * @version 1.0
 * @date 2022-04-30 1:21 下午
 * https://leetcode.com/problems/ones-and-zeroes/description/
 * https://leetcode-cn.com/problems/ones-and-zeroes/description/
 * 一和零
 */
public class OnesAndZeroes {

    public static void main(String[] args) {
        OnesAndZeroes onesAndZeroes = new OnesAndZeroes();
        String[] strs = {"10", "0001", "111001", "1", "0"};
        int m = 5, n = 3;
        int maxForm = onesAndZeroes.findMaxForm(strs, m, n);
        System.out.println(maxForm);

        int[] zerosOnes = onesAndZeroes.getZerosOnes("1100");
        System.out.println(Arrays.toString(zerosOnes));
    }

    public int findMaxForm(String[] strs, int m, int n) {
        int length = strs.length;
        int[][][] bp = new int[length + 1][m + 1][n + 1];
        for (int i = 1; i <= length; i++) {
            int[] zerosOnes = getZerosOnes(strs[i - 1]);
            int zeros = zerosOnes[0], ones = zerosOnes[1];
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    bp[i][j][k] = bp[i - 1][j][k];
                    if(zeros <= j && ones <= k){
                        bp[i][j][k] = Math.max(bp[i - 1][j][k], bp[i - 1][j - zeros][k - ones] + 1);
                    }
                }
            }
        }
        return bp[length][m][n];
    }

    public int[] getZerosOnes(String str) {
        int[] arr = new int[2];
        int length = str.length();
        for (int i = 0; i < length; i++) {
            arr[str.charAt(i) - '0']++;
        }
        return arr;
    }
}
