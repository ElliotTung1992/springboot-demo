package com.github.dge.algorithm.depthfirstsearch;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dge
 * @version 1.0
 * @date 2022-03-31 1:55 下午
 * https://leetcode.com/problems/pacific-atlantic-water-flow/description/
 * https://leetcode-cn.com/problems/pacific-atlantic-water-flow/description/
 * 太平洋大西洋水流问题
 */
public class PacificAtlanticWaterFlow {

    public static void main(String[] args) {
        PacificAtlanticWaterFlow pacificAtlanticWaterFlow = new PacificAtlanticWaterFlow();
        int[][] heights = {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        List<List<Integer>> lists = pacificAtlanticWaterFlow.pacificAtlantic(heights);
        System.out.println(lists);
    }

    private int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
    private int m, n;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        m = heights.length;
        n = heights[0].length;
        boolean[][] pa = new boolean[m][n];
        boolean[][] at = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            dfs(heights, i, 0, pa);
            dfs(heights, i, n - 1, at);
        }
        for (int i = 0; i < n; i++) {
            dfs(heights, 0, i, pa);
            dfs(heights, m - 1, i, at);
        }
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(pa[i][j] && at[i][j]){
                    List rl = new ArrayList();
                    rl.add(i);
                    rl.add(j);
                    list.add(rl);
                }
            }
        }
        return list;
    }

    private void dfs(int[][] heights, int cm, int cn, boolean[][] oc){
        oc[cm][cn] = true;
        for (int[] direction : directions) {
            int nm = cm + direction[0];
            int nn = cn + direction[1];
            if(nm < 0 || nm >= m || nn < 0 || nn >= n){
                continue;
            }
            if(!oc[nm][nn] && heights[nm][nn] >= heights[cm][cn]){
                dfs(heights, nm, nn, oc);
            }
        }
    }
    


    public List<List<Integer>> pacificAtlantic2(int[][] heights) {
        m = heights.length;
        n = heights[0].length;
        List<List<Integer>> list = new ArrayList<>();
        boolean[] twoMarkets = new boolean[2];
        boolean[][] currentMarkets = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                currentMarkets[i][j] = true;
                dsf(heights, i, j, twoMarkets, currentMarkets);
                if(twoMarkets[0] && twoMarkets[1]){
                    List<Integer> rList= new ArrayList<>();
                    rList.add(i);
                    rList.add(j);
                    list.add(rList);
                }
                twoMarkets[0] = false;
                twoMarkets[1] = false;
                currentMarkets = new boolean[m][n];
            }
        }
        return list;
    }

    private void dsf(int[][] heights, int cm, int cn, boolean[] twoMarkets, boolean[][] currentMarkets){
        if((cm == 0 || cn == 0) && !twoMarkets[0]){
            twoMarkets[0] = true;
        }
        if((cn == n -1 || cm == m - 1) && !twoMarkets[1]){
            twoMarkets[1] = true;
        }
        for (int[] direction : directions) {
            int nm = cm + direction[0];
            int nn = cn + direction[1];
            if(nm < 0 || nm >= m || nn < 0 || nn >= n){
                continue;
            }
            if(heights[cm][cn] >= heights[nm][nn] && !currentMarkets[nm][nn]){
                currentMarkets[nm][nn] = true;
                dsf(heights, nm, nn, twoMarkets, currentMarkets);
            }
        }
    }

}
