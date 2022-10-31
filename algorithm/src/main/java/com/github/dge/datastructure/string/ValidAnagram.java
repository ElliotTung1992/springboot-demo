package com.github.dge.datastructure.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/valid-anagram/description/
 * https://leetcode.cn/problems/valid-anagram/description/
 * 242. 有效的字母异位词
 */
public class ValidAnagram {

    public static void main(String[] args) {
        ValidAnagram validAnagram = new ValidAnagram();
        validAnagram.isAnagram("anagram", "nagaram");
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> table = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            table.put(ch, table.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            table.put(ch, table.getOrDefault(ch, 0) - 1);
            if (table.get(ch) < 0) {
                return false;
            }
        }
        return true;
    }

    /*public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] table = new int[26];
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            table[t.charAt(i) - 'a']--;
            if (table[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }*/


    /*public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        byte[] bytes = s.getBytes();
        Arrays.sort(bytes);
        byte[] bytes1 = t.getBytes();
        Arrays.sort(bytes1);
        return Arrays.equals(bytes, bytes1);
    }*/

    /*public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        byte[] bytes = s.getBytes();
        Arrays.sort(bytes);
        byte[] bytes1 = t.getBytes();
        Arrays.sort(bytes1);
        for (int i = 0; i < s.length(); i++) {
            if(bytes[i] != bytes1[i]){
                return false;
            }
        }
        return true;
    }*/

    /*public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        byte[] bytes = s.getBytes();
        byte[] bytes1 = t.getBytes();
        for (byte aByte : bytes) {
            boolean find = false;
            for (int i = 0; i < bytes1.length; i++) {
                if(aByte == bytes1[i]){
                    bytes1[i] = -1;
                    find = true;
                    break;
                }
            }
            if(!find){
                return false;
            }
        }
        return true;
    }*/
}
