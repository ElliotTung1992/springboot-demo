package com.github.dge.algorithm.depthfirstsearch;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author dge
 * @version 1.0
 * @date 2022-03-29 4:17 下午
 * https://leetcode.com/problems/number-of-provinces/description/
 * https://leetcode-cn.com/problems/number-of-provinces/
 * 省份数量
 */
public class NumberOfProvinces {

    public static void main(String[] args) {
        NumberOfProvinces numberOfProvinces = new NumberOfProvinces();
        int[][] isConnected = {{1,1,0},{1,1,0},{0,0,1}};
        int circleNum = numberOfProvinces.findCircleNum(isConnected);
        System.out.println(circleNum);
    }

    /**
     * 核心思想：
     * BFS
     */
    public int findCircleNum(int[][] isConnected) {
        int length = isConnected.length;
        int count = 0;
        boolean[] market = new boolean[length];
        for (int i = 0; i < length; i++) {
            if(!market[i]){
                market[i] = true;
                count++;
                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                while (!queue.isEmpty()){
                    int size = queue.size();
                    while (size-- > 0){
                        Integer n = queue.poll();
                        for (int j = 0; j < length; j++) {
                            if(!market[j] && isConnected[n][j] == 1){
                                market[j] = true;
                                queue.add(j);
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    /**
     * 核心思想：
     * DFS
     */
    public int findCircleNum2(int[][] isConnected) {
        int length = isConnected.length;
        int count = 0;
        boolean[] market = new boolean[length];
        for (int i = 0; i < length; i++) {
            if(!market[i]){
                dsf(isConnected, market, i);
                count++;
            }
        }
        return count;
    }

    private void dsf(int[][] isConnected, boolean[] market, int m){
        for (int i = 0; i < isConnected.length; i++) {
            if(isConnected[m][i] == 1 && !market[i]){
                market[i] = true;
                dsf(isConnected, market, i);
            }
        }
    }

}
