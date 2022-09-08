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
        if(root == null){
            return 0;
        }
        search(root, root.left, root.right);
        if(aaa > 1){
            return aaa - 1;
        }
        return aaa;
    }
    
    int aaa = 0;

    public int search(TreeNode root, TreeNode left, TreeNode right){
        if(root == null){
            return 0;
        }
        if(left == null && right == null){
            return 0;
        }
        int leftCount = 0, rightCount = 0;
        if(left != null){
            leftCount = search(left, left.left, left.right);
        }
        if(right != null){
            rightCount = search(right, right.left, right.right);
        }
        if(left != null && root.val == left.val){
            if(leftCount > 0){
                leftCount ++;
            }else{
                leftCount += 2;
            }
        }
        if(right != null && root.val == right.val){
            if(rightCount > 0){
                rightCount ++;
            }else{
                rightCount += 2;
            }
        }
        if(left != null && right != null && root.val == left.val && left.val == right.val){
            aaa = Math.max(aaa, leftCount + rightCount - 1);
        }
        int bbb = Math.max(leftCount, rightCount);
        aaa = Math.max(aaa, bbb);
        return bbb;
    }
}
