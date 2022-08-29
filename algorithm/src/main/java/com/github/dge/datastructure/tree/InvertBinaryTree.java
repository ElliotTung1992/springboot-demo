package com.github.dge.datastructure.tree;

/**
 * @author dge
 * @version 1.0
 * @date 2022-08-29 2:44 PM
 * https://leetcode.cn/problems/invert-binary-tree/description/
 * https://leetcode.com/problems/invert-binary-tree/description/
 * 翻转二叉树
 */
public class InvertBinaryTree {

    public static void main(String[] args) {

    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    public TreeNode invertTree2(TreeNode root) {
        invert(root);
        return root;
    }

    public void invert(TreeNode tree) {
        if(tree == null || (tree.left == null && tree.right == null)){
            return;
        }
        TreeNode left = tree.left;
        TreeNode right = tree.right;
        if(left != null){
            invert(left);
        }
        if(right  != null){
            invert(right);
        }

        tree.left = right;
        tree.right = left;
    }

}
