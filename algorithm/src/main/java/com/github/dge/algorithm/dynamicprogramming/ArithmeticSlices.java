package com.github.dge.algorithm.dynamicprogramming;

/**
 * @author dge
 * @version 1.0
 * @date 2022-04-20 9:35 上午
 * https://leetcode.com/problems/arithmetic-slices/description/
 * https://leetcode-cn.com/problems/arithmetic-slices/description/
 * 等差数列划分
 */
public class ArithmeticSlices {

    public static void main(String[] args) {
        ArithmeticSlices arithmeticSlices = new ArithmeticSlices();
        int[] nums = {1,3,5,7,9};
        int i = arithmeticSlices.numberOfArithmeticSlices(nums);
        System.out.println(i);
    }

    public int numberOfArithmeticSlices(int[] nums) {
        int length = nums.length;
        int[] temp = new int[length - 1];
        for (int i = 0; i < length - 1; i++) {
            temp[i] = nums[i + 1] - nums[i];
        }
        int sum = 0;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 1; j < length - 1; j++) {
                if(i + j >= length - 1 || temp[i + j - 1] != temp[i + j]){
                    break;
                }
                sum++;
            }
        }
        return sum;
    }
}
