package com.github.dge.algorithm.dynamicprogramming;

import java.util.Arrays;

/**
 * @author dge
 * @version 1.0
 * @date 2022-04-29 9:53 上午
 * https://leetcode.com/problems/target-sum/description/
 * https://leetcode-cn.com/problems/target-sum/description/
 * 目标和
 */
public class TargetSum {

    public static void main(String[] args) {
        TargetSum targetSum = new TargetSum();
        int[] nums = {1};
        int target = 1;
        int targetSumWays = targetSum.findTargetSumWays(nums, target);
        System.out.println(targetSumWays);
    }

    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int diff = sum - target;
        if(diff < 0 || diff % 2 != 0){
            return 0;
        }
        int avg = diff / 2, n = nums.length;
        int[][] bp = new int[n + 1][avg + 1];
        bp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int m = nums[i - 1];
            for (int j = 0; j <= avg; j++) {
                bp[i][j] = bp[i - 1][j];
                if(j >= m){
                    bp[i][j] += bp[i - 1][j - m];
                }
            }
        }
        return bp[n][avg];
    }

    /*public int findTargetSumWays(int[] nums, int target) {
        backtrack(nums, target, 0, 0);
        return count;
    }

    public void backtrack(int[] nums, int target, int index, int sum) {
        if (index == nums.length) {
            if (sum == target) {
                count++;
            }
        } else {
            backtrack(nums, target, index + 1, sum + nums[index]);
            backtrack(nums, target, index + 1, sum - nums[index]);
        }
    }*/

    private int count;
    private int length;

    public int findTargetSumWays2(int[] nums, int target) {
        length = nums.length;
        backTracking(nums, 0, target, 0);
        return count;
    }

    private void backTracking(int[] nums, int sum, int target, int nIndex){
        if(nIndex == length){
            if(sum == target){
                count++;
            }
            return;
        }
        int num = nums[nIndex];
        backTracking(nums, sum + num, target, nIndex + 1);
        backTracking(nums, sum - num, target, nIndex + 1);
    }
}
