package com.github.dge.datastructure.tree;

/**
 * @author dge
 * @version 1.0
 * @date 2022-09-20 3:59 下午
 * https://leetcode.com/problems/trim-a-binary-search-tree/description/
 * https://leetcode.cn/problems/trim-a-binary-search-tree/description/
 * 修剪二叉搜索树
 */
public class TrimBinarySearchTree {

    public static void main(String[] args) {

    }

    public TreeNode trimBST(TreeNode root, int low, int high) {
        // 查看从哪里开始修剪
        while (root != null && (root.val < low || root.val > high)){
            if(root.val < low){
                root = root.right;
            }else{
                root = root.left;
            }
        }
        if(root == null){
            return null;
        }
        // 修剪左子树
        for (TreeNode treeNode = root; treeNode.left != null;) {
            if(treeNode.left.val < low){
                treeNode.left = treeNode.left.right;
            }else{
                treeNode = treeNode.left;
            }
        }
        // 修剪右子树
        for (TreeNode treeNode = root; treeNode.right != null;) {
            if(treeNode.right.val > high){
                treeNode.right = treeNode.right.left;
            }else{
                treeNode = treeNode.right;
            }
        }
        return root;
    }


    /*public TreeNode trimBST(TreeNode root, int low, int high) {
        if(root == null){
            return null;
        }
        if(root.val < low){
            return trimBST(root.right, low, high);
        }
        if(root.val > high){
            return trimBST(root.left, low, high);
        }
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }*/
}
