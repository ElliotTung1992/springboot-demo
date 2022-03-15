package com.github.dge.algorithm.binarysearch;

/**
 * @author dge
 * @version 1.0
 * @date 2022-03-15 9:33 上午
 * https://leetcode.com/problems/first-bad-version/description/
 * https://leetcode-cn.com/problems/first-bad-version/description/
 * 第一个错误的版本
 */
public class FirstBadVersion {

    public static void main(String[] args) {
        FirstBadVersion firstBadVersion = new FirstBadVersion();
        int i = firstBadVersion.firstBadVersion(5);
        System.out.println(i);
    }

    /**
     * 核心思想：
     * 最简单的二分查找
     */
    public int firstBadVersion(int n) {
        int start = 1, end = n;
        while (end > start){
            int mid = start + (end - start) / 2;
            if(isBadVersion(mid)){
                end = mid;
            }else{
                start = mid + 1;
            }
        }
        return end;
    }

    private boolean isBadVersion(int version) {
        if(version >= 4){
            return true;
        }
        return false;
    }
}
