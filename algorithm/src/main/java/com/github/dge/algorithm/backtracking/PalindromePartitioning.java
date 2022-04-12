package com.github.dge.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dge
 * @version 1.0
 * @date 2022-04-11 1:11 下午
 * https://leetcode.com/problems/palindrome-partitioning/description/
 * https://leetcode-cn.com/problems/palindrome-partitioning/description/
 * 分割回文串
 */
public class PalindromePartitioning {

    public static void main(String[] args) {
        PalindromePartitioning palindromePartitioning = new PalindromePartitioning();
        List<List<String>> list = palindromePartitioning.partition("aab");
        System.out.println(list);
    }

    public List<List<String>> partition(String s) {
        List<List<String>> list = new ArrayList<>();
        List<String> rList = new ArrayList<>();
        int length = s.length();
        backTrack(list, rList, s, length, 0);
        return list;
    }

    private void backTrack(List<List<String>> list, List<String> rList, String s, int length, int sIndex){
        if(sIndex == length){
            list.add(new ArrayList<>(rList));
        }
        for (int i = sIndex; i < length; i++) {
            String substring = s.substring(sIndex, i + 1);
            boolean b = true;
            for (int j = 0; j < substring.length(); j++) {
                if(substring.charAt(j) != substring.charAt(substring.length() - j - 1)){
                    b = false;
                }
            }
            if(b){
                rList.add(substring);
                backTrack(list, rList, s, length, i + 1);
                rList.remove(rList.size() - 1);
            }
        }
    }


}
