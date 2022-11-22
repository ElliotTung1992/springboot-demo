package com.github.dge.datastructure.array;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class MoveZeroes {

    public static void main(String[] args) {
        MoveZeroes moveZeroes = new MoveZeroes();
        int[] arr = {0,1,2,0,12};
        moveZeroes.moveZeroes(arr);
    }

    public void moveZeroes(int[] nums) {
        int left = 0, right = 0, length = nums.length;
        while (right < length) {
            if(nums[right] != 0){
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    public void moveZeroes3(int[] nums) {
        Deque<Integer> deque = new LinkedList<>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if(nums[i] == 0){
                deque.addLast(i);
            }else{
                if(!deque.isEmpty()){
                    Integer first = deque.pollFirst();
                    nums[first] = nums[i];
                    nums[i] = 0;
                    deque.addLast(i);
                }
            }
        }
    }

    public void moveZeroes2(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if(nums[i] == 0){
                for (int j = i + 1; j < length ; j++) {
                    if(nums[j] != 0){
                        nums[i] = nums[j];
                        nums[j] = 0;
                        break;
                    }
                }
            }
        }
    }
}
