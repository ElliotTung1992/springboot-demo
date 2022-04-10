package com.github.dge.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dge
 * @version 1.0
 * @date 2022-04-10 8:45 下午
 * https://leetcode.com/problems/subsets/description/
 * https://leetcode-cn.com/problems/subsets/description/
 * 子集
 */
public class Subsets {

    public static void main(String[] args) {
        Subsets subsets = new Subsets();
        int[] nums = {1,2,3};
        List<List<Integer>> list = subsets.subsets(nums);
        System.out.println(list);
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> rList = new ArrayList<>();
        backTracking(nums, list, rList, nums.length, 0);
        return list;
    }

    private void backTracking(int[] nums, List<List<Integer>> list, List<Integer> rList, int length, int cIndex){
        if(cIndex - 1 <= length){
            list.add(new ArrayList<>(rList));
        }
        for (int i = cIndex; i < length; i++) {
            rList.add(nums[i]);
            backTracking(nums, list, rList, length, i + 1);
            rList.remove(rList.size() - 1);
        }
    }
}
