package com.github.dge.everyday;

import java.util.*;

public class LongestValidParentheses {

    public static void main(String[] args) {
        LongestValidParentheses longestValidParentheses = new LongestValidParentheses();
        int i = longestValidParentheses.longestValidParentheses(")()(())");
        System.out.println(i);
    }

    public int longestValidParentheses(String s) {
        int maxans = 0;
        Deque<Integer> stack = new LinkedList<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }

    /*public int longestValidParentheses(String s) {

        char[] chars = s.toCharArray();
        int length = chars.length, count = 0;
        int[] arr = new int[length];
        Deque<Integer> stack = new LinkedList<>();

        for (int i = 0; i < length; i++) {
            if(chars[i] == '('){
                count++;
                stack.addLast(i);
            }else{
                count--;
                if(count < 0){
                    count = 0;
                    while (!stack.isEmpty()){
                        stack.remove();
                    }
                }else{
                    Integer index = stack.removeLast();
                    arr[index] = 1;
                    arr[i] = 1;
                }
            }

        }
        int max = 0, per = 0;
        for (int i = 0; i < length; i++) {
            if(arr[i] == 0){
                max = Math.max(max, per / 2);
                per = 0;
            }else {
                per++;
            }
        }
        max = Math.max(max, per / 2);
        return max;
    }*/
}
