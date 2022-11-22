package com.github.dge.datastructure.array;

public class MaxConsecutiveOnes {

    public static void main(String[] args) {

    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, cur = 0, length = nums.length;
        for (int i = 0; i < length; i++) {
            if(nums[i] == 1){
                cur ++;
            }else {
                max = Math.max(max, cur);
                cur = 0;
            }
        }
        return Math.max(max, cur);
    }
}
