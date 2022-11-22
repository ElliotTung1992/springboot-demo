package com.github.dge.datastructure.array;

import java.util.Arrays;

public class FindTheDuplicateNumber {

    public static void main(String[] args) {
        FindTheDuplicateNumber findTheDuplicateNumber = new FindTheDuplicateNumber();
        int[] arr = {1,3,4,2,2};
        findTheDuplicateNumber.findDuplicate(arr);
    }

    /*public int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        int i = -1;
        for (int num : nums) {
            if(num == i){
                break;
            }
            i = num;
        }
        return i;
    }*/

    /*public int findDuplicate(int[] nums) {
        int n = nums.length;
        int l = 1, r = n - 1, ans = -1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            int cnt = 0;
            for (int i = 0; i < n; ++i) {
                if (nums[i] <= mid) {
                    cnt++;
                }
            }
            if (cnt <= mid) {
                l = mid + 1;
            } else {
                r = mid - 1;
                ans = mid;
            }
        }
        return ans;
    }*/

    /*public int findDuplicate(int[] nums) {
        int n = nums.length, ans = 0;
        int bit_max = 31;
        while (((n - 1) >> bit_max) == 0) {
            bit_max -= 1;
        }
        for (int bit = 0; bit <= bit_max; ++bit) {
            int x = 0, y = 0;
            for (int i = 0; i < n; ++i) {
                if ((nums[i] & (1 << bit)) != 0) {
                    x += 1;
                }
                if (i >= 1 && ((i & (1 << bit)) != 0)) {
                    y += 1;
                }
            }
            if (x > y) {
                ans |= 1 << bit;
            }
        }
        return ans;
    }*/

    public int findDuplicate(int[] nums) {
        // 创建快慢指针
        int slow = 0, fast = 0;
        do {
            // 慢指针走一步
            slow = nums[slow];
            // 快指针走两步
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
