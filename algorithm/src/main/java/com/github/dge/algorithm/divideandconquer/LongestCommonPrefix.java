package com.github.dge.algorithm.divideandconquer;

/**
 * @author dge
 * @version 1.0
 * @date 2022-03-18 2:59 下午
 * https://leetcode.com/problems/longest-common-prefix/
 * https://leetcode-cn.com/problems/longest-common-prefix/
 * 最长公共前缀
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        // String[] strs = {"flower","flow","flight"};
        // String[] strs = {"dog","racecar","car"};
        String[] strs = {"a"};
        LongestCommonPrefix longestCommonPrefix = new LongestCommonPrefix();
        String s = longestCommonPrefix.longestCommonPrefix(strs);
        System.out.println(s);
    }

    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0){
            return "";
        }
        String str = strs[0];
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            for (int j = 0; j < strs.length; j++) {
                if(i == strs[j].length() || c != strs[j].charAt(i)){
                    return str.substring(0, i);
                }
            }
        }
        return str;
    }

    /**
     * 核心思想：
     * 从左到右依次比较返回结果
     */
    public String longestCommonPrefix3(String[] strs) {
        String result = strs[0];
        for (int i = 1; i < strs.length; i++) {
            result = compare(result, strs[i]);
        }
        return result;
    }

    /**
     * 核心思想：
     * 核心思想就是分治思想
     *             a
     *       b            c
     *    d     e      f     g
     *  e   f g   h  i   j k   l
     * 从最底层的数据一次次比较最终得到a
     */
    public String longestCommonPrefix2(String[] strs) {
        if(strs == null || strs.length == 0){
            return "";
        }
        return longestCommonPrefixSection(strs, 0, strs.length - 1);
    }

    public String longestCommonPrefixSection(String[] strs, int start, int end){
        if(start == end){
            return strs[start];
        }else {
            int mid = start + (end - start) / 2;
            String left = longestCommonPrefixSection(strs, start, mid);
            String right = longestCommonPrefixSection(strs, mid + 1, end);
            return compare(left, right);
        }
    }

    private String compare(String left, String right) {
        int min = Math.min(left.length(), right.length());
        for (int i = 0; i < min; i++) {
            if(left.charAt(i) != right.charAt(i)){
                return left.substring(0, i);
            }
        }
        return left.substring(0, min);
    }
}
