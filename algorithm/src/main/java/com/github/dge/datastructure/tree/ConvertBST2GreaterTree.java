package com.github.dge.datastructure.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author dge
 * @version 1.0
 * @date 2022-09-22 4:27 下午
 * https://leetcode.com/problems/convert-bst-to-greater-tree/description/
 * https://leetcode.cn/problems/convert-bst-to-greater-tree/
 * 把二叉搜索树转换为累加树
 */
public class ConvertBST2GreaterTree {

    public static void main(String[] args) {

    }

    int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }

    /*public TreeNode convertBST(TreeNode origin) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode root = origin;
        int temp = 0;
        while (root != null || !stack.isEmpty()){
            while (root != null){
                stack.push(root);
                root = root.right;
            }
            TreeNode pop = stack.pop();
            pop.val = pop.val + temp;
            temp = pop.val;
            if(pop.left != null){
                root = pop.left;
            }
        }
        return origin;
    }*/

    /*public TreeNode convertBST(TreeNode root) {
        dfs(root, 0);
        return root;
    }

    private int dfs(TreeNode root, int sum){
        if(root == null){
            return sum;
        }
        // 右子树
        int right = dfs(root.right, sum);
        // 根
        root.val = root.val + right;
        // 左子树
        return dfs(root.left, root.val);
    }*/
}
