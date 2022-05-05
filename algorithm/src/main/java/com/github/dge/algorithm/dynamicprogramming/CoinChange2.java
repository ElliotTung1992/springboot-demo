package com.github.dge.algorithm.dynamicprogramming;

/**
 * @author dge
 * @version 1.0
 * @date 2022-05-05 9:45 上午
 * https://leetcode.com/problems/coin-change-2/description/
 * https://leetcode-cn.com/problems/coin-change-2/description/
 *
 */
public class CoinChange2 {

    public static void main(String[] args) {
        CoinChange2 coinChange2 = new CoinChange2();
        int amount = 5;
        int[] coins = {1, 2, 5};
        int change = coinChange2.change(amount, coins);
        System.out.println(change);
    }

    public int change(int amount, int[] coins) {
        int[] bp = new int[amount + 1];
        bp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            int coin = coins[i];
            for (int j = coin; j <= amount; j++) {
                bp[j] += bp[j - coin];
            }
        }
        return bp[amount];
    }
}
