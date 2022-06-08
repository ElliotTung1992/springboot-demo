package com.github.dge.algorithm.math;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dge
 * @version 1.0
 * @date 2022-06-08 5:04 下午
 * https://leetcode.com/problems/majority-element/description/
 * https://leetcode.cn/problems/majority-element/description/
 * 多数元素
 */
public class MajorityElement {

    public static void main(String[] args) {
        MajorityElement majorityElement = new MajorityElement();
        //int[] nums = {3,2,3};
        int[] nums = {2,2,1,1,1,2,2};
        int i = majorityElement.majorityElement(nums);
        System.out.println(i);
    }

    public int majorityElementSort(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

    public int majorityElementMap(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(16);
        int middle = nums.length / 2;
        for (int num : nums) {
            Integer v = map.getOrDefault(num, 0) + 1;
            map.put(num, v);
            if(v > middle){
                return num;
            }
        }
        return -1;
    }

    public int majorityElementRandom(int[] nums) {
        int middle = nums.length / 2;
        while (true){
            int a = nums[(int) (Math.random() * nums.length)];
            int count = 0;
            for (int num : nums) {
                if(a == num){
                    count++;
                }
                if(count > middle){
                    return a;
                }
            }
        }
    }

    public int majorityElement2(int[] nums) {
        return findMajority(nums, 0, nums.length - 1);
    }

    private int findMajority(int[] nums, int l, int r) {
        if(l == r){
            return nums[l];
        }
        int middle = (r + l) / 2;
        int a = findMajority(nums, l, middle);
        int b = findMajority(nums, middle + 1, r);
        int count1 = 0, count2 = 0;
        for (int i = l; i <= r; i++) {
            if(nums[i] == a){
                count1++;
            }
            if(nums[i] == b){
                count2++;
            }
        }
        return count1 > count2 ? a : b;
    }

    public int majorityElement(int[] nums) {
        int candidate = nums[0], count = 0, length = nums.length;
        for (int i = 0; i < length; i++) {
            if(nums[i] == candidate){
                count++;
            }else{
                count--;
                if(count == 0){
                    candidate = nums[i + 1];
                }
            }
        }
        return candidate;
    }
}
