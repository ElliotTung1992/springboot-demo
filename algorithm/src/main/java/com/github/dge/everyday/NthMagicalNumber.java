package com.github.dge.everyday;

public class NthMagicalNumber {

    public static void main(String[] args) {
        NthMagicalNumber nthMagicalNumber = new NthMagicalNumber();
        int i = nthMagicalNumber.nthMagicalNumber(4,
                2,
                3);
        System.out.println(i);
    }

    static final int MOD = 1000000007;

    public int nthMagicalNumber(int n, int a, int b) {
        // 设置最小值
        long l = Math.min(a, b);
        // 设置最右边
        // 极端情况如果(a,b)大值刚好是小值的倍数，则最右边就是n * Math.min(a, b);
        // 如果不是，则最大值肯定小于n * Math.min(a, b);
        long r = (long) n * Math.min(a, b);
        // 获取最小公倍数
        int c = lcm(a, b);
        while (l <= r) {
            long mid = (l + r) / 2;
            long cnt = mid / a + mid / b - mid / c;
            if (cnt >= n) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        // 相等的时候会减一次，所以最后还需要加一次
        return (int) ((r + 1) % MOD);
    }

    // 获取最小公倍数
    public int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    // 辗转相除法求最大公约数
    public int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }


}
