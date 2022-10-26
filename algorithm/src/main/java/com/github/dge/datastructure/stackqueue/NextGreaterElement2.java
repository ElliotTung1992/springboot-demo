package com.github.dge.datastructure.stackqueue;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class NextGreaterElement2 {

    public static void main(String[] args) {
        NextGreaterElement2 nextGreaterElement2 = new NextGreaterElement2();
        int[] aa = new int[]{5,4,3,2,1};
        int[] ints = nextGreaterElement2.nextGreaterElements(aa);
        Arrays.toString(ints);

    }

    /*public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];
        Arrays.fill(ret, -1);
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n * 2 - 1; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]) {
                ret[stack.pop()] = nums[i % n];
            }
            stack.push(i % n);
        }
        return ret;
    }*/

    // [1,2,3,2,1]
    public int[] nextGreaterElements(int[] nums) {
        Deque<Integer> queue = new LinkedList<>();
        int length = nums.length;
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = -1;
            int num = nums[i];
            while (!queue.isEmpty() && num > nums[queue.peek()]){
                Integer index = queue.pop();
                result[index] = num;
            }
            queue.push(i);
        }

        if(!queue.isEmpty()){
            for (int i = 0; i < length && queue.size() > 1; i++) {
                int num = nums[i];
                while (!queue.isEmpty() && num > nums[queue.peek()]){
                    Integer index = queue.pop();
                    result[index] = num;
                }
            }
        }
        return result;
    }
}
