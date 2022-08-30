package com.github.dge.datastructure.tree;

/**
 * @author dge
 * @version 1.0
 * @date 2022-08-30 10:20 AM
 * https://leetcode.com/problems/merge-two-binary-trees/description/
 * https://leetcode.cn/problems/merge-two-binary-trees/description/
 * 合并二叉树
 */
public class MergeTwoBinaryTree {

    public static void main(String[] args) {

    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1 == null){
            return t2;
        }
        if(t2 == null){
            return t1;
        }
        TreeNode merge = new TreeNode(t1.val + t2.val);
        merge.left = mergeTrees(t1.left, t2.left);
        merge.right = mergeTrees(t1.right, t2.right);
        return merge;
    }

    public TreeNode mergeTrees2(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null){
            return null;
        }
        if(root1 == null){
            root1 = new TreeNode(0);
        }
        if(root2 == null){
            root2 = new TreeNode(0);
        }
        merge(root1, root2);
        return root1;
    }

    public void merge(TreeNode root1, TreeNode root2) {
        // validate
        if(root1 == null && root2 == null){
            return;
        }

        // option
        if(root2 != null){
            root1.val = root1.val + root2.val;
        }

        // build
        if(root1.left == null && root2.left != null){
            root1.left = new TreeNode(0);
        }
        if(root1.left != null && root2.left == null){
            root2.left = new TreeNode(0);
        }
        if(root1.right == null && root2.right != null){
            root1.right = new TreeNode(0);
        }
        if(root1.right != null && root2.right == null){
            root2.right = new TreeNode(0);
        }

        // recursion
        merge(root1.left, root2.left);
        merge(root1.right, root2.right);
    }
}
