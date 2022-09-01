package com.github.dge.datastructure.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dge
 * @version 1.0
 * @date 2022-09-01 7:59 PM
 * https://leetcode.cn/problems/path-sum-iii/
 * https://leetcode.com/problems/path-sum-iii/
 * 路径总和 III
 */
public class PathSumIII {

    public static void main(String[] args) {

    }

    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> prefix = new HashMap<>();
        prefix.put(0L, 1);
        return dfs(root, prefix, 0, targetSum);
    }

    public int dfs(TreeNode root, Map<Long, Integer> prefix, long curr, int targetSum) {
        if (root == null) {
            return 0;
        }

        int ret = 0;
        curr += root.val;

        ret = prefix.getOrDefault(curr - targetSum, 0);
        prefix.put(curr, prefix.getOrDefault(curr, 0) + 1);
        ret += dfs(root.left, prefix, curr, targetSum);
        ret += dfs(root.right, prefix, curr, targetSum);
        prefix.put(curr, prefix.getOrDefault(curr, 0) - 1);

        return ret;
    }

    /*public int pathSum(TreeNode root, int targetSum) {
        if(root == null){
            return 0;
        }
        int var = pathRoot(root, targetSum);
        var += pathSum(root.right, targetSum);
        var += pathSum(root.left, targetSum);
        return var;
    }

    public int pathRoot(TreeNode root, long targetSum) {
        int var = 0;
        if(root == null){
            return 0;
        }
        if(root.val == targetSum){
            var++;
        }
        var += pathRoot(root.left, targetSum - root.val);
        var += pathRoot(root.right, targetSum - root.val);
        return var;
    }*/
}
