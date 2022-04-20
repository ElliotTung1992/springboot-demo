package com.github.dge.algorithm.contest.date20220416;

import java.util.PriorityQueue;

/**
 * @author dge
 * @version 1.0
 * @date 2022-04-18 11:36 上午
 * https://leetcode-cn.com/problems/maximum-score-of-a-node-sequence/
 */
public class MaximumScoreOfANodeSequence {

    public static void main(String[] args) {
        int[] scores = {5,2,9,8,10};
        int[][] edges = {{0,1},{1,2},{2,3},{0,2},{1,3},{2,4}};

        MaximumScoreOfANodeSequence maximumScoreOfANodeSequence = new MaximumScoreOfANodeSequence();
        int i = maximumScoreOfANodeSequence.maximumScore(scores, edges);
        System.out.println(i);
    }

    public int maximumScore(int[] scores, int[][] edges) {
        PriorityQueue<Integer>[] queue = new PriorityQueue[scores.length];
        for (int i = 0; i < scores.length; i++) {
            queue[i] = new PriorityQueue<>((o, p) -> scores[o] - scores[p]);
        }
        for (int[] edge : edges) {
            queue[edge[0]].offer(edge[1]);
            queue[edge[1]].offer(edge[0]);
            if (queue[edge[0]].size() > 3) {
                queue[edge[0]].poll();
            }
            if (queue[edge[1]].size() > 3) {
                queue[edge[1]].poll();
            }
        }
        int max = -1;
        for (int[] edge : edges) {
            for (int i : queue[edge[0]]) {
                for (int j : queue[edge[1]]) {
                    if (i != edge[1] && j != edge[0] && j != i) {
                        System.out.println(i + " " + j + " " + edge[0] + " " + edge[1]);
                        int sum = scores[edge[0]] + scores[edge[1]] + scores[i] + scores[j];
                        System.out.println(sum);
                        max = Math.max(max, sum);
                    }
                }
            }
        }
        return max;
    }
}
