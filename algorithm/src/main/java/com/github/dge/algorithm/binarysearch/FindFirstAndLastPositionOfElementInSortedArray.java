package com.github.dge.algorithm.binarysearch;

import java.util.Arrays;

/**
 * @author dge
 * @version 1.0
 * @date 2022-03-17 10:28 上午
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * 在排序数组中查找元素的第一个和最后一个位置
 */
public class FindFirstAndLastPositionOfElementInSortedArray {

    public static void main(String[] args) {
        FindFirstAndLastPositionOfElementInSortedArray sortedArray = new FindFirstAndLastPositionOfElementInSortedArray();
        int[] nums = {5,7,7,8,8,10};
        //int[] nums = {};
        int target = 0;
        int[] ints = sortedArray.searchRange(nums, target);
        System.out.println(Arrays.toString(ints));
    }

    /**
     * 核心思想：
     * 跟我一开始的二分查找一样
     * 通过其他渠道处理特殊情况
     */
    public int[] searchRange(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        }
        return new int[]{-1, -1};
    }

    public int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    /**
     * 核心思想：
     * 经典的二分查找
     * 找开始的index最常规的查找
     * 找最后的index只要反过来找第一个就可以
     * 思想相同
     */
    public int[] searchRange2(int[] nums, int target) {
        if(nums.length == 0){
            return new int[]{-1, -1};
        }
        int[] result = new int[2];
        result[0] = findFirst(nums, target);
        result[1] = findLast(nums, target);
        return result;
    }

    public int findFirst(int[] nums, int target){
        int start  = 0, end = nums.length -1;
        while (end > start){
            int mid = start + (end - start)/2;
            if(nums[mid] >= target){
                end = mid;
            }else{
                start = mid + 1;
            }
        }
        return nums[start] == target ? start: -1;
    }

    public int findLast(int[] nums, int target){
        int start = nums.length -1, end = 0;
        while (start > end){
            int mid = start - (start - end) / 2;
            if(nums[mid] <= target){
                end = mid;
            }else {
                start = mid - 1;
            }
        }
        return nums[start] == target ? start: -1;
    }
}
