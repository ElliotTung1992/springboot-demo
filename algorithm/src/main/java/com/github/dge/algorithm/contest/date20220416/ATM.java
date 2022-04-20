package com.github.dge.algorithm.contest.date20220416;

/**
 * @author dge
 * @version 1.0
 * @date 2022-04-17 9:52 下午
 * https://leetcode-cn.com/problems/design-an-atm-machine/
 * 设计一个 ATM 机器
 */
public class ATM {

    public static void main(String[] args) {
        ATM atm = new ATM();
        int[] banknotesCount = {0,0,1,2,1};
        atm.deposit(banknotesCount);
        atm.withdraw(600);
        int[] banknotesCountTwo = {0,1,0,1,1};
        atm.deposit(banknotesCountTwo);
        atm.withdraw(600);
        atm.withdraw(550);
    }

    int[] banknote = {20, 50, 100, 200, 500};
    long[] stock = {0, 0, 0, 0, 0};
    long total;

    public ATM() {

    }

    public void deposit(int[] banknotesCount) {
        for (int i = 0; i < banknotesCount.length; i++) {
            stock[i] += banknotesCount[i];
            total += banknote[i] * banknotesCount[i];
        }
    }

    public int[] withdraw(int amount) {
        int temp = amount;
        int[] returnBanknote = {0, 0, 0, 0, 0};
        for (int i = banknote.length - 1; i >= 0; i--) {
            if(banknote[i] <= amount){
                long count = amount / banknote[i];
                count = Math.min(stock[i], count);
                amount -= banknote[i] * count;
                returnBanknote[i] = (int)count;
                if(amount == 0){
                    break;
                }
            }
        }
        if(amount > 0){
            int[] error = {-1};
            return error;
        }
        for (int i = 0; i < returnBanknote.length; i++) {
            stock[i] -= returnBanknote[i];
        }
        total -= temp;
        return returnBanknote;
    }

    /*private long[] count = new long[5];

    public void deposit(int[] banknotesCount) {
        for (int i = 0; i < 5; i++) {
            count[i] += banknotesCount[i];
        }
    }

    public int[] withdraw(int amount) {
        int[] result = new int[5];
        for (int i = 4; i >= 0; i--) {
            amount -= new int[] { 20, 50, 100, 200, 500 }[i]
                    * (result[i] = (int) Math.min(count[i], amount / new int[] { 20, 50, 100, 200, 500 }[i]));
        }
        if (amount > 0) {
            return new int[] { -1 };
        }
        for (int i = 0; i < 5; i++) {
            count[i] -= result[i];
        }
        return result;
    }*/
}
