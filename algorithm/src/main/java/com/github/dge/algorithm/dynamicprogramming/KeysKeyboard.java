package com.github.dge.algorithm.dynamicprogramming;

/**
 * @author dge
 * @version 1.0
 * @date 2022-05-17 9:31 上午
 * 只有两个键的键盘
 * https://leetcode-cn.com/classic/problems/2-keys-keyboard/description/
 */
public class KeysKeyboard {

    public static void main(String[] args) {
        KeysKeyboard keysKeyboard = new KeysKeyboard();
        int i = keysKeyboard.minSteps(18);
        System.out.println(i);
    }

    public int minSteps(int n) {
        int[] bp = new int[n + 1];
        bp[1] = 0;
        for (int i = 2; i <= n; i++) {
            bp[i] = Integer.MAX_VALUE;
            for (int j = 1; j <= i; j++) {
                if(i % j == 0){
                    bp[i] = Math.min(bp[i], bp[j] + i / j);
                    bp[i] = Math.min(bp[i], bp[i / j] + j);
                }
            }
        }
        return bp[n];
    }
}
