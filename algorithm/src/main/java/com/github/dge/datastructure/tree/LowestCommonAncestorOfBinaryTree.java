package com.github.dge.datastructure.tree;

import java.util.*;

/**
 * @author dge
 * @version 1.0
 * @date 2022-09-27 3:05 下午
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
 * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/description/
 * 二叉树的最近公共祖先
 */
public class LowestCommonAncestorOfBinaryTree {

    public static void main(String[] args) {

    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<Integer> res = new ArrayList<>();
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
        return null;
    }

    /*private Map<Integer, TreeNode> parentMap = new HashMap<>();

    private Set<Integer> set = new HashSet<>();

    public void dfs(TreeNode root) {
        if(root.left != null){
            parentMap.put(root.left.val, root);
            dfs(root.left);
        }
        if(root.right != null){
            parentMap.put(root.right.val, root);
            dfs(root.right);
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        while (p != null){
            set.add(p.val);
            TreeNode treeNode = parentMap.get(p.val);
            p = treeNode;
        }
        while (q != null){
            if(set.contains(q.val)){
                return q;
            }
            q = parentMap.get(q.val);
        }
        return null;
    }*/

    /*private TreeNode commonAncestor;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return this.commonAncestor;
    }

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q){
        if(root == null){
            return false;
        }
        boolean lSon = dfs(root.left, p, q);
        boolean rSon = dfs(root.right, p, q);
        if((lSon && rSon) || ((root.val == p.val || root.val == q.val) && (lSon || rSon))){
            commonAncestor = root;
        }
        return lSon || rSon || (root.val == p.val) || (root.val == q.val);
    }*/
}
