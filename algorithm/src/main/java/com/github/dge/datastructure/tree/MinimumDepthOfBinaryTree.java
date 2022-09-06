package com.github.dge.datastructure.tree;

/**
 * @author dge
 * @version 1.0
 * @date 2022-09-06 11:11 AM
 * https://leetcode.cn/problems/minimum-depth-of-binary-tree/description/
 * https://leetcode.com/problems/minimum-depth-of-binary-tree/description/
 * 二叉树的最小深度
 */
public class MinimumDepthOfBinaryTree {

    public static void main(String[] args) {
        System.out.println(Math.min(1, 2147483647) + 1);
        System.out.println(2147483647 + 1);
    }

    public int minDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        return seek(root.left, root.right) + 1;
    }

    public int seek(TreeNode left, TreeNode right) {
        if(left == null && right== null){
            return 0;
        }
        int leftCount = Integer.MAX_VALUE;
        if(left != null){
            leftCount = seek(left.left, left.right);
        }
        int rightCount = Integer.MAX_VALUE;
        if(right != null){
            rightCount = seek(right.left, right.right);
        }
        return Math.min(leftCount, rightCount) + 1;
    }

    /*public int minDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        int left = Integer.MAX_VALUE;
        if(root.left != null){
            left = seek(root.left);
        }
        int right = Integer.MAX_VALUE;
        if(root.right != null){
            right = seek(root.right);
        }
        return Math.min(left, right) + 1;
    }

    public int seek(TreeNode root) {
        if(root == null){
            return 0;
        }
        return 1 + Math.min(seek(root.left), seek(root.right));
    }*/


}
