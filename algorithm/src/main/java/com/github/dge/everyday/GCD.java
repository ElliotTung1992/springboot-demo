package com.github.dge.everyday;

/**
 * 求最大
 */
public class GCD {

    public static void main(String[] args) {
        GCD gcd = new GCD();
        int gcd1 = gcd.gcd(25, 5);
        System.out.println(gcd1);

        int gcd2 = gcd.gcd2(25, 15);
        System.out.println(gcd2);
    }

    /**
     * 转辗相除法
     * greatest common divisor
     */
    private int gcd(int a, int b){
        return b != 0 ? gcd(b, a % b): a;
    }

    /**
     * 九章算法
     */
    private int gcd2(int a, int b){
        if(a == b){
            return a;
        }
        if(a > b){
            return gcd2(a - b, b);
        }else{
            return gcd2(b - a, a);
        }
    }
}
