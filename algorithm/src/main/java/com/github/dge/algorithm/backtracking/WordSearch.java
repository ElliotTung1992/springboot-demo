package com.github.dge.algorithm.backtracking;

/**
 * @author dge
 * @version 1.0
 * @date 2022-04-03 4:41 下午
 * https://leetcode.com/problems/word-search/description/
 * https://leetcode-cn.com/problems/word-search/description/
 * 单词搜索
 */
public class WordSearch {

    public static void main(String[] args) {
        WordSearch wordSearch = new WordSearch();
        char[][] board = {{'A','B','C','E'},{'S','F','E','S'},{'A','D','E','E'}};
        String word = "ABCESEEEFS";
        boolean exist = wordSearch.exist(board, word);
        System.out.println(exist);
    }

    int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
    int m,n;

    public boolean exist(char[][] board, String word) {
        m = board.length;
        n = board[0].length;
        int length = word.length();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(board[i][j] == word.charAt(0) && backTrack(board, word, i, j, 1, new boolean[m][n], length)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean backTrack(char[][] board, String word, int cm, int cn, int count, boolean[][] markets, int length){
        if(length == count){
            if(board[cm][cn] == word.charAt(count - 1)){
                return true;
            }
            return false;
        }else{
            if(board[cm][cn] == word.charAt(count - 1)){
                markets[cm][cn] = true;
                for (int[] direction : directions) {
                    int nm = cm + direction[0];
                    int nn = cn + direction[1];
                    if(nm < 0 || nm >= m || nn < 0 || nn >= n || markets[nm][nn]){
                        continue;
                    }
                    if(backTrack(board, word, nm, nn, count + 1, markets, length)){
                         return true;
                    }
                    markets[nm][nn] = false;
                }
            }
            return false;
        }
    }
}
