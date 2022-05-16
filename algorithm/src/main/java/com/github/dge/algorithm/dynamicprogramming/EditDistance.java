package com.github.dge.algorithm.dynamicprogramming;

/**
 * @author dge
 * @version 1.0
 * @date 2022-05-16 9:31 上午
 * https://leetcode.com/problems/edit-distance/description/
 * https://leetcode.cn/problems/edit-distance/description/
 * 编辑距离
 */
public class EditDistance {

    public static void main(String[] args) {
        String word1 = "horse", word2 = "ros";
        EditDistance test = new EditDistance();
        int i = test.minDistance(word1, word2);
        System.out.println(i);
    }

    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        if (m * n == 0) {
            return m + n;
        }

        int[][] bp = new int[m + 1][n + 1];

        // init
        for (int i = 0; i <= m; i++) {
            bp[i][0] = i;
        }
        for (int i = 0; i <= n; i++) {
            bp[0][i] = i;
        }

        // option
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int left = bp[i - 1][j] + 1;
                int right = bp[i][j - 1] + 1;
                int update = bp[i - 1][j - 1];
                if(word1.charAt(i - 1) != word2.charAt(j - 1)){
                    update += 1;
                }
                bp[i][j] = Math.min(Math.min(left, right), update);
            }
        }
        return bp[m][n];
    }
}
