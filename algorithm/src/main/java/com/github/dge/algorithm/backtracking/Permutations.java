package com.github.dge.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dge
 * @version 1.0
 * @date 2022-04-04 10:29 下午
 * https://leetcode.com/problems/permutations/description/
 * https://leetcode-cn.com/problems/permutations/description/
 * 全排列
 */
public class Permutations {

    public static void main(String[] args) {
        Permutations permutations = new Permutations();
        int[] nums = {1};
        List<List<Integer>> list = permutations.permute(nums);
        System.out.println(list);
    }

    public List<List<Integer>> permute(int[] nums) {
        int length = nums.length;
        boolean[] bArr = new boolean[length];
        List<List<Integer>> list = new ArrayList<>();
        if(nums.length == 1){
            List list1 = new ArrayList();
            list1.add(nums[0]);
            list.add(list1);
        }
        for (int i = 0; i < length; i++) {
            List<Integer> intList = new ArrayList<>();
            intList.add(nums[i]);
            bArr[i] = true;
            backTrack(nums, bArr, list, intList, length);
            bArr[i] = false;
        }
        return list;
    }

    private void backTrack(int[] nums, boolean[] bArr, List<List<Integer>> list, List<Integer> intList, int length){
        if(length == intList.size() + 1){
            for (int i = 0; i < length; i++) {
                if(!bArr[i]){
                    intList.add(nums[i]);
                    List rList = new ArrayList();
                    for (Integer integer : intList) {
                        rList.add(integer);
                    }
                    list.add(rList);
                    intList.remove(intList.size() - 1);
                }
            }
        }else{
            for (int i = 0; i < length; i++) {
                if(!bArr[i]){
                    intList.add(nums[i]);
                    bArr[i] = true;
                    backTrack(nums, bArr, list, intList, length);
                    bArr[i] = false;
                    intList.remove(intList.size() - 1);
                }
            }
        }
    }
}
