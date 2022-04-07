package com.github.dge.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dge
 * @version 1.0
 * @date 2022-04-07 9:42 上午
 * https://leetcode.com/problems/combination-sum/description/
 * https://leetcode-cn.com/problems/combination-sum/description/
 * 组合总和
 */
public class CombinationSum {

    public static void main(String[] args) {
        CombinationSum combinationSum = new CombinationSum();
        int[] candidates = {2,3,6,7};
        int target = 7;
        List<List<Integer>> list = combinationSum.combinationSum(candidates, target);
        System.out.println(list);
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> combine = new ArrayList<Integer>();
        dfs(candidates, target, ans, combine, 0);
        return ans;
    }

    public void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
        if (idx == candidates.length) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<Integer>(combine));
            return;
        }
        // 直接跳过
        dfs(candidates, target, ans, combine, idx + 1);
        // 选择当前数
        if (target - candidates[idx] >= 0) {
            combine.add(candidates[idx]);
            dfs(candidates, target - candidates[idx], ans, combine, idx);
            combine.remove(combine.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> dataList = new ArrayList<>();
        int length = candidates.length;
        Arrays.sort(candidates);
        backTrack(list, dataList, candidates, length, target, 0, 0);
        return list;
    }

    private void backTrack(List<List<Integer>> list, List<Integer> dataList, int[] candidates, int length, int target,
                           int currentSum, int index){
        if(currentSum == target){
            list.add(new ArrayList<>(dataList));
        }
        for (int i = index; i < length; i++) {
            int data = candidates[i];
            if(currentSum + data > target){
                break;
            }
            dataList.add(data);
            backTrack(list, dataList, candidates, length, target, currentSum + data, i);
            dataList.remove(dataList.size() - 1);
        }
    }
}
