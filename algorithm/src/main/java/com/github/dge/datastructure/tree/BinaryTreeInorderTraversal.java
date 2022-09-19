package com.github.dge.datastructure.tree;

import java.util.*;

/**
 * @author dge
 * @version 1.0
 * @date 2022-09-19 3:01 下午
 * https://leetcode.com/problems/binary-tree-inorder-traversal/description/
 * https://leetcode.cn/problems/binary-tree-inorder-traversal/description/
 * 二叉树的中序遍历
 */
public class BinaryTreeInorderTraversal {

    public static void main(String[] args) {

    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()){
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            TreeNode pop = stack.pop();
            result.add(pop.val);
            root = pop.right;
        }
        return result;
    }

    /*List<Integer> res = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        dfs(root);
        return res;
    }

    public void dfs(TreeNode treeNode){
        if(treeNode == null){
            return;
        }
        dfs(treeNode.left);
        res.add(treeNode.val);
        dfs(treeNode.right);
    }*/
}
