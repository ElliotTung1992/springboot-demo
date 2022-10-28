package com.github.dge.datastructure.hashtable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/longest-harmonious-subsequence/description/
 * https://leetcode.cn/problems/longest-harmonious-subsequence/description/
 */
public class LongestHarmoniousSubsequence {

    public static void main(String[] args) {

    }

    public int findLHS(int[] nums) {
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.sort(nums);
        for (int num : nums) {
            if(map.containsKey(num)){
                map.put(num, map.get(num) + 1);
            }else{
                map.putIfAbsent(num, 1);
            }
            if(map.containsKey(num - 1)){
                count = Math.max((map.get(num) + map.get(num - 1)), count);
            }
        }
        return count;
    }
}
