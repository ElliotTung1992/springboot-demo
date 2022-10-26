package com.github.dge.datastructure.hashtable;

import java.util.*;

/**
 * https://leetcode.com/problems/two-sum/description/
 * https://leetcode.cn/problems/two-sum/description/
 * 1. 两数之和
 */
public class TwoSum {

    public static void main(String[] args) {

    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(map.get(target - nums[i]) != null){
                int[] result = new int[2];
                result[0] = i;
                result[1] = map.get(target - nums[i]);
                return result;
            }
            map.put(nums[i], i);
        }
        return null;
    }

    /*public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        HashMap<Integer, List<Integer>> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(hashMap.containsKey(nums[i])){
                List<Integer> list = hashMap.get(nums[i]);
                list.add(i);
                hashMap.put(nums[i], list);
            }else{
                List<Integer> list = new ArrayList<>();
                list.add(i);
                hashMap.put(nums[i], list);
            }
        }
        for (Map.Entry<Integer, List<Integer>> entry : hashMap.entrySet()) {
            List<Integer> valueList = entry.getValue();
            List<Integer> list = hashMap.get(target - entry.getKey());
            if(list != null){
                result[0] = valueList.get(0);
                if(list.size() == 1){
                    result[1] = list.get(0);
                }else{
                    result[1] = list.get(1);
                }
                break;
            }
        }
        return result;
    }*/
}
