package com.github.dge.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dge
 * @version 1.0
 * @date 2022-04-06 9:49 上午
 * https://leetcode.com/problems/combinations/description/
 * https://leetcode-cn.com/problems/combinations/description/
 * 组合
 */
public class Combinations {

    public static void main(String[] args) {
        Combinations combinations = new Combinations();
        List<List<Integer>> combine = combinations.combine(4, 2);
        System.out.println(combine);
    }

    List<Integer> temp = new ArrayList<>();
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        dfs(1, n, k);
        return ans;
    }

    public void dfs(int cur, int n, int k) {
        // 剪枝：temp 长度加上区间 [cur, n] 的长度小于 k，不可能构造出长度为 k 的 temp
        if (temp.size() + (n - cur + 1) < k) {
            return;
        }
        // 记录合法的答案
        if (temp.size() == k) {
            ans.add(new ArrayList<Integer>(temp));
            return;
        }
        // 考虑选择当前位置
        temp.add(cur);
        dfs(cur + 1, n, k);
        temp.remove(temp.size() - 1);
        // 考虑不选择当前位置
        dfs(cur + 1, n, k);
    }

    public List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> list = new ArrayList<>();
        backTrack(list, new ArrayList<>(), k, n);
        return list;
    }

    private void backTrack(List<List<Integer>> list, List<Integer> rl, int k, int n){
        if(rl.size() == k){
            list.add(new ArrayList<>(rl));
        }
        int start = 0;
        if(rl.size() > 0){
            start = rl.get(rl.size() - 1);
        }
        if(k - start > k - rl.size()){
            return;
        }
        for (int i = start; i <= n; i++) {
            if(i + 1 > n){
                return;
            }
            rl.add(i + 1);
            backTrack(list, rl, k, n);
            rl.remove(rl.size() - 1);
        }
    }
}
