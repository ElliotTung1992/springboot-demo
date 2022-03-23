package com.github.dge.algorithm.breadthfirstsearch;

import javafx.util.Pair;

import java.util.*;

/**
 * @author dge
 * @version 1.0
 * @date 2022-03-23 9:36 上午
 * https://leetcode.com/problems/shortest-path-in-binary-matrix/
 * https://leetcode-cn.com/problems/shortest-path-in-binary-matrix/
 * 二进制矩阵中的最短路径
 */
public class ShortestPathInBinaryMatrix {

    public static void main(String[] args) {
        ShortestPathInBinaryMatrix shortestPathInBinaryMatrix = new ShortestPathInBinaryMatrix();
        //int[][]grid = {{0,1},{1,0}};
        //int[][]grid = {{0,0,0},{1,1,0},{1,1,0}};
        //int[][]grid = {{0,0,0},{0,1,0},{0,0,0}};
        //int[][]grid = {{0,1,1,1},{1,0,1,1},{0,1,1,1},{1,0,0,0}};
        int[][]grid = {{0,1,1,0,0,0},{0,1,0,1,1,0},{0,1,1,0,1,0},{0,0,0,1,1,0},{1,1,1,1,1,0},{1,1,1,1,1,0}};
        int i = shortestPathInBinaryMatrix.shortestPathBinaryMatrix(grid);
        System.out.println(i);
    }

    public int shortestPathBinaryMatrix(int[][] grids) {
        if(grids == null || grids.length == 0 || grids[0].length == 0){
            return -1;
        }
        int[][] directions = {{-1, 1}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}};
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(0, 0));
        int deapLength = 0;
        int m = grids.length, n = grids[0].length;
        while (queue.size() > 0){
            deapLength ++;
            int size = queue.size();
            while (size-- > 0){
                Pair<Integer, Integer> poll = queue.poll();
                int cx = poll.getKey();
                int cy = poll.getValue();
                if(grids[cx][cy] == 1){
                    continue;
                }
                if(cx == m -1 && cy == n -1){
                    return deapLength;
                }
                grids[cx][cy] = 1;
                for (int[] direction : directions) {
                    int nx = cx + direction[0];
                    int ny = cy + direction[1];
                    if(nx < 0 || nx >= m || ny < 0 || ny >= n){
                        continue;
                    }
                    queue.add(new Pair<>(nx, ny));
                }
            }
        }
        return -1;
    }

    /**
     * 无法解决套娃
     */
    public int shortestPathBinaryMatrix2(int[][] grid) {
        // 校验起点和终点
        int start = grid[0][0];
        int[] ints = grid[grid.length - 1];
        int end = ints[ints.length - 1];
        if(0 != start || 0 != end){
            return -1;
        }
        int min = shortestPathBinaryMatrix(grid, 0, 0, grid.length - 1, ints.length - 1, -1, -1);
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public int shortestPathBinaryMatrix(int[][] grid, int x, int y, int endX, int endY, int startX, int startY){
        if(x == endX && y == endY){
            return 1;
        }
        if(x < 0 || y < 0 || x > endX || y > endY || grid[x][y] == 1){
            return Integer.MAX_VALUE;
        }
        int result = Integer.MAX_VALUE;
        // right
        if(isAllow(x, y, startX, startY, x, y + 1)){
            int right = shortestPathBinaryMatrix(grid, x, y + 1, endX, endY, x, y);
            result = Math.min(result, right);
        }
        // down
        if(isAllow(x, y, startX, startY, x + 1, y)){
            int down = shortestPathBinaryMatrix(grid, x + 1, y, endX, endY, x, y);
            result = Math.min(result, down);
        }
        // rightDown
        if(isAllow(x, y, startX, startY, x + 1, y + 1)){
            int rightDown = shortestPathBinaryMatrix(grid, x + 1, y + 1, endX, endY, x, y);
            result = Math.min(result, rightDown);
        }
        // leftDown
        if(isAllow(x, y, startX, startY, x - 1, y + 1)){
            int leftDown = shortestPathBinaryMatrix(grid, x - 1, y + 1, endX, endY, x, y);
            result = Math.min(result, leftDown);
        }
        // rightUp
        if(isAllow(x, y, startX, startY, x + 1, y - 1)){
            int rightUp = shortestPathBinaryMatrix(grid, x + 1, y - 1, endX, endY, x, y);
            result = Math.min(result, rightUp);
        }
        // left
        if(isAllow(x, y, startX, startY, x - 1, y)){
            int left = shortestPathBinaryMatrix(grid, x - 1, y, endX, endY, x, y);
            result = Math.min(result, left);
        }
        // leftUp
        if(isAllow(x, y, startX, startY, x - 1, y - 1)){
            int leftUp = shortestPathBinaryMatrix(grid, x - 1, y - 1, endX, endY, x, y);
            result = Math.min(result, leftUp);
        }
        // up
        if(isAllow(x, y, startX, startY, x, y - 1)){
            int up = shortestPathBinaryMatrix(grid, x, y - 1, endX, endY, x, y);
            result = Math.min(result, up);
        }
        return result == Integer.MAX_VALUE ? result: result + 1;
    }

    private boolean  isAllow(int x, int y, int startX, int startY, int nextX, int nextY){
        for (int i = startX - 1; i <= startX + 1; i++) {
            for (int j = startY - 1; j <= startY + 1; j++) {
                if(nextX == i && nextY == j){
                    return false;
                }
            }
        }
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if(nextX == i && nextY == j){
                    return true;
                }
            }
        }
        return false;
    }
}
