package com.github.dge.everyday;

public class TrappingRainWater {

    public static void main(String[] args) {
        TrappingRainWater trappingRainWater = new TrappingRainWater();
        // int[] arr = {0,1,0,2,1,0,1,3,2,1,2,1};
        int[] arr = {4,2,0,3,2,5};
        int trap = trappingRainWater.trap(arr);
        System.out.println(trap);
    }

    public int trap(int[] height) {
        int length = height.length;

        int[] left = new int[length];
        left[0] = height[0];
        for (int i = 1; i < length; i++) {
            left[i] = Math.max(left[i - 1], height[i]);
        }

        int[] right = new int[length];
        right[length - 1] = height[length - 1];
        for (int i = length - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], height[i]);
        }

        int count = 0;
        for (int i = 0; i < length; i++) {
            count += Math.min(left[i], right[i]) - height[i];
        }
        return count;
    }
}
