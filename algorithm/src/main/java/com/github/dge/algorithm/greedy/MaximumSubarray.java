package com.github.dge.algorithm.greedy;

/**
 * @author dge
 * @version 1.0
 * @date 2022-03-08 9:36 下午
 * https://leetcode.com/problems/maximum-subarray/description/
 * https://leetcode-cn.com/problems/maximum-subarray/description/
 */
public class MaximumSubarray {

    public static void main(String[] args) {
        MaximumSubarray maximumSubarray = new MaximumSubarray();
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        int i = maximumSubarray.maxSubArray2(nums);
        System.out.println(i);
    }

    /**
     * 核心思想：
     * {（之前连续数组的值 + 当前值） 和 （当前值）} 做比较
     * 只有在 当前值 > 0 && 连续数组的值 < 0 丢弃 之前连续数组的值
     */
    public int maxSubArray(int[] nums) {
        int temp = 0; int max= nums[0];
        for (int num : nums) {
            temp = Math.max(temp + num, num);
            max = Math.max(temp, max);
        }
        return max;
    }

    public class Status {
        public int lSum, rSum, mSum, iSum;

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }

    public int maxSubArray2(int[] nums) {
        return getInfo(nums, 0, nums.length - 1).mSum;
    }

    public Status getInfo(int[] a, int l, int r) {
        if (l == r) {
            return new Status(a[l], a[l], a[l], a[l]);
        }
        int m = (l + r) >> 1;
        Status lSub = getInfo(a, l, m);
        Status rSub = getInfo(a, m + 1, r);
        return pushUp(lSub, rSub);
    }

    public Status pushUp(Status l, Status r) {
        int iSum = l.iSum + r.iSum;
        int lSum = Math.max(l.lSum, l.iSum + r.lSum);
        int rSum = Math.max(r.rSum, r.iSum + l.rSum);
        int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
        return new Status(lSum, rSum, mSum, iSum);
    }
}
