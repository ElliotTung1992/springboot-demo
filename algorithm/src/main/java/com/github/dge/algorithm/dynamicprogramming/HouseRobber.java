package com.github.dge.algorithm.dynamicprogramming;

/**
 * @author dge
 * @version 1.0
 * @date 2022-04-15 8:57 上午
 * https://leetcode.com/problems/house-robber/description/
 * https://leetcode-cn.com/problems/house-robber/description/
 * 打家劫舍
 */
public class HouseRobber {

    public static void main(String[] args) {
        HouseRobber houseRobber = new HouseRobber();
        int[] nums = {2,1,1,2};
        int rob = houseRobber.rob(nums);
        System.out.println(rob);
    }

    public int rob(int[] nums) {
        int sum = 0;
        int max = 0;
        int temp;
        for (int num : nums) {
            temp = max;
            max = Math.max(max, sum + num);
            sum = temp;
        }
        return max;
    }
}
