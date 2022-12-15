package com.github.dge.everyday;

import java.util.*;

public class Sum3 {

    public static void main(String[] args) {
        Sum3 sum3 = new Sum3();
        int[] arr = {-1,0,1,2,-1,-4};
        List<List<Integer>> lists = sum3.threeSum(arr);
        System.out.println(lists);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        int length = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int first = 0; first < length; first++) {
            if(first > 0 && nums[first] == nums[first - 1]){
                continue;
            }
            int third = length - 1;
            int target = - nums[first];
            for (int second = first + 1; second < length; second++) {
                if(second > first + 1 && nums[second] == nums[second - 1]){
                    continue;
                }
                while (second < third && nums[second] + nums[third] > target){
                    third--;
                }
                if(second == third){
                    break;
                }
                if(nums[second] + nums[third] == target){
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    result.add(list);
                }
            }
        }
        return result;
    }
}
