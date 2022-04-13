package com.github.dge.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dge
 * @version 1.0
 * @date 2022-04-13 8:35 上午
 * https://leetcode.com/problems/n-queens/description/
 * https://leetcode-cn.com/problems/n-queens/description/
 * N 皇后
 */
public class NQueens {

    public static void main(String[] args) {
        NQueens nQueens = new NQueens();
        List<List<String>> list = nQueens.solveNQueens(4);
        System.out.println(list);
    }

    int[][] market;
    int[][] directions = {{1,1},{-1,-1},{-1,1},{1,-1}};
    List<List<String>> list = null;

    public List<List<String>> solveNQueens(int n) {
        list = new ArrayList<>();
        market = new int[n][n];
        backTrack(0, n);
        return list;
    }

    private void backTrack(int cn, int n){
        if(cn == n){
            build();
            return;
        }
        int[] row = market[cn];
        for (int i = 0; i < row.length; i++) {
            if(row[i] == 0){
                row[i] = 1;
                forwardOrBack(cn, i, n, 1);
                backTrack(cn + 1, n);
                row[i] = row[i] - 1;
                forwardOrBack(cn, i, n, -1);
            }
        }
    }

    private void build(){
        List<String> rList = new ArrayList<>();
        for (int[] ints : market) {
            StringBuilder sb = new StringBuilder();
            for (int anInt : ints) {
                if(anInt == 1){
                    sb.append("Q");
                }else{
                    sb.append(".");
                }
            }
            rList.add(sb.toString());
        }
        list.add(rList);
    }

    private void forwardOrBack(int x, int y, int n, int q){
        for (int i = 0; i < n; i++) {
            if(i != x){
                market[i][y] = market[i][y] + q;
            }
            if(i != y){
                market[x][i] = market[x][i] + q;
            }
        }
        for (int[] direction : directions) {
            int x1 = direction[0];
            int y1 = direction[1];
            for (int i = 1; i < n; i++) {
                int cx = x + x1 * i;
                int cy = y + y1 * i;
                if(cx >= 0 && cx < n && cy >= 0 && cy < n){
                    market[cx][cy] = market[cx][cy] + q;
                }else{
                    break;
                }
            }
        }
    }
}
