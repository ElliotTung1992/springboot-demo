package com.github.dge.datastructure.array;

public class ReshapeTheMatrix {

    public static void main(String[] args) {

        ReshapeTheMatrix matrix = new ReshapeTheMatrix();
        int[][] mat = {{1,2,3,4,5,6,7,8},{9,10,11,12,13,14,15,16}};
        matrix.matrixReshape(mat, 4, 4);
    }

    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int x = mat.length;
        int y = mat[0].length;
        if(x * y != r * c){
            return mat;
        }
        int[][] result = new int[r][c];
        for (int i = 0; i < x * y; i++) {
            int i1 = mat[i / y][i % y];
            result[i / c][i % c] = i1;
        }
        return result;
    }
}
