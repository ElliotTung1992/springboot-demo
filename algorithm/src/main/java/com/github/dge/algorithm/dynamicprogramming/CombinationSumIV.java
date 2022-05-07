package com.github.dge.algorithm.dynamicprogramming;

/**
 * @author dge
 * @version 1.0
 * @date 2022-05-07 9:31 上午
 * https://leetcode.com/problems/combination-sum-iv/description/
 * https://leetcode-cn.com/problems/combination-sum-iv/description/
 * 组合总和 Ⅳ
 */
public class CombinationSumIV {

    public static void main(String[] args) {
        CombinationSumIV combinationSumIV = new CombinationSumIV();
        int[] nums = {2,1,3};
        int target = 35;
        int i = combinationSumIV.combinationSum4(nums, target);
        System.out.println(i);
    }

    public int combinationSum4(int[] nums, int target) {
        int[] bp = new int[target + 1];
        for (int i = 1; i <= target; i++) {
            int length = nums.length;
            for (int j = 0; j < length; j++) {
                int num = nums[j];
                if(num <= i){
                    if(num == i){
                        bp[i] = bp[i] + 1;
                    }else if(bp[i - num] > 0){
                        bp[i] = bp[i - num] + bp[i];
                    }
                }
            }
        }
        return bp[target];
    }

    public int combinationSum5(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num <= i) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

    /*public int combinationSum4(int[] nums, int target) {
        backTracking(0, target, nums);
        return count;
    }

    private int count = 0;

    private void backTracking(int sum, int target, int[] nums){
        if(sum == target){
            count++;
            return;
        }
        for (int num : nums) {
            int nSum = sum + num;
            if(nSum <= target){
                backTracking(nSum, target, nums);
            }
        }
    }*/
}
