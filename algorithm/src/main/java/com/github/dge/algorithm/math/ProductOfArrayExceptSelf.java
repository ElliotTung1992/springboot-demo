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
        int[] nums = {-1,1,0,-3,3};
        int[] ints = productOfArrayExceptSelf.productExceptSelf(nums);
        System.out.println(Arrays.toString(ints));
    }

    public int[] productExceptSelf(int[] nums) {
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
