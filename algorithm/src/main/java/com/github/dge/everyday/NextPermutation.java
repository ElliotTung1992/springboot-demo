package com.github.dge.everyday;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NextPermutation {

    public static void main(String[] args) {
        NextPermutation nextPermutation = new NextPermutation();
        int[] nums = {2,3,1};
        nextPermutation.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void nextPermutation(int[] nums) {
        int length = nums.length;
        Boolean tag = true;
        for (int i = length - 2; i >= 0; i--) {
            if(nums[i] < nums[i + 1]){
                sort(nums, i, length);
                tag = false;
                break;
            }
        }
        if(tag){
            Arrays.sort(nums);
        }
    }

    private void sort(int[] nums, int x, int length){
        List<Integer> list = new ArrayList<>();
        list.add(nums[x]);
        Integer position = Integer.MAX_VALUE;
        for (int i = x + 1; i < length; i++) {
            if(nums[i] > nums[x]){
                position = Math.min(position, nums[i]);
            }
            list.add(nums[i]);
        }
        nums[x] = position;
        list.remove(position);
        list = list.stream().sorted().collect(Collectors.toList());
        for (int i = x + 1; i < length; i++) {
            nums[i] = list.remove(0);
        }
    }
}
