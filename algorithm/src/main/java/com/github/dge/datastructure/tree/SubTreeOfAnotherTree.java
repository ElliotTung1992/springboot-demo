package com.github.dge.datastructure.tree;

/**
 * @author dge
 * @version 1.0
 * @date 2022-09-02 2:31 PM
 * 另一棵树的子树
 * https://leetcode.cn/problems/subtree-of-another-tree/description/
 * https://leetcode.com/problems/subtree-of-another-tree/description/
 */
public class SubTreeOfAnotherTree {

    public static void main(String[] args) {

    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(root == null){
            return false;
        }
        return isSame(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    public boolean isSame(TreeNode one, TreeNode two){
        if(one == null && two == null){
            return true;
        }
        if(one != null && two != null && one.val == two.val){
            return isSame(one.left, two.left) && isSame(one.right, two.right);
        }
        return false;
    }


}
