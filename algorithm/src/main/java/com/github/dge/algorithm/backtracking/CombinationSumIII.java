package com.github.dge.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dge
 * @version 1.0
 * @date 2022-04-09 9:17 下午
 * https://leetcode.com/problems/combination-sum-iii/description/
 * https://leetcode-cn.com/problems/combination-sum-iii/description/
 * 组合总和 III
 */
public class CombinationSumIII {

    public static void main(String[] args) {
        CombinationSumIII combinationSumIII = new CombinationSumIII();
        List<List<Integer>> list = combinationSumIII.combinationSum3(3, 9);
        System.out.println(list);
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> rList = new ArrayList<>();
        backTracking(list, rList, k, n, 1);
        return list;
    }

    private void backTracking(List<List<Integer>> list, List<Integer> rList, int k, int n, int cIndex){
        if(k == rList.size()){
            if(n == 0){
                list.add(new ArrayList<>(rList));
            }
            return;
        }
        for (int i = cIndex; i <= 9; i++) {
            int result = n - i;
            if(result >= 0){
                rList.add(i);
                backTracking(list, rList, k, result, i + 1);
                rList.remove(rList.size() - 1);
            }
        }
    }
}
