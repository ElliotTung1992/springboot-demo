package com.github.dge.datastructure.string;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/isomorphic-strings/description/
 * https://leetcode.cn/problems/isomorphic-strings/description/
 * 同构字符串
 */
public class IsomorphicStrings {

    public static void main(String[] args) {
        IsomorphicStrings isomorphicStrings = new IsomorphicStrings();
        boolean isomorphic = isomorphicStrings.isIsomorphic("egg", "add");
        System.out.println(isomorphic);
    }

    public boolean isIsomorphic(String s, String t) {
        int length = s.length();
        if(t.length() != length){
            return false;
        }

        Map<Character, Character> map1 = new HashMap<>();
        Map<Character, Character> map2 = new HashMap<>();
        for (int i = 0; i < length; i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if(map1.get(c1) == null && map2.get(c2) == null){
                map1.put(c1, c2);
                map2.put(c2, c1);
                continue;
            }
            if(map1.get(c1) == null || map2.get(c2) == null || !map1.get(c1).equals(c2) || !map2.get(c2).equals(c1)){
                return false;
            }
        }
        return true;
    }

}
