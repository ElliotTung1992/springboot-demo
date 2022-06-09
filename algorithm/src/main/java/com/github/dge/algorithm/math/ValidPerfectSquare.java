package com.github.dge.algorithm.math;

/**
 * @author dge
 * @version 1.0
 * @date 2022-06-09 11:09 上午
 * https://leetcode.com/problems/valid-perfect-square/description/
 * https://leetcode.cn/problems/valid-perfect-square/description/
 * 有效的完全平方数
 */
public class ValidPerfectSquare {

    public static void main(String[] args) {
        ValidPerfectSquare validPerfectSquare = new ValidPerfectSquare();
        boolean perfectSquare = validPerfectSquare.isPerfectSquare(2147483647);
        System.out.println(perfectSquare);
    }

    public boolean isPerfectSquare(int num) {
        return find(0, num,num);
    }

    private boolean find(int left, int right, int num){
        if((double)left * left == num || (double)right * right == num){
            return Boolean.TRUE;
        }
        int middle = (right + left) / 2;
        if((double)middle * middle == num){
            return Boolean.TRUE;
        }else if((double)middle * middle > num){
            right = middle;
        }else{
            left = middle;
        }
        if(left + 1 >= right){
            return Boolean.FALSE;
        }else{
            return find(left, right, num);
        }
    }
}
