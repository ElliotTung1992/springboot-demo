package com.github.dge.algorithm.greedy;

/**
 * @author dge
 * @version 1.0
 * @date 2022-03-07 10:20 上午
 *  非递减数列
 *  https://leetcode-cn.com/problems/non-decreasing-array/
 *  https://leetcode.com/problems/non-decreasing-array/description/
 *
 *  核心思想就是：
 *  if arr[index] < arr[index-1]
 *      if index - 1 = 0
 *          arr[index-1] = arr[index]
 *      if arr[index] >= arr[index -2]
 *          arr[index-1] = arr[index]
 *      else
 *          arr[index] = arr[index-1]
 */
public class NonDecreasingArray {

    public static void main(String[] args) {
        NonDecreasingArray array = new NonDecreasingArray();
        //int[] nums = {-1,4,2,3};
        // int[] nums = {3,2,1,1};
        //                [3,4,2,3]
        int[] nums = {3,4,2,3};
        // 543
        // 879
        // 876
        boolean b = array.checkPossibility(nums);
        System.out.println(b);
    }

    /**
     * my answer
     *
     * @param nums nums
     * @return boolean
     * @author dge
     * @date 2022/3/7 12:26 下午
     */
    public boolean checkPossibility(int[] nums) {
        if(nums.length <= 2){
            return Boolean.TRUE;
        }
        int temp = nums[0];
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if(temp > nums[i]){
                if(count == 1){
                    return Boolean.FALSE;
                }
                if(i - 1 == 0 || nums[i] >= nums[i-2]){
                    temp = nums[i];
                }else{
                    nums[i] = temp;
                }
                count++;
            }
            temp = nums[i];
        }
        return Boolean.TRUE;
    }

    /**
     * 核心思想：
     * foreach arr
     * if arr[i] > arr[i+1]
     *      set arr[i+1] = arr[i] arr is order
     *      set arr[i] = arr[i+1] arr is order
     *  如果两种情况下都不满足则返回false, 满足任一条件则返回true
     */
    public boolean checkPossibility2(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; ++i) {
            int x = nums[i], y = nums[i + 1];
            if (x > y) {
                nums[i] = y;
                if (isSorted(nums)) {
                    return true;
                }
                nums[i] = x;
                nums[i + 1] = x;
                return isSorted(nums);
            }
        }
        return true;
    }

    public boolean isSorted(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; ++i) {
            if (nums[i - 1] > nums[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 和我的思想很想，比我的更加精简
     */
    public boolean checkPossibility3(int[] nums) {
        int n = nums.length, cnt = 0;
        for (int i = 0; i < n - 1; ++i) {
            int x = nums[i], y = nums[i + 1];
            if (x > y) {
                cnt++;
                if (cnt > 1) {
                    return false;
                }
                if (i > 0 && y < nums[i - 1]) {
                    nums[i + 1] = x;
                }
            }
        }
        return true;
    }
}
