package com.github.dge.everyday;

public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        SearchInRotatedSortedArray search = new SearchInRotatedSortedArray();
        int[] nums = {3,1};
        int target = 1;
        int result = search.search(nums, target);
        System.out.println(result);
    }

    public int search(int[] nums, int target) {
        int length = nums.length;
        if(length == 0){
            return -1;
        }
        if(length == 1){
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = length - 1;
        while (r >= l){
            int mid = (r + l) / 2;
            if(nums[mid] == target){
                return mid;
            } else if(nums[mid] >= nums[l]){
                if(nums[mid] > target && target >= nums[l]){
                    r = mid - 1;
                }else{
                    l = mid + 1;
                }
            }else{
                if(nums[r] >= target && target > nums[mid]){
                    l = mid + 1;
                }else{
                    r = mid - 1;
                }
            }
        }
        return -1;
    }
}
