package com.github.dge.algorithm.dynamicprogramming;

/**
 * @author dge
 * @version 1.0
 * @date 2022-05-13 9:45 上午
 * https://leetcode.com/problems/delete-operation-for-two-strings/description/
 * https://leetcode.cn/problems/delete-operation-for-two-strings/description/
 * 两个字符串的删除操作
 */
public class DeleteOperationForTwoStrings {

    public static void main(String[] args) {
        DeleteOperationForTwoStrings test = new DeleteOperationForTwoStrings();
        // String word1 = "intention", word2 = "execution";
        String word1 = "sea", word2 = "eat";
        int i = test.minDistance(word1, word2);
        System.out.println(i);
    }

    public int minDistance2(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        // int container
        int[][] bp = new int[m + 1][n + 1];
        // init data
        for (int i = 1; i <= m; i++) {
            bp[i][0] = i;
        }
        for (int i = 0; i <= n; i++) {
            bp[0][i] = i;
        }
        // fill
        for (int i = 1; i <= m; i++) {
            char c = word1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                // if equal bp[i][j] = bp[i - 1][j - 1]
                if(c == word2.charAt(j - 1)){
                    bp[i][j] = bp[i - 1][j - 1];
                }
                // if not equal
                else{
                    bp[i][j] = Math.min(bp[i - 1][j], bp[i][j - 1]) + 1;
                }
            }
        }
        return bp[m][n];
    }

    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            char c1 = word1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = word2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        int lcs = dp[m][n];
        return m - lcs + n - lcs;
    }

}
