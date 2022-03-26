package com.github.dge.algorithm.depthfirstsearch;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author dge
 * @version 1.0
 * @date 2022-03-26 9:43 下午
 * https://leetcode.com/problems/max-area-of-island/description/
 * https://leetcode-cn.com/problems/max-area-of-island/description/
 * 岛屿的最大面积
 */
public class MaxAreaOfIsland {

    public static void main(String[] args) {
        int[][] grid = {{0,0,1,0,0,0,0,1,0,0,0,0,0},
                        {0,0,0,0,0,0,0,1,1,1,0,0,0},
                        {0,1,1,0,1,0,0,0,0,0,0,0,0},
                        {0,1,0,0,1,1,0,0,1,0,1,0,0},
                        {0,1,0,0,1,1,0,0,1,1,1,0,0},
                        {0,0,0,0,0,0,0,0,0,0,1,0,0},
                        {0,0,0,0,0,0,0,1,1,1,0,0,0},
                        {0,0,0,0,0,0,0,1,1,0,0,0,0}};
        //int[][] grid = {{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}};
        MaxAreaOfIsland maxAreaOfIsland = new MaxAreaOfIsland();
        int i = maxAreaOfIsland.maxAreaOfIsland(grid);
        System.out.println(i);
    }

    /**
     * 核心思想：
     * 求遇队列的最大值
     */
    public int maxAreaOfIsland(int[][] grid) {
        int[][] direction = {{-1, 0}, {1, 0}, {0, 1}};
        int m = grid.length;
        int n = grid[0].length;
        int maxCount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == 0){
                    continue;
                }else{
                    int count = 0;
                    Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
                    queue.add(new Pair<>(i, j));
                    grid[i][j] = 0;
                    while (!queue.isEmpty()){
                        int size = queue.size();
                        while (size-- > 0){
                            count++;
                            Pair<Integer, Integer> poll = queue.poll();
                            int cm = poll.getKey();
                            int cn = poll.getValue();
                            for (int[] ints : direction) {
                                int nm = cm + ints[0];
                                int nn = cn + ints[1];
                                if(nm < 0 || nm >= m || nn < 0 || nn >= n){
                                    continue;
                                }
                                if(grid[nm][nn] == 1){
                                    queue.add(new Pair<>(nm, nn));
                                    grid[nm][nn] = 0;
                                }
                            }
                        }
                    }
                    if(count > maxCount){
                        maxCount = count;
                    }
                }
            }
        }
        return maxCount;
    }
}
