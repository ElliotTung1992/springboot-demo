package com.github.dge.algorithm.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dge
 * @version 1.0
 * @date 2022-04-19 9:31 上午
 * https://leetcode.com/problems/range-sum-query-immutable/description/
 * https://leetcode-cn.com/problems/range-sum-query-immutable/description/
 * 区域和检索 - 数组不可变
 */
public class RangeSumQueryImmutable {

    public static void main(String[] args) {

    }

    /*int[] sums;

    public NumArray(int[] nums) {
        int n = nums.length;
        sums = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        return sums[j + 1] - sums[i];
    }*/

    /*int[] arr;

    public NumArray(int[] nums) {
        arr = nums;
    }

    public int sumRange(int left, int right) {
        int sum = 0;
        for (int i = left; i <= right; i++) {
            sum += arr[i];
        }
        return sum;
    }*/
}
