package com.github.dge.datastructure.array;

import java.util.Arrays;

public class Search2DMatrixII {

    public static void main(String[] args) {

        //int[][] arr = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        int[][] arr = {{-5}};

        Search2DMatrixII search2DMatrixII = new Search2DMatrixII();
        boolean b = search2DMatrixII.searchMatrix(arr, -5);
        System.out.println(b);
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int y = matrix.length, x = matrix[0].length;
        int m = 0, n = x - 1;
        while (m < y && n >= 0){
            if(matrix[m][n] == target){
                return true;
            }else if(matrix[m][n] > target){
                n--;
            }else{
                m++;
            }
        }
        return false;
    }

    /*int y = -1;
    int x = -1;

    public boolean searchMatrix(int[][] matrix, int target) {
        y = matrix.length;
        x = matrix[0].length;
        boolean[][] arr = new boolean[y][x];
        return search(matrix, arr, target, 0, 0);
    }

    private boolean search(int[][] matrix, boolean[][] arr, int target, int curX, int curY){
        System.out.println(curX + "|" + curY);
        if(curX >= y || curY >= x || curX < 0 || curY < 0 || arr[curX][curY]){
            return false;
        }
        arr[curX][curY] = true;
        int cur = matrix[curX][curY];
        boolean a = false;
        boolean b = false;
        if(cur == target){
            return true;
        }else if(cur > target){
            // 左 上
            a = search(matrix, arr, target, curX - 1, curY) || search(matrix, arr, target, curX, curY - 1);
        }else if(cur < target){
            // 下 右
            b = search(matrix, arr, target, curX + 1, curY) || search(matrix, arr, target, curX, curY + 1);
        }
        return a || b;
    }*/
}
