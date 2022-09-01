package com.github.dge.datastructure.tree;

/**
 * @author dge
 * @version 1.0
 * @date 2022-08-31 3:24 PM
 */
public class PathSum {

    public static void main(String[] args) {

    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }


}
