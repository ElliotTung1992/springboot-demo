package com.github.dge.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author dge
 * @version 1.0
 * @date 2022-09-14 3:29 PM
 * https://leetcode.com/problems/find-bottom-left-tree-value/description/
 * https://leetcode.cn/problems/find-bottom-left-tree-value/description/
 * 找树左下角的值
 */
public class FindBottomLeftTreeValue {

    public static void main(String[] args) {

    }

    public int findBottomLeftValue(TreeNode root) {



        /*int leftValue = root.val;
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode poll = queue.poll();
            if(poll.right != null){
                queue.offer(poll.right);
            }
            if(poll.left != null){
                queue.offer(poll.left);
            }
            leftValue = poll.val;
        }
        return leftValue;*/

        /*int leftValue = root.val;
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                if(i == 0){
                    leftValue = treeNode.val;
                }
                if(treeNode.left != null){
                    queue.offer(treeNode.left);
                }
                if(treeNode.right != null){
                    queue.offer(treeNode.right);
                }
            }
        }
        return leftValue;*/
        value = root.val;
        dfs(root, 0);
        return value;
    }

    Integer level = 0;
    Integer value;

    private void dfs(TreeNode treeNode, int currentLevel){
        if(treeNode == null){
            return;
        }
        if(currentLevel > level){
            level = currentLevel;
            value = treeNode.val;
        }
        dfs(treeNode.left, currentLevel + 1);
        dfs(treeNode.right, currentLevel + 1);
    }
}
