package com.github.dge.algorithm.dynamicprogramming;

/**
 * @author dge
 * @version 1.0
 * @date 2022-05-11 9:48 上午
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/description/
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii/description/
 * 123. 买卖股票的最佳时机 III
 */
public class BestTimeToBuyAndSellStockIII {

    public static void main(String[] args) {
        BestTimeToBuyAndSellStockIII test = new BestTimeToBuyAndSellStockIII();
        int[] prices = {3,5,4,3,4,0};
        int i = test.maxProfit(prices);
        System.out.println(i);
    }

    public int maxProfit(int[] prices) {
        int n = prices.length;
        int buy1 = prices[0], sell1 = 0;
        int buy2 = prices[0], sell2 = 0;
        for (int i = 1; i < n; ++i) {
            // 买入的最低成本
            buy1 = Math.min(buy1, prices[i]);
            // 卖出的最高价格
            sell1 = Math.max(sell1, prices[i] - buy1);
            // 买入的最低成本 - max第一次卖出的利润
            buy2 = Math.min(buy2, prices[i] - sell1);
            // 卖出的最高价格 - min第二次买入的成本
            sell2 = Math.max(sell2, prices[i] - buy2);
        }
        return sell2;
    }
}
