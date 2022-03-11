package com.github.dge.algorithm.binarysearch;

/**
 * @author dge
 * @version 1.0
 * @date 2022-03-11 9:54 上午
 * https://leetcode.com/problems/find-smallest-letter-greater-than-target/description/
 * https://leetcode-cn.com/problems/find-smallest-letter-greater-than-target/description/
 * 寻找比目标字母大的最小字母
 */
public class FindSmallestLetterGreaterThanTarget {

    public static void main(String[] args) {
        char[] letters = {'c', 'f', 'j'};
        char target = 'a';
        FindSmallestLetterGreaterThanTarget thanTarget = new FindSmallestLetterGreaterThanTarget();
        char c = thanTarget.nextGreatestLetter3(letters, target);
        System.out.println(c);
    }

    /**
     * 核心思想：
     * 因为数组是非递减有序数组
     * 我们查找比目标字符大的最小字符
     * 我们只需要遍历比较当前值减取目标值大于0的第一个数就可以了
     */
    public char nextGreatestLetter(char[] letters, char target) {
        for (char letter : letters) {
            if(letter - target > 0){
                return letter;
            }
        }
        return letters[0];
    }

    /**
     * 核心思想：
     * 和上面类似
     */
    public char nextGreatestLetter2(char[] letters, char target) {
        boolean[] isExist = new boolean[26];
        for (char letter : letters) {
            isExist[letter - 'a'] = true;
        }
        while (true){
            target++;
            if(target > 'z'){
                return letters[0];
            }
            if(isExist[target - 'a'] == true){
                return target;
            }
        }
    }

    /**
     * 典型的二分查找法
     */
    public char nextGreatestLetter3(char[] letters, char target) {
        int l = 0, r = letters.length - 1;
        char result = letters[0];
        while (l <= r){
            int mid = l + (r - l)/2;
            if(letters[mid] <= target){
                l = mid + 1;
            }else{
                r = mid - 1;
                result = letters[mid];
            }
        }
        return result;
    }
}
