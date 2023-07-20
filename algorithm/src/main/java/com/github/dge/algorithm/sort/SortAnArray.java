package com.github.dge.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

public class SortAnArray {

    public static void main(String[] args) {
        SortAnArray sortAnArray = new SortAnArray();
        int[] nums = {5,2,3,1,10,4,8,6,7,9};
        int[] ints = sortAnArray.sortArray(nums);
        System.out.println(Arrays.toString(ints));
    }

    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    // 分治
    private void quickSort(int[] nums, int l, int r) {
        if(l < r){
            // 返回上次基准下标
            int index = randomizedPartition(nums, l, r);
            quickSort(nums, l, index - 1);
            quickSort(nums, index + 1, r);
        }
    }

    private int randomizedPartition(int[] nums, int l, int r) {
        // 随机取个下标作为基准
        int random = new Random().nextInt(r - l + 1) + l;
        // 把基准放到区间最后
        swap(nums, r, random);
        return partition(nums, l, r);
    }

    // 双指针
    private int partition(int[] nums, int l, int r){
        // 获取基准值
        int norm = nums[r];
        // i: 小于等于基准值的下标
        // j: 当前下标
        int i = l - 1;
        for (int j = l; j <= r - 1; j++) {
            if(nums[j] <= norm){
                // 找到比基准值小的元素，移动i,并把值交换过去
                i += 1;
                swap(nums, i, j);
            }
        }
        // 把基准值移动到指针i右边
        swap(nums, r, i + 1);
        return i + 1;
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }



    /*public int[] sortArray(int[] nums) {

        int current;
        int length = nums.length;

        for (int i = 0; i < length; i++) {
            current = nums[i];
            for (int j = i + 1; j < length; j++) {
                if(nums[j] < current){
                    int temp = current;
                    current = nums[j];
                    nums[j] = temp;
                }
            }
            nums[i] = current;
        }

        return nums;
    }*/
}
