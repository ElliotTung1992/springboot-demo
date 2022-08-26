package com.github.dge.datastructure.tree;

/**
 * @author dge
 * @version 1.0
 * @date 2022-08-26 11:14 AM
 * https://leetcode.cn/problems/diameter-of-binary-tree/description/
 * https://leetcode.com/problems/diameter-of-binary-tree/description/
 * 两节点的最长路径
 */
public class DiameterOfBinaryTree {

    public static void main(String[] args) {

    }

    private int max = -1;

    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return max;
    }

    public int maxDepth(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        max = Math.max(max, left + right);
        return 1 + Math.max(left, right);
    }
}
