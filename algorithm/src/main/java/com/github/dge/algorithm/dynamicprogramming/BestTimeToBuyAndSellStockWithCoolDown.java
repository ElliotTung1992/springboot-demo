package com.github.dge.algorithm.dynamicprogramming;

/**
 * @author dge
 * @version 1.0
 * @date 2022-05-09 9:38 上午
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/description/
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown/description/
 * 最佳买卖股票时机含冷冻期
 */
public class BestTimeToBuyAndSellStockWithCoolDown {

    public static void main(String[] args) {
        BestTimeToBuyAndSellStockWithCoolDown coolDown = new BestTimeToBuyAndSellStockWithCoolDown();
        int[] prices = {1,2,3,0,2};
        int i = coolDown.maxProfit(prices);
        System.out.println(i);
    }

    public int maxProfit2(int[] prices) {
        int length = prices.length;
        int[][] bp = new int[length][3];

        bp[0][0] = -prices[0];
        for (int i = 1; i < length; i++) {
            bp[i][0] = Math.max(bp[i - 1][0], bp[i - 1][2] - prices[i]);
            bp[i][1] = bp[i - 1][0] + prices[i];
            bp[i][2] = Math.max(bp[i - 1][1], bp[i - 1][2]);
        }
        return Math.max(bp[length -1][1], bp[length - 1][2]);
    }

    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int n = prices.length;
        int f0 = -prices[0];
        int f1 = 0;
        int f2 = 0;
        for (int i = 1; i < n; ++i) {
            int newf0 = Math.max(f0, f2 - prices[i]);
            int newf1 = f0 + prices[i];
            int newf2 = Math.max(f1, f2);
            f0 = newf0;
            f1 = newf1;
            f2 = newf2;
        }

        return Math.max(f1, f2);
    }
}
