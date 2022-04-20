package com.github.dge.algorithm.contest.date20220416;

/**
 * @author dge
 * @version 1.0
 * @date 2022-04-17 9:32 下午
 * https://leetcode-cn.com/problems/find-closest-number-to-zero/
 * 找到最接近 0 的数字
 */
public class FindClosestNumberToZero {

    public static void main(String[] args) {

    }

    public int findClosestNumber(int[] nums) {
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (Math.abs(nums[i]) < Math.abs(result) || (Math.abs(nums[i]) == Math.abs(result) && nums[i] > result)) {
                result = nums[i];
            }
        }
        return result;
    }
}
