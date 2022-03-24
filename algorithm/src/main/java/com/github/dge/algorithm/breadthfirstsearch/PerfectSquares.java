package com.github.dge.algorithm.breadthfirstsearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author dge
 * @version 1.0
 * @date 2022-03-23 10:25 下午
 * https://leetcode.com/problems/perfect-squares/description/
 * https://leetcode-cn.com/problems/perfect-squares/description/
 * 完全平方数
 */
public class PerfectSquares {

    public static void main(String[] args) {
        int n = 7168;
        //int n = 12;
        PerfectSquares perfectSquares = new PerfectSquares();
        int i = perfectSquares.numSquares(n);
        System.out.println(i);
    }

    /**
     * 核心思想：
     * 典型的BFS的解决方案
     * 这里的超时需要注意下：
     * 需要把已经的结果不重复处理
     * 如果不处理会超时
     */
    public int numSquares(int n) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] market = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            if(i * i > n){
                break;
            }
            if(i * i == n){
                return 1;
            }
            queue.add(i * i);
        }
        market[n] = true;
        int deapLength = 1;
        while (!queue.isEmpty()){
            int size = queue.size();
            deapLength ++;
            while (size -- > 0){
                Integer poll = queue.poll();
                for (int i = 0; i <= n; i++) {
                    int result = poll + i * i;
                    if(result > n){
                        break;
                    }
                    if(poll + i * i == n){
                        return deapLength;
                    }
                    if(true == market[result]){
                        continue;
                    }
                    market[result] = true;
                    queue.add(result);
                }
            }
        }
        return -1;
    }

    public int numSquares2(int n) {
        List<Integer> squares = generateSquares(n);
        Queue<Integer> queue = new LinkedList<>();
        boolean[] marked = new boolean[n + 1];
        queue.add(n);
        marked[n] = true;
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            while (size-- > 0) {
                int cur = queue.poll();
                for (int s : squares) {
                    int next = cur - s;
                    if (next < 0) {
                        break;
                    }
                    if (next == 0) {
                        return level;
                    }
                    if (marked[next]) {
                        continue;
                    }
                    marked[next] = true;
                    queue.add(next);
                }
            }
        }
        return n;
    }

    /**
     * 生成小于 n 的平方数序列
     * @return 1,4,9,...
     */
    private List<Integer> generateSquares(int n) {
        List<Integer> squares = new ArrayList<>();
        int square = 1;
        int diff = 3;
        while (square <= n) {
            squares.add(square);
            square += diff;
            diff += 2;
        }
        return squares;
    }
}
