package com.github.dge.algorithm.dynamicprogramming;

import java.util.*;

/**
 * @author dge
 * @version 1.0
 * @date 2022-05-06 9:52 上午
 * https://leetcode.com/problems/word-break/description/
 * https://leetcode-cn.com/problems/word-break/description/
 * 单词拆分
 */
public class WordBreak {

    public static void main(String[] args) {
        WordBreak  wordBreak = new WordBreak();
        String s = "abcd";
        String[] arr = {"a","abc","b","cd"};
        List<String> wordDict = Arrays.asList(arr);
        boolean b = wordBreak.wordBreak(s, wordDict);
        System.out.println(b);
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public boolean wordBreak2(String s, List<String> wordDict) {
        int length = s.length();
        boolean[] bp = new boolean[length];
        // init
        for (String s1 : wordDict) {
            if(s.charAt(0) == s1.charAt(0)){
                int end = 0 + s1.length();
                if(end <= length && s.substring(0, end).equals(s1)){
                    bp[end - 1] = true;
                }
            }
        }
        for (int i = 0; i < length; i++) {
            if(bp[i]){
                int start = i + 1;
                if(start >= length){
                    break;
                }
                for (String s1 : wordDict) {
                    if(s.charAt(start) == s1.charAt(0)){
                        int end = start + s1.length();
                        if(end <= length && s.substring(start, end).equals(s1)){
                            bp[end - 1] = true;
                        }
                    }
                }
            }
        }
        return bp[length - 1];
    }
}
