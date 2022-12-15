package com.github.dge.everyday;

import java.util.HashSet;
import java.util.Set;

public class CheckIfTheSentenceIsPangram {

    public static void main(String[] args) {
        System.out.println('z' - 'a');
    }

    public boolean checkIfPangram(String sentence) {
        Set<Integer> set = new HashSet<>();
        char[] chars = sentence.toCharArray();
        for (char aChar : chars) {
            set.add(aChar - 'a');
        }
        return set.size() == 26;
    }
}
