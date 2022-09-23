package com.github.dge.datastructure.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author dge
 * @version 1.0
 * @date 2022-09-23 2:46 下午
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
 * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
 * 二叉搜索树的最近公共祖先
 */
public class LowestCommonAncestorOfBinarySearchTree {

    public static void main(String[] args) {

    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        TreeNode result = null;
        int min = p.val > q.val ? q.val: p.val;
        int max = p.val > q.val ? p.val: q.val;
        while (!stack.isEmpty()){
            TreeNode pop = stack.pop();
            int val = pop.val;
            if(val >= min && val <= max){
                result = pop;
            }
            if(val > max){
                stack.push(pop.left);
            }
            if(val < min){
                stack.push(pop.right);
            }
        }
        return result;
    }

    /*TreeNode result = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p.val > q.val ? q.val: p.val, p.val > q.val ? p.val: q.val);
        return result;
    }

    private void dfs(TreeNode treeNode, int p, int q){
        int val = treeNode.val;
        if(val >= p && val <= q){
            result = treeNode;
            return;
        }
        if(val > q){
            dfs(treeNode.left, p, q);
        }
        if(val < p){
            dfs(treeNode.right, p, q);
        }
    }*/
}
