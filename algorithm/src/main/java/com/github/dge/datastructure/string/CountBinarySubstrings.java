package com.github.dge.datastructure.string;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/count-binary-substrings/description/
 * https://leetcode.cn/problems/count-binary-substrings/description/
 * 696. 计数二进制子串
 */
public class CountBinarySubstrings {

    public static void main(String[] args) {

    }

    public int countBinarySubstrings3(String s) {
        int ptr = 0, n = s.length(), last = 0, ans = 0;
        while (ptr < n) {
            char c = s.charAt(ptr);
            int count = 0;
            while (ptr < n && s.charAt(ptr) == c) {
                ++ptr;
                ++count;
            }
            ans += Math.min(count, last);
            last = count;
        }
        return ans;
    }

    public int countBinarySubstrings2(String s) {
        List<Integer> counts = new ArrayList<>();
        int ptr = 0, n = s.length();
        while (ptr < n) {
            char c = s.charAt(ptr);
            int count = 0;
            while (ptr < n && s.charAt(ptr) == c) {
                ++ptr;
                ++count;
            }
            counts.add(count);
        }
        int ans = 0;
        for (int i = 1; i < counts.size(); ++i) {
            ans += Math.min(counts.get(i), counts.get(i - 1));
        }
        return ans;
    }

    public int countBinarySubstrings(String s) {
        int result = 0;
        int per = 0;
        char perByte = '2';
        int cur = 0;
        char curNow = s.charAt(0);
        for (char c : s.toCharArray()) {
            if(c == curNow){
                cur++;
                if(cur <= per){
                    result++;
                }
            }else{
                perByte = curNow;
                per = cur;
                cur = 1;
                curNow = c;
                result++;
            }
        }
        return result;
    }
}
