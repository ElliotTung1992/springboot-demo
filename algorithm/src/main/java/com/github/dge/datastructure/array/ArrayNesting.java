package com.github.dge.datastructure.array;

import java.util.Arrays;

public class ArrayNesting {

    public static void main(String[] args) {
        ArrayNesting arrayNesting = new ArrayNesting();
        int[] arr = {5,4,0,3,1,6,2};
        arrayNesting.arrayNesting(arr);
    }

    public int arrayNesting(int[] nums) {
        int max = -1;
        int[] arr = new int[nums.length];
        Arrays.fill(arr, -1);
        for (int num : nums) {
            if(arr[num] == num){
                continue;
            }
            int temp = num;
            int count = 0;
            while (arr[temp] != temp){
                arr[temp] = temp;
                temp = nums[temp];
                count++;
            }
            max = Math.max(max, count);
        }
        return max;
    }

    public int arrayNesting2(int[] nums) {
        int ans = 0, n = nums.length;
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; ++i) {
            int cnt = 0;
            while (!vis[i]) {
                vis[i] = true;
                i = nums[i];
                ++cnt;
            }
            ans = Math.max(ans, cnt);
        }
        return ans;
    }
}
