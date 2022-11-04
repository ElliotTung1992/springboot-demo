package com.github.dge.datastructure.string;

public class PalindromeNumber {

    public static void main(String[] args) {
        // % 取余数
        System.out.println(123 % 10);

        PalindromeNumber p = new PalindromeNumber();
        p.isPalindrome(123);
    }

    public boolean isPalindrome(int x) {
        if(x < 0 || (x != 0 && x % 10 == 0)){
            return false;
        }
        int comp = 0;
        while (x > comp){
            comp = comp * 10 + x % 10;
            x /= 10;
        }
        return x == comp || comp / 10 == x;
    }
}
