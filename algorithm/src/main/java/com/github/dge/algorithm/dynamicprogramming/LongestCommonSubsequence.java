package com.github.dge.algorithm.dynamicprogramming;

/**
 * @author dge
 * @version 1.0
 * @date 2022-04-27 1:24 下午
 * https://leetcode.com/problems/longest-common-subsequence/
 * https://leetcode-cn.com/problems/longest-common-subsequence/
 * 最长公共子序列
 */
public class LongestCommonSubsequence {

    public static void main(String[] args) {
        LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence();
        // String text1 = "mhunuzqrkzsnidwbun", text2 = "szulspmhwpazoxijwbq";
        String text1 = "abcde", text2 = "ace";
        int i = longestCommonSubsequence.longestCommonSubsequence(text1, text2);
        System.out.println(i);
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] container = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            char c1 = text1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = text2.charAt(j - 1);
                if(c1 == c2){
                    container[i][j] = container[i - 1][j - 1] + 1;
                }else{
                    container[i][j] = Math.max(container[i][j - 1], container[i - 1][j]);
                }
            }
        }
        return container[m][n];
    }
}
