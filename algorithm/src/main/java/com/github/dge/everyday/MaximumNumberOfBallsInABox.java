package com.github.dge.everyday;

import java.util.HashMap;
import java.util.Map;

public class MaximumNumberOfBallsInABox {

    public static void main(String[] args) {
        MaximumNumberOfBallsInABox maximumNumberOfBallsInABox = new MaximumNumberOfBallsInABox();
        maximumNumberOfBallsInABox.countBalls(11, 74);
    }

    public int countBalls(int lowLimit, int highLimit) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = lowLimit; i <= highLimit; i++) {
            int sum = 0;
            int temp = i;
            while (temp != 0){
                sum += temp % 10;
                temp /= 10;
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return map.values().stream().sorted((a, b) -> b - a).findFirst().get();
    }
}
