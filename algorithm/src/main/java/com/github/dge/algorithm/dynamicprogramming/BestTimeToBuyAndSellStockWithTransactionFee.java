package com.github.dge.algorithm.dynamicprogramming;

/**
 * @author dge
 * @version 1.0
 * @date 2022-05-10 9:43 上午
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/description/
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/description/
 * 买卖股票的最佳时机含手续费
 */
public class BestTimeToBuyAndSellStockWithTransactionFee {

    public static void main(String[] args) {
        BestTimeToBuyAndSellStockWithTransactionFee  test = new BestTimeToBuyAndSellStockWithTransactionFee();
        int[] prices = {1, 3, 2, 6, 8, 4, 9};
        int fee = 2;
        int i = test.maxProfit(prices, fee);
        System.out.println(i);
    }

    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int profit = 0;
        int bug = prices[0] + fee;
        for (int i = 1; i < n; i++) {
            if(prices[i] + fee < bug){
                bug = prices[i] + fee;
            }else if(prices[i] > bug){
                profit += prices[i] - bug;
                bug = prices[i];
            }
        }
        return profit;
    }

    public int maxProfit2(int[] prices, int fee) {
        int n = prices.length;
        int buy = -prices[0];
        int sell = 0;
        for (int i = 1; i < n; i++) {
            buy = Math.max(buy, sell - prices[i]);
            sell = Math.max(buy + prices[i] - fee, sell);
        }
        return sell;
    }
}
