package com.github.dge.datastructure.tree;

import java.util.*;

/**
 * @author dge
 * @version 1.0
 * @date 2022-09-28 2:07 下午
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/
 * https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/description/
 * 将有序数组转换为二叉搜索树
 */
public class ConvertSortedArrayToBinarySearchTree {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        //list.add(2);
        /*list.add(3);
        list.add(4);
        list.add(5);*/

        int size = list.size();
        int middle = size / 2;

        List<Integer> integers = list.subList(0, middle);
        List<Integer> integers2 = list.subList(middle + 1, size);

        System.out.println(integers);
        System.out.println(integers2);
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    public TreeNode helper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        // 总是选择中间位置左边的数字作为根节点
        int mid = (left + right) / 2;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        return root;
    }



    /*public TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null || nums.length == 0){
            return null;
        }
        List<Integer> list = new ArrayList<>(nums.length);
        for (int num : nums) {
            list.add(num);
        }
        int size = list.size();
        int middle = size / 2;
        TreeNode treeNode = new TreeNode(nums[middle]);
        recursion(treeNode, list.subList(0, middle), list.subList(middle + 1, size));
        return treeNode;
    }

    public void recursion(TreeNode treeNode, List<Integer> leftList, List<Integer> rightList) {
        // build left tree
        if(leftList.size() > 0){
            int leftSize = leftList.size();
            int leftMiddle = leftSize / 2;
            treeNode.left = new TreeNode(leftList.get(leftMiddle));
            recursion(treeNode.left, leftList.subList(0, leftMiddle), leftList.subList(leftMiddle + 1, leftSize));
        }

        // build right tree
        if(rightList.size() > 0){
            int rightSize = rightList.size();
            int rightMiddle = rightSize / 2;
            treeNode.right = new TreeNode(rightList.get(rightMiddle));
            recursion(treeNode.right, rightList.subList(0, rightMiddle), rightList.subList(rightMiddle + 1, rightSize));
        }
    }*/
}
