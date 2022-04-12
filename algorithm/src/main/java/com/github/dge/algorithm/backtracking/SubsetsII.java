package com.github.dge.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dge
 * @version 1.0
 * @date 2022-04-11 9:25 上午
 * https://leetcode.com/problems/subsets-ii/description/
 * https://leetcode-cn.com/problems/subsets-ii/description/
 * 子集 II
 */
public class SubsetsII {

    public static void main(String[] args) {
        SubsetsII subsetsII = new SubsetsII();
        int[] nums = {4,4,4,1,4};
        List<List<Integer>> list = subsetsII.subsetsWithDup(nums);
        System.out.println(list);
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> rList = new ArrayList<>();
        Arrays.sort(nums);
        backTracking(list, rList, nums, nums.length, 0);
        return list;
    }

    private void backTracking(List<List<Integer>> list, List<Integer> rList, int[] nums, int length, int cIndex){
        if(cIndex - 1 <= length){
            list.add(new ArrayList<>(rList));
        }
        for (int i = cIndex; i < length; i++) {
            if(i - 1 >= 0 && nums[i] == nums[i - 1] && cIndex <= i - 1){
                continue;
            }
            rList.add(nums[i]);
            backTracking(list, rList, nums, length, i + 1);
            rList.remove(rList.size() - 1);
        }
    }
}
