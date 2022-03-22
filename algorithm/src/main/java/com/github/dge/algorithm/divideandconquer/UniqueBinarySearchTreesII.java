package com.github.dge.algorithm.divideandconquer;

import java.util.LinkedList;
import java.util.List;

/**
 * @author dge
 * @version 1.0
 * @date 2022-03-22 9:23 上午
 * https://leetcode.com/problems/unique-binary-search-trees-ii/description/
 * https://leetcode-cn.com/problems/unique-binary-search-trees-ii/description/
 * 95. 不同的二叉搜索树 II
 */
public class UniqueBinarySearchTreesII {

    public static void main(String[] args) {
        UniqueBinarySearchTreesII uniqueBinarySearchTreesII = new UniqueBinarySearchTreesII();
        List<TreeNode> treeNodes = uniqueBinarySearchTreesII.generateTrees(3);
        System.out.println(treeNodes);
    }

    /**
     * 核心思想：
     * 搜索二叉树：搜索二叉树的左子树均小于根节点的值，搜索二叉树的右子树均大于根节点的值
     * 用分治法解决
     * 程序的出口是start > end 返回null
     */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<TreeNode>();
        }
        return generateTrees(1, n);
    }

    public List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> list = new LinkedList<TreeNode>();
        if(start > end){
            list.add(null);
            return list;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> lefts = generateTrees(start, i - 1);
            List<TreeNode> rights = generateTrees(i + 1, end);
            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    TreeNode treeNode = new TreeNode(i, left, right);
                    list.add(treeNode);
                }
            }
        }
        return list;
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
