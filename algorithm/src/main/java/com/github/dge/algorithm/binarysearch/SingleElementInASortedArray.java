package com.github.dge.algorithm.binarysearch;

/**
 * @author dge
 * @version 1.0
 * @date 2022-03-14 9:28 上午
 * https://leetcode.com/problems/single-element-in-a-sorted-array/description/
 * https://leetcode-cn.com/problems/single-element-in-a-sorted-array/description/
 * 有序数组中的单一元素
 */
public class SingleElementInASortedArray {

    public static void main(String[] args) {
        SingleElementInASortedArray  singleElementInASortedArray = new SingleElementInASortedArray();
        int[] nums = {1,1,2,3,3,4,4,8,8};
        int count = singleElementInASortedArray.singleNonDuplicate(nums);
        System.out.println(count);
    }

    /**
     * 核心思想：
     * 从左到又偶数下标和基数下标做比较
     * 如果arr[index] == arr[index+1]则匹配成功
     * 如果arr[index] != arr[index+1]则返回arr[index]
     */
    public int singleNonDuplicate2(int[] nums) {
        int i = 0;
        while (i < nums.length - 1){
            if(nums[i] == nums[i + 1]){
                i += 2;
            }else{
                return nums[i];
            }
        }
        return nums[nums.length - 1];
    }

    /**
     * 核心思想
     * 二分法过滤一半的数据直到找到零界点
     */
    public int singleNonDuplicate3(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (end > start){
            int mid = start + (end - start) / 2;
            if(mid % 2 == 1){
                // 奇数
                if(nums[mid] == nums[mid-1]){
                    start = mid + 1;
                }else{
                    end = mid - 1;
                }
            }else{
                // 偶数
                if(nums[mid] == nums[mid +1]){
                    start = mid + 2;
                }else{
                    end = mid;
                }
            }
        }
        return nums[end];
    }

    /**
     * 核心思想
     * 奇数index和index - 1比较
     * 相等则左边都是对的开始下标变成index + 1
     * 不相等则左边都是错的结束下标成成index
     * 偶数index和index + 1比较
     * 相等则左边都是对的开始下标变成index + 1
     * 不相等则左边都是错的结束下标成成index
     */
    public int singleNonDuplicate(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            // 奇数-1 偶数+1  5 ^ 1 = 4 / 4 ^ 1 = 5
            if (nums[mid] == nums[mid ^ 1]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return nums[low];
    }

    /**
     * 核心思想
     * 经分析未成对数字只会出现在偶数下标
     * 二分查找只处理偶数下标
     */
    public int singleNonDuplicate4(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            // 奇数 - 1 变偶数
            // 偶数 - 0 还是偶数
            mid -= mid & 1;
            if (nums[mid] == nums[mid + 1]) {
                low = mid + 2;
            } else {
                high = mid;
            }
        }
        return nums[low];
    }
}
