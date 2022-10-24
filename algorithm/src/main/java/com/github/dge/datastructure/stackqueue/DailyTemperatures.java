package com.github.dge.datastructure.stackqueue;

import java.util.Deque;
import java.util.LinkedList;

public class DailyTemperatures {

    public static void main(String[] args) {
        DailyTemperatures dailyTemperatures = new DailyTemperatures();
        int[] temperatures = {73,74,75,71,69,72,76,73};
        dailyTemperatures.dailyTemperatures(temperatures);
    }

    public int[] dailyTemperatures(int[] temperatures) {
        int length = temperatures.length;
        Deque<Integer> stack = new LinkedList<>();
        int result[] = new int[length];
        for (int i = 0; i < length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]){
                Integer pop = stack.pop();
                result[pop] = i - pop;
            }
            stack.push(i);
        }
        return result;
    }

    /*public int[] dailyTemperatures(int[] temperatures) {
        int length = temperatures.length;
        int[] result = new int[length];
        for (int i = length - 2; i >= 0; i--) {
            if(temperatures[i] < temperatures[i + 1]){
                result[i] = 1;
            }else if(temperatures[i] == temperatures[i + 1]){
                if(result[i + 1] != 0){
                    result[i] = result[i + 1] + 1;
                }
            }else {
                for (int j = i + 1; j < length; j++) {
                    if(temperatures[j] > temperatures[i]){
                        result[i] = j - i;
                        break;
                    }
                }
            }

        }
        return result;
    }*/


    /*public int[] dailyTemperatures(int[] temperatures) {
        int length = temperatures.length;
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if(temperatures[j] > temperatures[i]){
                    result[i] = j - i;
                    break;
                }
            }
        }
        return result;
    }*/

}
