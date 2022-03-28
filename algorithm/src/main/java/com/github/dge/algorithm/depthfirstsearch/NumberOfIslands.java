package com.github.dge.algorithm.depthfirstsearch;

/**
 * @author dge
 * @version 1.0
 * @date 2022-03-28 9:44 上午
 * https://leetcode.com/problems/number-of-islands/description/
 * https://leetcode-cn.com/problems/number-of-islands/description/
 * 岛屿数量
 */
public class NumberOfIslands {

    public static void main(String[] args) {
        char[][] grid = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
                };
        NumberOfIslands numberOfIslands = new NumberOfIslands();
        int i = numberOfIslands.numIslands(grid);
        System.out.println(i);
    }

    int[][] directions = {{-1,0}, {0,1}, {1,0}, {0,-1}};
    int m, n;

    /**
     * 核心思想：
     * DFS
     */
    public int numIslands(char[][] grid) {
        int count = 0;
        m = grid.length;
        n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == '1'){
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int cm, int cn) {
        if(cm < 0 || cm >= m || cn < 0 || cn >= n || grid[cm][cn] == '0'){
            return;
        }
        grid[cm][cn] = '0';
        for (int[] direction : directions) {
            dfs(grid, cm + direction[0], cn + direction[1]);
        }
    }
}
