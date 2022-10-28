package com.github.dge.datastructure.hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/contains-duplicate/description/
 * https://leetcode.com/problems/contains-duplicate/description/
 */
public class ContainsDuplicate {

    public static void main(String[] args) {

    }

    public boolean containsDuplicate(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if(map.containsKey(num)){
                return true;
            }
            map.put(num, num);
        }
        return false;
    }
}
