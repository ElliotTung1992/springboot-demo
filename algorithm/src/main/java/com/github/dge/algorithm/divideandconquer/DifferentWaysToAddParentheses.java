package com.github.dge.algorithm.divideandconquer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dge
 * @version 1.0
 * @date 2022-03-18 11:12 上午
 * https://leetcode-cn.com/problems/different-ways-to-add-parentheses/description/
 * https://leetcode.com/problems/different-ways-to-add-parentheses/description/
 * 为运算表达式设计优先级
 */
public class DifferentWaysToAddParentheses {

    public static void main(String[] args) {
        //String expression = "2-1-1";
        String expression = "2*3-4*5";
        //String expression = "11";
        DifferentWaysToAddParentheses parentheses = new DifferentWaysToAddParentheses();
        List<Integer> integers = parentheses.diffWaysToCompute(expression);
        System.out.println(integers);
    }

    /**
     * 核心思想：
     * 最小颗粒度是一个数字
     * 永远计算的字符串的left和right
     */
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if(c == '+' || c == '-' || c == '*'){
                List<Integer> lefts = diffWaysToCompute(input.substring(0, i));
                List<Integer> rights = diffWaysToCompute(input.substring(i + 1));
                for (Integer left : lefts) {
                    for (Integer right : rights) {
                        switch (c){
                            case '+':
                                resultList.add(left + right);
                                break;
                            case '-':
                                resultList.add(left - right);
                                break;
                            case '*':
                                resultList.add(left * right);
                                break;
                        }
                    }
                }
            }
        }
        if(resultList.size() == 0){
            resultList.add(Integer.valueOf(input));
        }
        return resultList;
    }

    /**
     * 核心思想：
     * 先把字符串转换成List
     * 使用分治思想开始计算
     * 当集合长度是1或者3时为最小单元进行计算
     * 遍历所有可能性进行计算
     */
    public List<Integer> diffWaysToCompute2(String expression) {
        char[] chars = expression.toCharArray();
        List<String> dataList = new ArrayList<>();
        Integer p = null;
        for (char aChar : chars) {
            if(aChar == '+' || aChar == '-' || aChar == '*'){
                dataList.add(String.valueOf(p));
                dataList.add(String.valueOf(aChar));
                p = null;
            }else{
                if(p == null){
                    p = Integer.valueOf(String.valueOf(aChar));
                }else{
                    p = p * 10 + Integer.valueOf(String.valueOf(aChar));
                }
            }
        }
        dataList.add(String.valueOf(p));
        return calculationList(dataList, 0, dataList.size() - 1);
    }

    public List<Integer> calculationList(List<String> dataList, int start, int end){
        List<Integer> list = new ArrayList<>();
        if(start == end){
            list.add(Integer.valueOf(dataList.get(start)));
            return list;
        }else if(end - start == 2){
            Integer startInt = Integer.valueOf(dataList.get(start));
            Integer endInt = Integer.valueOf(dataList.get(end));
            list.add(calculation(startInt, dataList.get(start + 1), endInt));
            return list;
        }
        for (int i = start; i <= end; i++) {
            String s = dataList.get(i);
            if(s.equals("+")  || s.equals("-")  || s.equals("*")){
                List<Integer> integers = calculationList(dataList, start, i - 1);
                List<Integer> integers1 = calculationList(dataList, i + 1, end);
                for (Integer integer : integers) {
                    for (Integer integer1 : integers1) {
                        list.add(calculation(integer, s, integer1));
                    }
                }
            }
        }
        return list;
    }

    public Integer calculation(int left, String operate, int right){
        if("+".equals(operate)){
            return left + right;
        }else if("-".equals(operate)){
            return left - right;
        }else if("*".equals(operate)){
            return left * right;
        }
        return null;
    }


}
