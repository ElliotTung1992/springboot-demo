package com.github.dge.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dge
 * @version 1.0
 * @date 2022-04-05 9:37 下午
 * https://leetcode.com/problems/permutations-ii/description/
 * https://leetcode-cn.com/problems/permutations-ii/description/
 * 全排列 II
 */
public class PermutationsII {

    public static void main(String[] args) {
        PermutationsII permutations = new PermutationsII();
        int[] nums = {1,2,1,2};
        List<List<Integer>> list = permutations.permuteUnique(nums);
        System.out.println(list);
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        int length = nums.length;
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> rList = new ArrayList<>();
        boolean[] market = new boolean[length];
        Arrays.sort(nums);
        backTrack(nums, list, rList, market, length, 0);
        return list;
    }

    private void backTrack(int[] nums, List<List<Integer>> list, List<Integer> rList, boolean[] market, int length, int count){
        if(length == count){
            list.add(new ArrayList<>(rList));
        }
        for (int i = 0; i < length; i++) {
            if(market[i] || (i > 0 && nums[i] == nums[i - 1] && !market[i - 1])){
                continue;
            }
            rList.add(nums[i]);
            market[i] = true;
            backTrack(nums, list, rList, market, length, count + 1);
            rList.remove(rList.size() - 1);
            market[i] = false;
        }
    }
}
