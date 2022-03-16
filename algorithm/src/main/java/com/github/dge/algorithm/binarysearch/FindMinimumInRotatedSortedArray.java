package com.github.dge.algorithm.binarysearch;

/**
 * @author dge
 * @version 1.0
 * @date 2022-03-16 9:32 上午
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/description/
 * 寻找旋转排序数组中的最小值
 */
public class FindMinimumInRotatedSortedArray {

    public static void main(String[] args) {
        //int[] nums = {3,4,5,1,2};
        //int[] nums = {11,13,15,17};
        //int[] nums = {4,5,6,7,0,1,2};
        int[] nums = {1};
        FindMinimumInRotatedSortedArray array = new FindMinimumInRotatedSortedArray();
        int min = array.findMin(nums);
        System.out.println(min);
    }

    /**
     * 核心思想：
     * if arr[0] <= arr[arr.length - 1]
     * 有序数组 return arr[0]
     * 然后采用二分查法查询数据
     * mid = start + (end - start)/2;
     * 如果值小于左边的值则命中
     * arr[mid] < arr[mid-1]
     * return mid
     * 如果arr[mid] > arr[arr.length - 1]
     * 则start = mid + 1
     * 如果arr[mid] < arr[0]
     * 则end = mid
     */
    public int findMin2(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        int mid = 0;
        int rightInt = nums[nums.length -1];
        int leftValue = nums[0];
        if(leftValue <= rightInt){
            return leftValue;
        }
        while (end >= start){
            mid = start + (end - start) / 2;
            int left = mid - 1 < 0 ? nums.length - 1: mid - 1;
            if(nums[mid] < nums[left]){
                return nums[mid];
            }
            if(nums[mid] > rightInt){
                start = mid + 1;
            }
            if(nums[mid] < leftValue){
                end = mid;
            }
        }
        return nums[mid];
    }

    /**
     * 更加精简
     * 剔除法无需考虑边界反而简单
     */
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int pivot = low + (high - low) / 2;
            if (nums[pivot] < nums[high]) {
                high = pivot;
            } else {
                low = pivot + 1;
            }
        }
        return nums[low];
    }
}
