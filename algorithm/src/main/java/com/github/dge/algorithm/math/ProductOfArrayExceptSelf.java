package com.github.dge.algorithm.math;

import java.util.Arrays;

/**
 * @author dge
 * @version 1.0
 * @date 2022-08-08 4:48 PM
 * https://leetcode.com/problems/product-of-array-except-self/description/
 * https://leetcode.cn/problems/product-of-array-except-self/description/
 * 除自身以外数组的乘积
 */
public class ProductOfArrayExceptSelf {

    public static void main(String[] args) {
        ProductOfArrayExceptSelf productOfArrayExceptSelf = new ProductOfArrayExceptSelf();
        // int[] nums = {1,2,3,4};
        int[] nums = {-1,1,0,-3,3};
        int[] ints = productOfArrayExceptSelf.productExceptSelf(nums);
        System.out.println(Arrays.toString(ints));
    }

    public int[] productExceptSelf(int[] nums) {
        int length = nums.length, R = 1;
        int[] result = new int[length];
        result[0] = 1;
        for (int i = 1; i < length; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        for (int i = length - 1; i >= 0 ; i--) {
            result[i] = result[i] * R;
            R *= nums[i];
        }
        return result;
    }

    public int[] productExceptSelf3(int[] nums){
        int length = nums.length;
        int[] LEFT = new int[length];
        int[] RIGHT = new int[length];
        int[] result = new int[nums.length];
        LEFT[0] = nums[0];
        for (int i = 1; i < length; i++) {
            LEFT[i] = LEFT[i -1] * nums[i];
        }
        RIGHT[length - 1] = nums[length - 1];
        for (int i = length - 2; i > -1; i--) {
            RIGHT[i] = RIGHT[i + 1] * nums[i];
        }

        result[0] = RIGHT[1];
        result[length - 1] = LEFT[length - 2];
        for (int i = 1; i < length -1; i++) {
            result[i] = LEFT[i - 1] * RIGHT[i + 1];
        }
        return result;
    }

    public int[] productExceptSelf2(int[] nums) {
        int allProduct = 1, zeroCount = 0;
        for (int num : nums) {
            if(num == 0){
                zeroCount ++;
                continue;
            }
            allProduct *= num;
        }
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if(zeroCount > 0){
                result[i] = 0;
                if(zeroCount == 1 && nums[i] == 0){
                    result[i] = allProduct;
                }
                continue;
            }
            result[i] = allProduct / nums[i];
        }
        return result;
    }
}
