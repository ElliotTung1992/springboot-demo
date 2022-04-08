package com.github.dge.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dge
 * @version 1.0
 * @date 2022-04-08 10:46 上午
 * https://leetcode.com/problems/combination-sum-ii/description/
 * https://leetcode-cn.com/problems/combination-sum-ii/description/
 * 组合总和 II
 */
public class CombinationSumII {

    public static void main(String[] args) {
        int[] candidates = {10,1,2,7,6,1,5};
        int target = 8;
        CombinationSumII  combinationSumII = new CombinationSumII();
        List<List<Integer>> list = combinationSumII.combinationSum22(candidates, target);
        System.out.println(list);
    }

    List<int[]> freq = new ArrayList<int[]>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    List<Integer> sequence = new ArrayList<Integer>();

    public List<List<Integer>> combinationSum22(int[] candidates, int target) {
        Arrays.sort(candidates);
        for (int num : candidates) {
            int size = freq.size();
            if (freq.isEmpty() || num != freq.get(size - 1)[0]) {
                freq.add(new int[]{num, 1});
            } else {
                ++freq.get(size - 1)[1];
            }
        }
        dfs(0, target);
        return ans;
    }

    public void dfs(int pos, int rest) {
        if (rest == 0) {
            ans.add(new ArrayList<Integer>(sequence));
            return;
        }
        if (pos == freq.size() || rest < freq.get(pos)[0]) {
            return;
        }

        dfs(pos + 1, rest);

        int most = Math.min(rest / freq.get(pos)[0], freq.get(pos)[1]);
        for (int i = 1; i <= most; ++i) {
            sequence.add(freq.get(pos)[0]);
            dfs(pos + 1, rest - i * freq.get(pos)[0]);
        }
        for (int i = 1; i <= most; ++i) {
            sequence.remove(sequence.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> resultList = new ArrayList<>();
        Arrays.sort(candidates);
        backTrack(list, resultList, candidates, target, 0, candidates.length);
        return list;
    }

    private void backTrack(List<List<Integer>> list, List<Integer> resultList, int[] candidates, int target, int index,
                           int length){
        if(target == 0){
            list.add(new ArrayList<>(resultList));
            return;
        }
        for (int i = index; i < length; i++) {
            if(i - 1 >= index && candidates[i - 1] == candidates[i]){
                continue;
            }
            int result = target - candidates[i];
            if(result >= 0){
                resultList.add(candidates[i]);
                backTrack(list, resultList, candidates, result, i + 1, length);
                resultList.remove(resultList.size() - 1);
            }
        }
    }
}
