package com.github.dge.algorithm.math;

/**
 * @author dge
 * @version 1.0
 * @date 2022-08-08 4:06 PM
 * https://leetcode.com/problems/power-of-three/description/
 * 3 的幂
 */
public class PowerOfThree {

    public static void main(String[] args) {
        PowerOfThree powerOfThree = new PowerOfThree();
        boolean isPower = powerOfThree.isPowerOfThree(1);
        System.out.println(isPower);
    }

    public boolean isPowerOfThree2(int n) {
        if(n == 1){
            return true;
        }
        while (n >= 3 && n % 3 == 0){
            n /= 3;
            if(n == 1){
                return true;
            }
        }
        return false;
    }

    public boolean isPowerOfThree(int n) {
        while (n > 1 && n % 3 == 0){
            n /= 3;
        }
        return n == 1;
    }

    public boolean isPowerOfThree3(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
}
