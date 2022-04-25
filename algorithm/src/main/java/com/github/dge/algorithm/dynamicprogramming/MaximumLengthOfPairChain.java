package com.github.dge.algorithm.dynamicprogramming;

import java.util.Arrays;

/**
 * @author dge
 * @version 1.0
 * @date 2022-04-25 9:55 上午
 * https://leetcode.com/problems/maximum-length-of-pair-chain/description/
 * https://leetcode-cn.com/problems/maximum-length-of-pair-chain/description/
 * 最长数对链
 */
public class MaximumLengthOfPairChain {

    public static void main(String[] args) {
        MaximumLengthOfPairChain maximumLengthOfPairChain = new MaximumLengthOfPairChain();
        int[][] r = {{1,2}, {7,8}, {4,5}};
        int longestChain = maximumLengthOfPairChain.findLongestChain(r);
        System.out.println(longestChain);
    }

    public int findLongestChain(int[][] pairs) {
        int length = pairs.length;
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        /*PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int[] pair : pairs) {
            queue.add(pair);
        }
        for (int i = 0; i < length; i++) {
            pairs[i] = queue.poll();
        }*/
        int[] count = new int[length];
        for (int i = 0; i < length; i++) {
            int cc = 1;
            for (int j = i - 1; j >= 0; j--) {
                if(pairs[i][0] > pairs[j][1]){
                    cc = count[j] + 1;
                    break;
                }
            }
            count[i] = cc;
        }
        return count[length - 1];
    }

    public int findLongestChain3(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
        int cur = Integer.MIN_VALUE, ans = 0;
        for (int[] pair: pairs) if (cur < pair[0]) {
            cur = pair[1];
            ans++;
        }
        return ans;
    }

    public int findLongestChain2(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        int N = pairs.length;
        int[] dp = new int[N];
        Arrays.fill(dp, 1);

        for (int j = 1; j < N; ++j) {
            for (int i = 0; i < j; ++i) {
                if (pairs[i][1] < pairs[j][0])
                    dp[j] = Math.max(dp[j], dp[i] + 1);
            }
        }

        int ans = 0;
        for (int x: dp) if (x > ans) ans = x;
        return ans;
    }
}
