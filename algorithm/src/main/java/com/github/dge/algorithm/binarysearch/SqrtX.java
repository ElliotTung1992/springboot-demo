package com.github.dge.algorithm.binarysearch;

/**
 * @author dge
 * @version 1.0
 * @date 2022-03-10 9:46 上午
 * https://leetcode.com/problems/sqrtx/description/
 * https://leetcode-cn.com/problems/sqrtx/description/
 */
public class SqrtX {

    public static void main(String[] args) {
        System.out.println(1e-7);
        SqrtX sqrtX = new SqrtX();
        int i = sqrtX.mySqrt4(8);
        System.out.println(i);

//        double sqrt = Math.sqrt(2147483647);
//        System.out.println(sqrt);
    }

    /**
     * 核心思想：
     * y * y == x || (y * g < x && (y+1)(y+1) > x)
     * 注意点：
     * 边界值及数据溢出问题
     */
    public int mySqrt(int x) {
        return getDate(x, 0, x);
    }

    public int getDate(int x, int left, int right){
        if(x == 1){
            return 1;
        }
        if(right - left == 1 && (long)left * left < x && (long)right * right > x){
            return left;
        }
        int mid = left + (right - left)/2;
        long midTwo = (long)mid * mid;
        if(midTwo > x){
            return getDate(x, left, mid);
        }else if(midTwo < x){
            return getDate(x, mid, right);
        }else{
            return mid;
        }
    }

    /**
     * 调用函数解决，没有参考价值
     */
    public int mySqrt2(int x) {
        if (x == 0) {
            return 0;
        }
        int ans = (int) Math.exp(0.5 * Math.log(x));
        return (long) (ans + 1) * (ans + 1) <= x ? ans + 1 : ans;
    }

    /**
     * 二分查找法比我的更精简
     * 1. 不需要对边界值1做计算
     * 2. 通过一个变量记录中间值
     *    只要mid * mid <= x就可以了
     */
    public int mySqrt3(int x) {
        int l = 0, r = x, ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    // todo 不太懂
    public int mySqrt4(int x) {
        if (x == 0) {
            return 0;
        }

        double C = x, x0 = x;
        while (true) {
            double xi = 0.5 * (x0 + C / x0);
            // 1e-7 = 0.0000001
            if (Math.abs(x0 - xi) < 1e-7) {
                break;
            }
            x0 = xi;
        }
        return (int) x0;
    }
}
