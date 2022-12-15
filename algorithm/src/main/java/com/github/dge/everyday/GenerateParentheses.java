package com.github.dge.everyday;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

    public static void main(String[] args) {
        GenerateParentheses generateParentheses = new GenerateParentheses();
        List<String> strings = generateParentheses.generateParenthesis(3);
        System.out.println(strings);
    }

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append('(');
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    /*public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generate(new char[n * 2], 0, result);
        return result;
    }

    private void generate(char[] contain, int position, List<String> result){
        if(position == contain.length){
            if(validate(contain)){
                result.add(new String(contain));
            }
        }else{
            contain[position] = '(';
            generate(contain, position + 1, result);
            contain[position] = ')';
            generate(contain, position + 1, result);
        }
    }

    private boolean validate(char[] contain){
        int count = 0;
        for (char c : contain) {
            if('(' == c){
                count++;
            }else{
                count--;
            }
            if(count < 0){
                return false;
            }
        }
        if(count == 0){
            return true;
        }
        return false;
    }*/
}
