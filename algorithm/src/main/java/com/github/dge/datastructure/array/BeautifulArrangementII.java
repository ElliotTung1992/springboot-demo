package com.github.dge.datastructure.array;

public class BeautifulArrangementII {

    public static void main(String[] args) {
        BeautifulArrangementII beautifulArrangementII = new BeautifulArrangementII();
        beautifulArrangementII.constructArray(6, 3);
    }

    public int[] constructArray(int n, int k) {
        // 初始化结果集
        int[] answer = new int[n];
        // 当前下标
        int idx = 0;
        // 这里做的一件事就是计算不需要参加计算的个数，并从1开始逐个递增
        // 如果k是需要结果集,那么我们就需要k + 1个参加下面的计算， 那么不需要计算的是n - k - 1个
        for (int i = 1; i < n - k; ++i) {
            answer[idx] = i;
            ++idx;
        }
        // i = n - k为第一个需要开始处理的数据
        // j = n为最大的数
        // index(i + 1) - index(i) = k
        // index(i) = n - k, index(i + 1) = n => n - n + k = k
        // index(i + 1) = n - k + 1 => n - (n - k + 1) = k - 1
        // ...
        // 取开头取末尾, i++ j-- 使差值越来越小
        for (int i = n - k, j = n; i <= j; ++i, --j) {
            // 取开头
            answer[idx] = i;
            ++idx;
            if (i != j) {
                // 取末尾
                answer[idx] = j;
                ++idx;
            }
        }
        return answer;
    }

    /*public int[] constructArray(int n, int k) {
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = i + 1;
        }
        for (int i = 1; i < n; i++) {
            if(Math.abs(result[i] - result[i - 1]) != k){
                for (int j = i + 1; j < n; j++) {
                    if(Math.abs(result[j] - result[i - 1]) == k){
                        int temp = result[j];
                        result[j] = result[i];
                        result[i] = temp;
                        break;
                    }
                }
            }
            k = k > 1? k -= 1: 1;
        }
        return result;
    }*/
}
