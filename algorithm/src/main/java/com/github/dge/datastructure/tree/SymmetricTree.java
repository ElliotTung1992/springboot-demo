package com.github.dge.datastructure.tree;

/**
 * @author dge
 * @version 1.0
 * @date 2022-09-05 1:37 PM
 * https://leetcode.com/problems/symmetric-tree/description/
 * https://leetcode.cn/problems/symmetric-tree/description/
 * 对称的二叉树
 */
public class SymmetricTree {

    public static void main(String[] args) {

    }

    public boolean isSymmetric(TreeNode root) {
        if(root == null){
            return false;
        }
        return check(root.left, root.right);
    }

    public boolean check(TreeNode one, TreeNode two){
        if(one == null && two == null){
            return true;
        }
        if(one != null && two != null && one.val == two.val){
            return check(one.left, two.right) && check(one.right, two.left);
        }
        return false;
    }


}
