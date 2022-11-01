package com.github.dge.datastructure.string;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/longest-palindrome/description/
 * https://leetcode.cn/problems/longest-palindrome/description/
 * 409. 最长回文串
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        LongestPalindrome longestPalindrome = new LongestPalindrome();
        int adam = longestPalindrome.longestPalindrome("adam");
        System.out.println(adam);
    }

    public int longestPalindrome2(String s) {
        int[] count = new int[128];
        int length = s.length();
        for (int i = 0; i < length; ++i) {
            char c = s.charAt(i);
            count[c]++;
        }

        int ans = 0;
        for (int v: count) {
            ans += v / 2 * 2;
            if (v % 2 == 1 && ans % 2 == 0) {
                ans++;
            }
        }
        return ans;
    }

    public int longestPalindrome(String s) {
        byte[] bytes = s.getBytes();
        Map<Byte, Integer> map = new HashMap<>();
        for (byte aByte : bytes) {
            if(map.containsKey(aByte)){
                map.put(aByte, map.get(aByte) + 1);
            }else {
                map.putIfAbsent(aByte, 1);
            }
        }
        int count = 0;
        boolean exit = false;
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            Integer value = entry.getValue();
            if(value % 2 == 0){
                count += value;
            }else{
                exit = true;
                count = count + value - 1;
            }
        }
        return exit ? count + 1: count;
    }
}
