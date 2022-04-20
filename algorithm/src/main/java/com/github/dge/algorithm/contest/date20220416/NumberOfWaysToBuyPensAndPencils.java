package com.github.dge.algorithm.contest.date20220416;

/**
 * @author dge
 * @version 1.0
 * @date 2022-04-17 9:39 下午
 */
public class NumberOfWaysToBuyPensAndPencils {

    public static void main(String[] args) {
        NumberOfWaysToBuyPensAndPencils test = new NumberOfWaysToBuyPensAndPencils();
        long count = test.waysToBuyPensPencils(20, 10, 5);
        System.out.println(count);
    }

    public long waysToBuyPensPencils(int total, int cost1, int cost2) {
        long count = 0;
        for (; total >= 0; total -= cost1) {
            count += 1 + total / cost2;
        }
        return count;
    }
}
