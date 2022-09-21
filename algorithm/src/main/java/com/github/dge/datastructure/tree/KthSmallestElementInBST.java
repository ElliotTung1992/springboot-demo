package com.github.dge.datastructure.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author dge
 * @version 1.0
 * @date 2022-09-21 10:51 上午
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/
 * https://leetcode.cn/problems/kth-smallest-element-in-a-bst/description/
 * 二叉搜索树中第K小的元素
 */
public class KthSmallestElementInBST {

    public static void main(String[] args) {

    }

    // 递归实现
    public int kthSmallest(TreeNode root, int k) {
        int leftCnt = count(root.left);
        if (leftCnt == k - 1) {
            return root.val;
        }
        if (leftCnt > k - 1) {
            return kthSmallest(root.left, k);
        }
        return kthSmallest(root.right, k - leftCnt - 1);
    }

    private int count(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + count(node.left) + count(node.right);
    }


    /**
    // 树的中序遍历实现
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()){
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            TreeNode pop = stack.pop();
            k -= 1;
            if(k == 0){
                return pop.val;
            }
            if(pop.right != null){
                root = pop.right;
            }
        }
        return -1;
    }*/
}
