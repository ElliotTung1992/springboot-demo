package com.github.dge.algorithm.dynamicprogramming;

/**
 * @author dge
 * @version 1.0
 * @date 2022-04-24 9:39 上午
 * https://leetcode.com/problems/longest-increasing-subsequence/description/
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/description/
 * 最长递增子序列
 */
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        LongestIncreasingSubsequence longestIncreasingSubsequence = new LongestIncreasingSubsequence();
        int[] nums = {0,1,0,3,2,3};
        int i = longestIncreasingSubsequence.lengthOfLIS(nums);
        System.out.println(i);
    }

    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }

    public int lengthOfLIS2(int[] nums) {
        int length = nums.length;
        int[] container = new int[length];
        int max = 0;
        for (int i = 0; i < length; i++) {
            int innerMax = 1;
            for (int j = i - 1; j >= 0; j--) {
                if(nums[i] > nums[j]){
                    innerMax = Math.max(innerMax, container[j] + 1);
                }
            }
            container[i] = innerMax;
            max = Math.max(container[i], max);
        }
        return max;
    }
}
