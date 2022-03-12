package com.github.dge.algorithm.binarysearch;

/**
 * @author dge
 * @version 1.0
 * @date 2022-03-07 10:04 上午
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 * 寻找两个正序数组的中位数
 */
public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        int[] nums1 = {1,2,4,5}, nums2 = {3,6,7,8};
        SearchInRotatedSortedArray  searchInRotatedSortedArray = new SearchInRotatedSortedArray();
        double medianSortedArrays = searchInRotatedSortedArray.findMedianSortedArrays(nums1, nums2);
        System.out.println(medianSortedArrays);
    }

    /**
     * 核心思想：
     * 计算出我们需要的中位数（总长是基数|needDelCount）(总长是偶数|needDelCount1 or needDelCount2)
     * 我们在两个有序数组中删除(needDelCount - 1)
     * 我们采用的二分删除法
     * 我们从两个数组的 startIndex + (needDelCount/2 - 1)开始寻找
     * 较小的数我们从needDelCount中删除(index-startIndex + 1)
     * 较小的数的下一个startIndex = index + 1开始
     *
     * 零界点：
     * 1. k = (k - k/2) = k/2 so k = 2 是最后一次寻找 还需要删最后一个数，则返回当前两个数组下表较小的一个数
     * 2. if k <= 1 其中一个数组全部删除，我们只需要返回另一个数组（needDelCount + startIndex - 1）
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalLength = nums1.length + nums2.length;
        if(totalLength % 2 == 1){
            return findTarget(nums1, nums2, totalLength/2 + 1);
        }else{
            int target = findTarget(nums1, nums2, totalLength / 2);
            int target1 = findTarget(nums1, nums2, totalLength / 2 + 1);
            return (double) (target + target1)/2;
        }
    }

    private int findTarget(int[] nums1, int[] nums2, int count) {
        int startIndex1 = 0, startIndex2 = 0;
        int nums1Length = nums1.length, nums2Length = nums2.length;
        while (true){
            if(startIndex1 == nums1Length){
                return nums2[startIndex2 + count - 1];
            }
            if(startIndex2 == nums2Length){
                return nums1[startIndex1 + count - 1];
            }
            if(count == 1){
                return Math.min(nums1[startIndex1], nums2[startIndex2]);
            }
            int m = count / 2;
            int index1 = Math.min(startIndex1 + m, nums1Length) - 1;
            int index2 = Math.min(startIndex2 + m, nums2Length) - 1;
            int temp1 = nums1[index1];
            int temp2 = nums2[index2];
            if(temp2 > temp1){
                count = count - (index1 - startIndex1 + 1);
                startIndex1 = index1 + 1;
            }else{
                count = count - (index2 - startIndex2 + 1);
                startIndex2 = index2 + 1;
            }
        }
    }
}
