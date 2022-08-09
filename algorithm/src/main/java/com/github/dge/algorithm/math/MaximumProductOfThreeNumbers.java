package com.github.dge.algorithm.math;

import java.util.Arrays;

/**
 * @author dge
 * @version 1.0
 * @date 2022-08-09 5:00 PM
 */
public class MaximumProductOfThreeNumbers {

    public static void main(String[] args) {
        MaximumProductOfThreeNumbers maximumProductOfThreeNumbers = new MaximumProductOfThreeNumbers();
        // int[] nums = {1,2,3,4};
        int[] nums = {-8,-7,-2,10,20};
        //int[] nums = {-100,-98,-1,2,3,4};
        int i = maximumProductOfThreeNumbers.maximumProduct(nums);
        System.out.println(i);
    }

    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return Math.max(nums[0] * nums[1] * nums[n - 1], nums[n - 3] * nums[n - 2] * nums[n - 1]);
    }

    public int maximumProduct2(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < 3; i++) {
            int swap = nums[i];
            for (int j = i; j < length; j++) {
                if(nums[j] > swap){
                    swap = nums[j];
                    nums[j] = nums[i];
                    nums[i] = swap;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            int minSwap = nums[length - i - 1];
            for (int j = i + 3; j < length - i; j++) {
                if(nums[j] < minSwap){
                    minSwap = nums[j];
                    nums[j] = nums[length - 1 - i];
                    nums[length - 1 - i] = minSwap;

                }
            }
        }
        return Math.max(nums[0] * nums[1] * nums[2], nums[length - 1] * nums[length - 2] * nums[0]);
    }
}
