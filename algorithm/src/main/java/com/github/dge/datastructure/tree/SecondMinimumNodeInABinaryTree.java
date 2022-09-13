package com.github.dge.datastructure.tree;

/**
 * @author dge
 * @version 1.0
 * @date 2022-09-13 2:32 PM
 * https://leetcode.cn/problems/second-minimum-node-in-a-binary-tree/submissions/
 * https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/description/
 * 二叉树中第二小的节点
 */
public class SecondMinimumNodeInABinaryTree {

    public static void main(String[] args) {

    }

    int result;
    int rootValue;

    public int findSecondMinimumValue(TreeNode root) {
        result = -1;
        rootValue = root.val;
        dfs(root);
        return result;
    }

    public void dfs(TreeNode node) {
        if(node == null){
            return;
        }
        if(result != -1 && node.val >= result){
            return;
        }
        if(node.val > rootValue){
            result = node.val;
        }
        dfs(node.left);
        dfs(node.right);
    }

    /*int second = Integer.MAX_VALUE;
    int mini = -1;
    int count = 0;

    public int findSecondMinimumValue(TreeNode root) {
        mini = root.val;
        find(root);
        if(second == mini){
            return -1;
        }
        if(second == Integer.MAX_VALUE && count == 0){
            return -1;
        }
        return second;
    }

    public void find(TreeNode treeNode){
        if(treeNode == null){
            return;
        }
        if(treeNode.left == null){
            return;
        }
        if(treeNode.left.val > mini){
            count++;
            second = Math.min(second, treeNode.left.val);
        }
        if(treeNode.right.val > mini){
            count++;
            second = Math.min(second, treeNode.right.val);
        }
        find(treeNode.left);
        find(treeNode.right);
    }*/
}
