package com.github.dge.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dge
 * @version 1.0
 * @date 2022-04-03 9:19 下午
 * https://leetcode.com/problems/binary-tree-paths/description/
 * https://leetcode-cn.com/problems/binary-tree-paths/description/
 * 二叉树的所有路径
 */
public class BinaryTreePaths {

    public static void main(String[] args) {
        BinaryTreePaths binaryTreePaths = new BinaryTreePaths();
        List<String> strings = binaryTreePaths.binaryTreePaths(null);
        System.out.println(strings);
    }

    public List<String> binaryTreePaths(TreeNode root) {
        int val = root.val;
        List<Integer> intList = new ArrayList<>();
        intList.add(val);
        List<String> strList = new ArrayList<>();
        backTrack(root, intList, strList);
        return strList;
    }

    private void backTrack(TreeNode treeNode, List<Integer> intList, List<String> strList){
        if(treeNode!= null && treeNode.left == null && treeNode.right == null){
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < intList.size(); i++) {
                sb.append(intList.get(i));
                if(i != intList.size() - 1){
                    sb.append("->");
                }
            }
            strList.add(sb.toString());
        }else{
            TreeNode left = treeNode.left;
            if(left != null){
                intList.add(left.val);
                backTrack(left, intList, strList);
                intList.remove(intList.size() - 1);
            }
            TreeNode right = treeNode.right;
            if(right != null){
                intList.add(right.val);
                backTrack(right, intList, strList);
                intList.remove(intList.size() - 1);
            }
        }
    }
}



class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
