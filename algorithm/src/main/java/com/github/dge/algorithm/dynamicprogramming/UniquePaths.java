package com.github.dge.algorithm.dynamicprogramming;

/**
 * @author dge
 * @version 1.0
 * @date 2022-04-18 9:18 上午
 * https://leetcode.com/problems/unique-paths/description/
 * https://leetcode-cn.com/problems/unique-paths/description/
 * 不同路径
 */
public class UniquePaths {

    public static void main(String[] args) {
        UniquePaths  uniquePaths = new UniquePaths();
        int i = uniquePaths.uniquePaths(4, 4);
        System.out.println(i);
    }

    int[][] directions = {{0, -1}, {-1, 0}};

    public int uniquePaths(int m, int n) {
        if(m == 1 && n == 1){
            return 1;
        }
        int[][] temp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(i == 0 && j == 0){
                    temp[i][j] = 0;
                    continue;
                }
                if(i == 0 || j == 0){
                    temp[i][j] = 1;
                    continue;
                }
                int sum = 0;
                for (int[] direction : directions) {
                    sum += temp[i + direction[0]][j + direction[1]];
                }
                temp[i][j] = sum;
            }
        }
        return temp[m - 1][n - 1];
    }
}
