package com.github.dge.datastructure.tree;

/**
 * @author dge
 * @version 1.0
 * @date 2022-09-08 9:59 PM
 * https://fanyi.baidu.com/?aldtype=16047#en/zh/unique
 * https://leetcode.com/problems/longest-univalue-path/
 */
public class LongestUnivaluePath {

    public static void main(String[] args) {

    }

    public int longestUnivaluePath(TreeNode root) {
        search(root);
        return res;
    }
    
    int res = 0;

    public int search(TreeNode treeNode){
        if(treeNode == null){
            return 0;
        }
        int left = search(treeNode.left), right = search(treeNode.right);
        int left1 = 0, right1 = 0;
        if(treeNode.left != null && treeNode.left.val == treeNode.val){
            left1 = left + 1;
        }
        if(treeNode.right != null && treeNode.right.val == treeNode.val){
            right1 = right + 1;
        }
        res = Math.max(res, left1 + right1);
        return Math.max(left1, right1);
    }
}
