package com.github.dge.datastructure.tree;

/**
 * @author dge
 * @version 1.0
 * @date 2022-08-25 4:59 PM
 * https://leetcode.com/problems/balanced-binary-tree/description/
 * https://leetcode.cn/problems/balanced-binary-tree/description/
 * 平衡二叉树
 */
public class BalancedBinaryTree {

    public static void main(String[] args) {

    }

    public boolean isBalanced(TreeNode root) {
        return height(root) >= 0;
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    /*public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(getMaxDepth(root.left) - getMaxDepth(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    public int getMaxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getMaxDepth(root.left), getMaxDepth(root.right)) + 1;
    }*/
}
