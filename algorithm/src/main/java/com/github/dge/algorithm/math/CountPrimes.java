package com.github.dge.algorithm.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dge
 * @version 1.0
 * @date 2022-05-18 9:46 上午
 * https://leetcode.com/problems/count-primes/description/
 * https://leetcode.cn/problems/count-primes/description/
 * 计算质数
 */
public class CountPrimes {

    public static void main(String[] args) {
        CountPrimes countPrimes = new CountPrimes();
        int count = countPrimes.countPrimes(499979);
        System.out.println(count);
    }

    public int countPrimes2(int n) {
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            if (isPrime[i] == 1) {
                ans += 1;
                if ((long) i * i < n) {
                    for (int j = i * i; j < n; j += i) {
                        isPrime[j] = 0;
                    }
                }
            }
        }
        return ans;
    }

    public int countPrimes(int n) {
        List<Integer> primes = new ArrayList<Integer>();
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        for (int i = 2; i < n; ++i) {
            if (isPrime[i] == 1) {
                primes.add(i);
            }
            for (int j = 0; j < primes.size() && i * primes.get(j) < n; ++j) {
                isPrime[i * primes.get(j)] = 0;
                if (i % primes.get(j) == 0) {
                    break;
                }
            }
        }
        return primes.size();
    }
}
