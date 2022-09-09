package com.github.dge.datastructure.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dge
 * @version 1.0
 * @date 2022-09-09 10:23 PM
 * https://leetcode.com/problems/house-robber-iii/description/
 * https://leetcode.cn/problems/house-robber-iii/solution/da-jia-jie-she-iii-by-leetcode-solution/
 * 打家劫舍
 */
public class HouseRobber {

    public static void main(String[] args) {

    }

    public int rob(TreeNode root) {
        int[] arr = dfs(root);
        return Math.max(arr[0], arr[1]);
    }

    public int[] dfs(TreeNode node) {
        if(node == null){
            return new int[]{0, 0};
        }
        int[] l = dfs(node.left);
        int[] r = dfs(node.right);
        int checked = node.val + l[1] + r[1];
        int unChecked = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
        return new int[]{checked, unChecked};
    }

    /*Map<TreeNode, Integer> checked = new HashMap<>();
    Map<TreeNode, Integer> unChecked = new HashMap<>();

    public int rob(TreeNode root) {
        if(root == null){
            return 0;
        }
        dfs(root);
        return Math.max(checked.getOrDefault(root, 0), unChecked.getOrDefault(root, 0));
    }

    public void dfs(TreeNode node){
        if(node == null){
            return;
        }
        dfs(node.left);
        dfs(node.right);
        checked.put(node, node.val + unChecked.getOrDefault(node.left, 0) + unChecked.getOrDefault(node.right, 0));
        unChecked.put(node, Math.max(checked.getOrDefault(node.left, 0), unChecked.getOrDefault(node.left, 0)) +
                Math.max(checked.getOrDefault(node.right, 0), unChecked.getOrDefault(node.right,0)));
    }*/
}
