package com.github.dge.datastructure.tree;

import java.util.*;

/**
 * @author dge
 * @version 1.0
 * @date 2022-09-16 2:00 PM
 * https://leetcode.com/problems/binary-tree-postorder-traversal/description/
 * https://leetcode.cn/problems/binary-tree-postorder-traversal/description/
 * 二叉树的后序遍历
 */
public class BinaryTreePostorderTraversal {

    public static void main(String[] args) {
        int a = 10;
        if(a > 1){
            System.out.println(1);
        }else if(a > 2){
            System.out.println(2);
        }
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()){
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(root.right == null || root.right == pre){
                res.add(root.val);
                pre = root;
                root = null;
            }else{
                stack.push(root);
                root = root.right;
            }
        }
        return res;
    }

    /*public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> s = new Stack<>();
        Set<TreeNode> seen = new HashSet<>();
        while (root != null || !s.isEmpty()) {
            if (root == null && seen.contains(s.peek())) {
                ans.add(s.pop().val);
            } else if (root == null) {
                seen.add(s.peek());
                root = s.peek().right;
            } else {
                s.push(root);
                root = root.left;
            }
        }
        return ans;
    }*/

    /*public List<Integer> postorderTraversal(TreeNode root) {
        // 初始化结果集
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        // 创建栈
        Deque<TreeNode> stack = new LinkedList<>();
        // 标识:上一次处理的节点
        TreeNode prev = null;
        // 如果当前处理节点不为空或者栈中有数据则继续处理
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                // 如果当前处理节点不为空则入栈
                stack.push(root);
                // 寻找左子树
                root = root.left;
            }
            // 拿取栈顶节点
            root = stack.pop();
            // if逻辑
            // root.right == null 表示当前节点为根节点
            // root.right == prev 如果右子树是上一次处理的节点则处理根节点
            // else逻辑
            // 右子树不为空，左子树找到根,根找到右子树，先处理右子树
            if (root.right == null || root.right == prev) {
                res.add(root.val);
                // 打标
                prev = root;
                root = null;
            } else {
                // 重新把根节点入栈，处理完右子树还要回来处理根节点
                stack.push(root);
                // 当前节点为右子树
                root = root.right;
            }
        }
        return res;
    }*/

    /*List<Integer> res = new ArrayList<>();

    public List<Integer> postorderTraversal(TreeNode root) {
        dfs(root);
        return res;
    }

    private void dfs(TreeNode treeNode){
        if(treeNode == null){
            return;
        }
        dfs(treeNode.left);
        dfs(treeNode.right);
        res.add(treeNode.val);
    }*/
}
