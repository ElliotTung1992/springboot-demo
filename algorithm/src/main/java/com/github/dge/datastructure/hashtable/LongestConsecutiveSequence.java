package com.github.dge.datastructure.hashtable;

import java.util.*;

/**
 * https://leetcode.cn/problems/longest-consecutive-sequence/description/
 * https://leetcode.com/problems/longest-consecutive-sequence/description/
 * 最长连续序列
 */
public class LongestConsecutiveSequence {

    public static void main(String[] args) {
        LongestConsecutiveSequence longestConsecutiveSequence = new LongestConsecutiveSequence();
        int[] arr = new int[]{100,4,200,1,3,2};
        int i = longestConsecutiveSequence.longestConsecutive2(arr);
        System.out.println(i);
    }

    public int longestConsecutive2(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }

    public int longestConsecutive(int[] nums) {
        if(Objects.isNull(nums) || nums.length == 0){
            return 0;
        }
        Map<Integer, int[]> map = new HashMap<>();
        int maxCount = 1;
        for (int num : nums) {
            if(map.containsKey(num)){
                continue;
            }
            int current = num;
            map.put(num, new int[]{num, num});
            while (map.containsKey(current + 1)){
                int[] ints = map.get(current);
                int[] ints2 = map.get(current + 1);
                int min = ints[0];
                int max = ints2[1];
                int[] high = new int[]{min, max};
                map.put(min, high);
                map.put(max, high);
                maxCount = Math.max(maxCount, max - min + 1);
                current = max;
            }
            int current2 = num;
            while (map.containsKey(current2 - 1)){
                int[] ints = map.get(current2 - 1);
                int min = ints[0];
                int max = map.get(current2)[1];
                int[] low = new int[]{min, max};
                map.put(min, low);
                map.put(max, low);
                maxCount = Math.max(maxCount, max - min + 1);
                current2 = min;
            }
        }
        return maxCount;
    }

}
