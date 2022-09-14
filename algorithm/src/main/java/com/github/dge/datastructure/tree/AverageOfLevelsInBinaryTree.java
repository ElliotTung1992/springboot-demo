package com.github.dge.datastructure.tree;

import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author dge
 * @version 1.0
 * @date 2022-09-14 2:09 PM
 */
public class AverageOfLevelsInBinaryTree {

    public static void main(String[] args) {

    }

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> averages = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            Double sum = 0.0;
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                sum += treeNode.val;
                if(treeNode.left != null){
                    queue.offer(treeNode.left);
                }
                if(treeNode.right != null){
                    queue.offer(treeNode.right);
                }
            }
            averages.add(sum / size);
        }
        return averages;
    }

    /*public List<Double> averageOfLevels(TreeNode root) {
        List<Integer> counts = new ArrayList<>();
        List<Double> sums = new ArrayList<>();
        dfs(root, 0, counts, sums);
        List<Double> averages = new ArrayList<>();
        int size = sums.size();
        for (int i = 0; i < size; i++) {
            averages.add(sums.get(i) / counts.get(i));
        }
        return averages;
    }

    public void dfs(TreeNode root, int level, List<Integer> counts, List<Double> sums) {
        if (root == null) {
            return;
        }
        if (level < sums.size()) {
            sums.set(level, sums.get(level) + root.val);
            counts.set(level, counts.get(level) + 1);
        } else {
            sums.add(1.0 * root.val);
            counts.add(1);
        }
        dfs(root.left, level + 1, counts, sums);
        dfs(root.right, level + 1, counts, sums);
    }*/

    /*public List<Double> averageOfLevels(TreeNode root) {
        dfs(Collections.singletonList(root));
        return resultList;
    }

    List<Double> resultList = new ArrayList<>();

    public void dfs(List<TreeNode> list){
        if(list == null || list.isEmpty()){
            return;
        }
        Double sum = 0.0;
        List<TreeNode> treeNodes = new ArrayList<>();
        for (TreeNode treeNode : list) {
            sum += treeNode.val;
            if(treeNode.left != null){
                treeNodes.add(treeNode.left);
            }
            if(treeNode.right != null){
                treeNodes.add(treeNode.right);
            }
        }
        resultList.add(sum / list.size());
        dfs(treeNodes);
    }*/
}
