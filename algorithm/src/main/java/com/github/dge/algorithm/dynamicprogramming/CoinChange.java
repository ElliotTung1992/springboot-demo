package com.github.dge.algorithm.dynamicprogramming;

import java.util.Arrays;

/**
 * @author dge
 * @version 1.0
 * @date 2022-05-01 8:29 下午
 * https://leetcode.com/problems/coin-change/description/
 * https://leetcode-cn.com/problems/coin-change/description/
 * 零钱兑换
 */
public class CoinChange {

    public static void main(String[] args) {
        CoinChange coinChange = new CoinChange();
        int[] coins = {1, 2, 5};
        int amount = 11;
        int i = coinChange.coinChange(coins, amount);
        System.out.println(i);
    }

    public int coinChange(int[] coins, int amount) {
        // max
        int max = amount + 1;
        // init
        int[] bp = new int[max];
        Arrays.fill(bp, max);
        bp[0] = 0;
        for (int i = 1; i < max; i++) {
            for (int j = 0; j < coins.length; j++) {
                int cc = coins[j];
                if(cc <= i){
                    bp[i] = Math.min(bp[i], bp[i - cc] + 1);
                }
            }
        }
        return bp[amount] > amount ? -1: bp[amount];
    }
}
