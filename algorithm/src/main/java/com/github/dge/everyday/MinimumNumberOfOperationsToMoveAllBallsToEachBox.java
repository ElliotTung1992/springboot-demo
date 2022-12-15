package com.github.dge.everyday;

public class MinimumNumberOfOperationsToMoveAllBallsToEachBox {

    public static void main(String[] args) {
        MinimumNumberOfOperationsToMoveAllBallsToEachBox method = new MinimumNumberOfOperationsToMoveAllBallsToEachBox();
        method.minOperations("001011");
    }

    public int[] minOperations(String c) {
        char[] chars = c.toCharArray();
        int length = chars.length;
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            char aChar = chars[i];
            if(aChar == '0'){
                continue;
            }
            for (int j = 0; j < length; j++) {
                result[j] += Math.abs(i - j);
            }
        }
        return result;
    }
}
