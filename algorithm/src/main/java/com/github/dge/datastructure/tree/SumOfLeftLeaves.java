package com.github.dge.datastructure.tree;

/**
 * @author dge
 * @version 1.0
 * @date 2022-09-07 2:05 PM
 * https://leetcode.cn/problems/sum-of-left-leaves/solution/zuo-xie-zi-zhi-he-by-leetcode-solution/
 * https://leetcode.com/problems/sum-of-left-leaves/description/
 * 左叶子之和
 */
public class SumOfLeftLeaves {

    public static void main(String[] args) {

    }

    public int sumOfLeftLeaves(TreeNode root) {
        if(root.left == null && root.right == null){
            return 0;
        }
        return seek(root.left, root.right);
    }

    public int seek(TreeNode left, TreeNode right){
        if(left == null && right == null){
            return 0;
        }
        int result = 0;
        if(left != null && left.left == null && left.right == null){
            result = left.val;
        }
        int leftInt = 0 ,rightInt = 0;
        if(left != null){
            leftInt = seek(left.left, left.right);
        }
        if(right != null){
            rightInt = seek(right.left, right.right);
        }
        return leftInt + rightInt + result;
    }
}
