package com.github.dge.datastructure.tree;

import java.util.*;

/**
 * @author dge
 * @version 1.0
 * @date 2022-09-15 2:42 PM
 * https://leetcode.cn/problems/binary-tree-preorder-traversal/submissions/
 * https://leetcode.com/problems/binary-tree-preorder-traversal/description/
 * 二叉树的前序遍历
 */
public class BinaryTreePreorderTraversal {

    public static void main(String[] args) {
        Deque<Integer> queue = new LinkedList<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);

        System.out.println(queue.pop());
        System.out.println(queue.pop());
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()){
            while (root != null){
                res.add(root.val);
                stack.push(root);
                root = root.left;
            }
            TreeNode pop = stack.pop();
            root = pop.right;
        }
        return res;
    }

    /*List<Integer> res = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        dfs(root);
        return res;
    }

    private void dfs(TreeNode root){
        if(root == null){
            return;
        }
        res.add(root.val);
        dfs(root.left);
        dfs(root.right);
    }*/
}
