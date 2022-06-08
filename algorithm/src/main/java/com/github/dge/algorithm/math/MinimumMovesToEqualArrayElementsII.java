package com.github.dge.algorithm.math;

import java.util.Arrays;

/**
 * @author dge
 * @version 1.0
 * @date 2022-06-02 2:35 下午
 * https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/description/
 * https://leetcode.cn/problems/minimum-moves-to-equal-array-elements-ii/description/
 * 最少移动次数使数组元素相等 II
 */
public class MinimumMovesToEqualArrayElementsII {

    public static void main(String[] args) {
        MinimumMovesToEqualArrayElementsII moves = new MinimumMovesToEqualArrayElementsII();
        int[] nums = {1, 0, 0, 8, 6};
        int i = moves.minMoves2(nums);
        System.out.println(i);
    }

    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int length = nums.length, avg = nums[length/ 2];
        int difference = 0;
        for (int num : nums) {
            difference += Math.abs(num - avg);
        }
        return difference;
    }
}
