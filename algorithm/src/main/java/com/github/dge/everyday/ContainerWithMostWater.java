package com.github.dge.everyday;

public class ContainerWithMostWater {

    public static void main(String[] args) {
        ContainerWithMostWater containerWithMostWater = new ContainerWithMostWater();
        int[] height = {1,8,6,2,5,4,8,3,7};
        int i = containerWithMostWater.maxArea(height);
        System.out.println(i);
    }

    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int max = -1;
        while (right > left){
            max = Math.max(max, (right - left) * Math.min(height[left], height[right]));
            if(height[left] <= height[right]){
                left++;
            }else{
                right--;
            }
        }
        return max;
    }
}
