package com.github.dge.algorithm.dynamicprogramming;

/**
 * @author dge
 * @version 1.0
 * @date 2022-04-16 9:08 下午
 * https://leetcode.com/problems/house-robber-ii/description/
 * https://leetcode-cn.com/problems/house-robber-ii/description/
 * 打家劫舍 II
 */
public class HouseRobberII {

    public static void main(String[] args) {
        HouseRobberII houseRobber = new HouseRobberII();
        int[] nums = {6,3,10,8,2,10,3,5,10,5,3};
        int rob = houseRobber.rob(nums);
        System.out.println(rob);
    }

    public int rob(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        } else if (length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(robRange(nums, 0, length - 2), robRange(nums, 1, length - 1));
    }

    public int robRange(int[] nums, int start, int end) {
        int first = nums[start], second = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }

    public int rob2(int[] nums) {
        int max = 0;
        int length = nums.length;
        if(length < 4){
            for (int i = 0; i < length; i++) {
                max = Math.max(max, nums[i]);
            }
            return max;
        }
        for (int i = 0; i < length; i++) {
            int temp = nums[i];
            int[] tempNums = new int[length - 3];
            int preIndex = i - 1 >= 0 ? i - 1: length - 1;
            int nextIndex = i + 1 < length ? i + 1 : 0;
            int tempIndex = 0;
            if(nextIndex < length - 1 && nextIndex != 0){
                for (int j = nextIndex + 1; j < length; j++) {
                    if(j == preIndex){
                        continue;
                    }
                    tempNums[tempIndex] =  nums[j];
                    tempIndex++;
                }
            }
            if(preIndex > 0 && preIndex != length - 1){
                for (int j = 0; j < preIndex; j++) {
                    if(j == nextIndex){
                        continue;
                    }
                    tempNums[tempIndex] =  nums[j];
                    tempIndex++;
                }
            }
            temp += rob2(tempNums, length - 3);
            max = Math.max(max, temp);
        }
        return max;
    }

    private int rob2(int[] tempNums, int length){
        int sum = 0, max = 0;
        for (int i = 0; i < length; i++) {
            int temp = max;
            max = Math.max(tempNums[i] + sum, max);
            sum = temp;
        }
        return max;
    }
}
