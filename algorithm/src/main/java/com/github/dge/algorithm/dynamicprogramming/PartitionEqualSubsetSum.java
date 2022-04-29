package com.github.dge.algorithm.dynamicprogramming;

/**
 * @author dge
 * @version 1.0
 * @date 2022-04-28 9:56 上午
 * https://leetcode.com/problems/partition-equal-subset-sum/description/
 * https://leetcode-cn.com/problems/partition-equal-subset-sum/description/
 * 分割等和子集
 */
public class PartitionEqualSubsetSum {

    public static void main(String[] args) {
        PartitionEqualSubsetSum partitionEqualSubsetSum = new PartitionEqualSubsetSum();
        int[] nums = {1,5,11,5};
        boolean b = partitionEqualSubsetSum.canPartition(nums);
        System.out.println(b);
    }

    public boolean canPartition(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (maxNum > target) {
            return false;
        }
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            for (int j = target; j >= num; --j) {
                dp[j] |= dp[j - num];
            }
        }
        return dp[target];
    }

    public boolean canPartition2(int[] nums) {
        int length = nums.length;
        if(length < 2){
            return false;
        }
        int amount = 0, max = 0;
        for (int num : nums) {
            amount += num;
            max = Math.max(max, num);
        }
        if(amount % 2 != 0){
            return false;
        }
        int target = amount / 2;
        if(max > target){
            return false;
        }else if(max == target){
            return true;
        }
        boolean[][] bp = new boolean[length][target + 1];
        // init date
        for (int i = 0; i < length; i++) {
            bp[i][0] = true;
        }
        bp[0][nums[0]] = true;
        for (int i = 1; i < length; i++) {
            int num = nums[i];
            for (int j = 1; j <= target; j++) {
                if(j >= num){
                    bp[i][j] = bp[i - 1][j] | bp[i - 1][j - num];
                }else{
                    bp[i][j] = bp[i - 1][j];
                }
                if(j == target && bp[i][j]){
                    return true;
                }
            }
        }
        return bp[length - 1][target];
    }
}
