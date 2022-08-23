package com.github.dge.datastructure.tree;

/**
 * @author dge
 * @version 1.0
 * @date 2022-08-23 4:14 PM
 * https://leetcode.cn/problems/maximum-depth-of-binary-tree/description/
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
 */
public class MaximumDepthOfBinaryTree {

    public static void main(String[] args) {

    }

    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        return 1 + Integer.max(maxDepth(root.left), maxDepth(root.right));
    }


}
