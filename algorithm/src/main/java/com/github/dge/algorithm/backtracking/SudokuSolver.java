package com.github.dge.algorithm.backtracking;

import java.util.Arrays;

/**
 * @author dge
 * @version 1.0
 * @date 2022-04-12 9:30 上午
 * https://leetcode.com/problems/sudoku-solver/description/
 * https://leetcode-cn.com/problems/sudoku-solver/description/
 * 解数独
 */
public class SudokuSolver {

    public static void main(String[] args) {
        SudokuSolver sudokuSolver = new SudokuSolver();
        char[][] board = {{'.','.','.','2','.','.','.','6','3'},
                {'3','.','.','.','.','5','4','.','1'},
                {'.','.','1','.','.','3','9','8','.'},
                {'.','.','.','.','.','.','.','9','.'},
                {'.','.','.','5','3','8','.','.','.'},
                {'.','3','.','.','.','.','.','.','.'},
                {'.','2','6','3','.','.','5','.','.'},
                {'5','.','3','7','.','.','.','.','8'},
                {'4','7','.','.','.','1','.','.','.'}};
        sudokuSolver.solveSudoku(board);
        System.out.println("====");
            for (char[] chars : board) {
                System.out.println(Arrays.toString(chars));
            }
        System.out.println("====");
        sudokuSolver.checkThreeThree(board, 8, 1, '1');
    }

    private char[] intChar = {'1','2','3','4','5','6','7','8','9'};

    public void solveSudoku(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        backTracking(board, m, n, 0, 0);
    }

    private boolean backTracking(char[][] board, int m, int n, int cm, int cn){
        if(cm == m && cn == 0){
            return true;
        }
        for (int i = cm; i < m; i++) {
            for (int j = cn; j < n; j++) {
                if(board[i][j] == '.'){
                    for (char c : intChar) {
                        if(checkRow(board, i, n, c) && checkList(board, m, j, c) && checkThreeThree(board, i, j, c)){
                            board[i][j] = c;
                            int nm = i;
                            int nn = j + 1;
                            if(j + 1 == n){
                                nm = i + 1;
                                nn = 0;
                            }
                            boolean b = backTracking(board, m, n, nm, nn);
                            if(b){
                                return b;
                            }
                            board[i][j] = '.';
                            if(!b){
                                continue;
                            }
                        }
                    }
                    if(board[i][j] == '.'){
                        return false;
                    }
                }
                if(board[i][j] != '.' && j == n - 1){
                    cm = cm + 1;
                    cn = 0;
                }
            }
        }
        return true;
    }

    private boolean checkRow(char[][] board, int cm, int n, char cc) {
        for (int i = 0; i < n; i++) {
            if(board[cm][i] == cc){
                return false;
            }
        }
        return true;
    }

    private boolean checkList(char[][] board, int m, int cn, char cc) {
        for (int i = 0; i < m; i++) {
            if(board[i][cn] == cc){
                return false;
            }
        }
        return true;
    }

    private boolean checkThreeThree(char[][] board, int cm, int cn, char cc){
        int startM = (cm / 3) * 3;
        int endM = startM + 2;
        int startN = (cn / 3) * 3;
        int endN = startN + 2;
        for (int i = startM; i <= endM; i++) {
            for (int j = startN; j <= endN; j++) {
                if(board[i][j] == cc){
                    return false;
                }
            }
        }
        return true;
    }
}
