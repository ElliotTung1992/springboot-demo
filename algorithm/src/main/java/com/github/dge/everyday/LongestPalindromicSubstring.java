package com.github.dge.everyday;

public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        LongestPalindromicSubstring longestPalindromicSubstring = new LongestPalindromicSubstring();
        String str = longestPalindromicSubstring.longestPalindrome("bbbb");
        System.out.println(str);
    }

    public String longestPalindrome(String s) {
        String temp = String.valueOf(s.charAt(0));
        int length = s.length();
        for (int i = 0; i < length - 1; i++) {
            int left = i - 1, right = i + 1;

            while (right <= length - 1 && s.charAt(i) == s.charAt(right)){
                if(right - left + 1 > temp.length()){
                    temp = s.substring(left + 1, right + 1);
                }
                right++;
                i++;
            }

            while (left >= 0 && right <= length - 1 && s.charAt(left) == s.charAt(right)){
                if(right - left + 1 > temp.length()){
                    temp = s.substring(left, right + 1);
                }
                left--;
                right++;
            }
        }
        return temp;
    }
}
