package com.github.dge.algorithm.dynamicprogramming;

/**
 * @author dge
 * @version 1.0
 * @date 2022-04-17 9:02 下午
 * https://leetcode.com/problems/minimum-path-sum/description/
 * https://leetcode-cn.com/problems/minimum-path-sum/description/
 * 最小路径和
 */
public class MinimumPathSum {

    public static void main(String[] args) {
        MinimumPathSum minimumPathSum = new MinimumPathSum();
        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        int i = minimumPathSum.minPathSum(grid);
        System.out.println(i);
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] temp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // first row
                if(i == 0){
                    int pre = j == 0 ? 0: temp[0][j - 1];
                    temp[i][j] = pre + grid[i][j];
                    continue;
                }
                // first column
                if(j == 0){
                    temp[i][0] = temp[i - 1][0] + grid[i][0];
                    continue;
                }
                temp[i][j] = Math.min(temp[i - 1][j], temp[i][j - 1]) + grid[i][j];
            }
        }
        return temp[m - 1][n - 1];
    }


}
