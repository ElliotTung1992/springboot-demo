package com.github.dge.algorithm.dynamicprogramming;

/**
 * @author dge
 * @version 1.0
 * @date 2022-04-26 11:04 上午
 * https://leetcode.com/problems/wiggle-subsequence/description/
 * https://leetcode-cn.com/problems/wiggle-subsequence/description/
 * 摆动序列
 */
public class WiggleSubsequence {

    public static void main(String[] args) {
        WiggleSubsequence wiggleSubsequence = new WiggleSubsequence();
        int[] nums = {1,17,5,10,13,15,10,5,16,8};
        int i = wiggleSubsequence.wiggleMaxLength(nums);
        System.out.println(i);
    }

    public int wiggleMaxLength(int[] nums) {
        int length = nums.length;
        if(length < 2){
            return length;
        }
        int up = 1, down = 1;
        for (int i = 1; i < length; i++) {
            if(nums[i] > nums[i - 1]){
                up = Math.max(up, down + 1);
            }
            if(nums[i] < nums[i - 1]){
                down = Math.max(up + 1, down);
            }
        }
        return Math.max(up, down);
    }

    public int wiggleMaxLength2(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int[] up = new int[n];
        int[] down = new int[n];
        up[0] = down[0] = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                up[i] = Math.max(up[i - 1], down[i - 1] + 1);
                down[i] = down[i - 1];
            } else if (nums[i] < nums[i - 1]) {
                up[i] = up[i - 1];
                down[i] = Math.max(up[i - 1] + 1, down[i - 1]);
            } else {
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }
        }
        return Math.max(up[n - 1], down[n - 1]);
    }

}
