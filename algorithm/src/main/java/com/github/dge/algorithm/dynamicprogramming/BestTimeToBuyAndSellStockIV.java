package com.github.dge.algorithm.dynamicprogramming;

import java.util.Arrays;

/**
 * @author dge
 * @version 1.0
 * @date 2022-05-12 10:01 上午
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/description/
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv/description/
 * 买卖股票的最佳时机 IV
 */
public class BestTimeToBuyAndSellStockIV {

    public static void main(String[] args) {
        BestTimeToBuyAndSellStockIV test = new BestTimeToBuyAndSellStockIV();
        int k = 2;
        int[] prices = {3,2,6,5,0,3};
        int i = test.maxProfit(2, prices);
        System.out.println(i);
    }

    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int n = prices.length;
        k = Math.min(k, n / 2);
        int[] buy = new int[k + 1];
        int[] sell = new int[k + 1];

        buy[0] = -prices[0];
        sell[0] = 0;
        for (int i = 1; i <= k; ++i) {
            buy[i] = sell[i] = Integer.MIN_VALUE / 2;
        }

        for (int i = 1; i < n; ++i) {
            buy[0] = Math.max(buy[0], sell[0] - prices[i]);
            for (int j = 1; j <= k; ++j) {
                buy[j] = Math.max(buy[j], sell[j] - prices[i]);
                sell[j] = Math.max(sell[j], buy[j - 1] + prices[i]);
            }
        }

        return Arrays.stream(sell).max().getAsInt();
    }

    public int maxProfit2(int k, int[] prices) {
        int length = prices.length;
        if(length == 0){
            return 0;
        }
        k = Math.min(k, length / 2);
        int[][] buy = new int[length][k + 1];
        int[][] sell = new int[length][k + 1];

        // init
        buy[0][0] = -prices[0];
        sell[0][0] = 0;
        for (int i = 1; i <= k; i++) {
            buy[0][i] = sell[0][i] = Integer.MIN_VALUE / 2;
        }
        for (int i = 1; i < length; i++) {
            // 第i天买一次的最佳方案
            buy[i][0] = Math.max(buy[i - 1][0], sell[i - 1][0] - prices[i]);
            for (int j = 1; j <= k; j++) {
                // 第i天买入 j 次的最佳方案 （卖第i - 1天j次 ｜ 买第i - 1天j - 1次 + 当前价格）
                sell[i][j] = Math.max(sell[i - 1][j], buy[i - 1][j - 1] + prices[i]);
                // 第i天买j + 1次的最佳方案（买第i-1天j + 1次 ｜ 卖第i-1天j次 - 当天价格）
                buy[i][j] = Math.max(buy[i - 1][j], sell[i - 1][j] - prices[i]);
            }
        }
        return Arrays.stream(sell[length - 1]).max().getAsInt();
    }
}
