package com.github.dge.datastructure.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author dge
 * @version 1.0
 * @date 2022-09-16 2:00 PM
 * https://leetcode.com/problems/binary-tree-postorder-traversal/description/
 * https://leetcode.cn/problems/binary-tree-postorder-traversal/description/
 * 二叉树的后序遍历
 */
public class BinaryTreePostorderTraversal {

    public static void main(String[] args) {

    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == prev) {
                res.add(root.val);
                prev = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
        return res;
    }

    /*List<Integer> res = new ArrayList<>();

    public List<Integer> postorderTraversal(TreeNode root) {
        dfs(root);
        return res;
    }

    private void dfs(TreeNode treeNode){
        if(treeNode == null){
            return;
        }
        dfs(treeNode.left);
        dfs(treeNode.right);
        res.add(treeNode.val);
    }*/
}
