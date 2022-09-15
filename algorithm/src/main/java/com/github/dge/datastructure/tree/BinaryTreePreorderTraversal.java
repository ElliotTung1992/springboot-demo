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
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }

        TreeNode p1 = root, p2 = null;

        while (p1 != null) {
            p2 = p1.left;
            if (p2 != null) {
                while (p2.right != null && p2.right != p1) {
                    p2 = p2.right;
                }
                if (p2.right == null) {
                    res.add(p1.val);
                    p2.right = p1;
                    p1 = p1.left;
                    continue;
                } else {
                    p2.right = null;
                }
            } else {
                res.add(p1.val);
            }
            p1 = p1.right;
        }
        return res;
    }

    /*public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null){
            while (node != null){
                res.add(node.val);
                stack.push(node);
                node = node.left;
            }
            TreeNode pop = stack.pop();
            node = pop.right;
        }
        return res;
    }*/

    /*public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorder(root, res);
        return res;
    }

    public void preorder(TreeNode root, List<Integer> res) {
        if(root == null){
            return;
        }
        res.add(root.val);
        preorder(root.left, res);
        preorder(root.right, res);
    }*/

    /*List<Integer> integersList = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        if(root == null){
            return integersList;
        }
        integersList.add(root.val);
        dfs(root);
        return integersList;
    }

    private void dfs(TreeNode treeNode){
        if(treeNode == null){
            return;
        }
        if(treeNode.left != null){
            integersList.add(treeNode.left.val);
            dfs(treeNode.left);
        }
        if(treeNode.right != null){
            integersList.add(treeNode.right.val);
            dfs(treeNode.right);
        }
    }*/
}
