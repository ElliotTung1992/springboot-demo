package com.github.dge.datastructure.array;

public class ToeplitzMatrix {

    public static void main(String[] args) {
        ToeplitzMatrix toeplitzMatrix = new ToeplitzMatrix();
        //int[][] arr = {{1,2,3,4},{5,1,2,3},{9,5,1,2}};
        int[][] arr = {{11,74,7,93},{40,11,74,7}};
        //int[][] arr = {{65,98,57}};
        toeplitzMatrix.isToeplitzMatrix(arr);
    }

    public boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] != matrix[i - 1][j - 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    /*public boolean isToeplitzMatrix(int[][] matrix) {
        boolean result = true;
        int a = matrix.length, b = matrix[0].length;
        if(a == 1 || b == 1){
            return true;
        }
        for (int i = 0; i < a; i++) {
            int a1 = i, b1 = 0;
            int rr = matrix[a1][b1];
            while (a1 < a && b1 < b){
                if(matrix[a1][b1] != rr){
                    return false;
                }
                a1++;
                b1++;
            }
        }
        for (int i = 1; i < b; i++) {
            int a1 = 0, b1 = i;
            int rr = matrix[a1][b1];
            while (b1 < b && a1 < a){
                if(matrix[a1][b1] != rr){
                    return false;
                }
                a1++;
                b1++;
            }
        }
        return result;
    }*/
}
