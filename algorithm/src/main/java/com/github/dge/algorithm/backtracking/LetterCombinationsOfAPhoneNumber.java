package com.github.dge.algorithm.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dge
 * @version 1.0
 * @date 2022-04-01 9:34 上午
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/description/
 * 电话号码的字母组合
 */
public class LetterCombinationsOfAPhoneNumber {

    String[][] combination = {{},{"a", "b", "c"},{"d","e","f"},{"g","h","i"},{"j","k","l"},
            {"m","n","o"}, {"p","q","r","s"}, {"t","u","v"}, {"w","x","y","z"}};

    public static void main(String[] args) {
        LetterCombinationsOfAPhoneNumber letterCombinationsOfAPhoneNumber = new LetterCombinationsOfAPhoneNumber();
        List<String> strings = letterCombinationsOfAPhoneNumber.letterCombinations("23");
        System.out.println(strings);
    }

    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<String>();
        if (digits.length() == 0) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    public void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
        if(index == digits.length()){
            combinations.add(combination.toString());
        }else{
            char c = digits.charAt(index);
            String s = phoneMap.get(c);
            int length = s.length();
            for (int i = 0; i < length; i++) {
                combination.append(s.charAt(i));
                backtrack(combinations, phoneMap, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }

    public List<String> letterCombinations2(String digits) {
        if(digits.length() == 0){
            return new ArrayList<>();
        }
        char[] chars = digits.toCharArray();
        List<String> list = new ArrayList<>();
        list.add("");
        for (char aChar : chars) {
            Integer index = Integer.valueOf(String.valueOf(aChar));
            String[] strArr = combination[index-1];
            list = calculation(list, strArr);
        }
        return list;
    }

    private List<String> calculation(List<String> strList, String[] strArr){
        List<String> list = new ArrayList<>();
        for (String s : strList) {
            for (String s1 : strArr) {
                list.add(s.concat(s1));
            }
        }
        return list;
    }
}
