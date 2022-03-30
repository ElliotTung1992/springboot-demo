package com.github.dge.algorithm.depthfirstsearch;

import java.util.Arrays;

/**
 * @author dge
 * @version 1.0
 * @date 2022-03-30 9:19 下午
 * https://leetcode.com/problems/surrounded-regions/description/
 * https://leetcode-cn.com/problems/surrounded-regions/description/
 * 被围绕的区域
 */
public class SurroundedRegions {

    public static void main(String[] args) {
        SurroundedRegions surroundedRegions = new SurroundedRegions();
        char[][] board = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        surroundedRegions.solve2(board);
        Arrays.toString(board);
    }

    int n, m;

    public void solve(char[][] board) {
        n = board.length;
        if (n == 0) {
            return;
        }
        m = board[0].length;
        for (int i = 0; i < n; i++) {
            dfs(board, i, 0);
            dfs(board, i, m - 1);
        }
        for (int i = 1; i < m - 1; i++) {
            dfs(board, 0, i);
            dfs(board, n - 1, i);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void dfs(char[][] board, int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= m || board[x][y] != 'O') {
            return;
        }
        board[x][y] = 'A';
        dfs(board, x + 1, y);
        dfs(board, x - 1, y);
        dfs(board, x, y + 1);
        dfs(board, x, y - 1);
    }

    int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};

    public void solve2(char[][] board) {
        m = board.length;
        n = board[0].length;
        boolean[][] market = new boolean[m][n];
        int[] rowM = {0, m - 1};
        int[] rowN = {0, n - 1};
        for (int i = 0; i < m; i++) {
            for (int j : rowN) {
                if(board[i][j] == 'O'){
                    dfs(board, market, i, j);
                }
            }
        }
        for (int i : rowM) {
            for (int j = 0; j < n; j++) {
                if(board[i][j] == 'O'){
                    dfs(board, market, i, j);
                }
            }
        }
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if(board[i][j] == 'O' && !market[i][j]){
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void dfs(char[][] board, boolean[][] market, int cm, int cn){
        market[cm][cn] = true;
        for (int[] direction : directions) {
            int nm = cm + direction[0];
            int nn = cn + direction[1];
            if(nm < 0 || nm >= m || nn < 0 || nn >= n || market[nm][nn]){
                continue;
            }
            if(board[nm][nn] == 'O'){
                dfs(board, market, nm, nn);
            }
        }
    }
}
