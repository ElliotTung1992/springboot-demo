package com.github.dge.algorithm.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dge
 * @version 1.0
 * @date 2022-03-09 9:56 上午
 * https://leetcode.com/problems/partition-labels/description/
 * https://leetcode-cn.com/problems/partition-labels/description/
 * 763. 划分字母区间
 * 这道题目我觉得不是很好，没有说清楚具体规则，什么时候切换下一个集合
 * String s = "ababcbacadefegdehijhklijc";
 * 官方的答案就是25，不科学
 */
public class PartitionLabels {

    public static void main(String[] args) {
        //String s = "eccbbbbdec";
        //String s = "caedbdedda";
        String s = "zababcbacadefegdehijhklijz";

        PartitionLabels partitionLabels = new PartitionLabels();
        List<Integer> integers = partitionLabels.partitionLabels2(s);
        for (Integer integer : integers) {
            System.out.println(integer);
        }
    }

    /**
     * 核心思想：
     * 首先记录26个小写字符的对应最大index
     * 然后遍历每个字符
     *  当前字符最大index和当前字符集最大index做比较取最大值
     *  如果当前index==当前字符集最大的index则寻找下一个区间
     * 这个答案有一个致命的缺点
     * String s = "ababcbacadefegdehijhklijc";
     * 只要在官方的例子中最前面和最后面面加一个字符'z'；
     * String s = "zababcbacadefegdehijhklijcz";
     * 则返回26,则不科学
     */
    public List<Integer> partitionLabels2(String s) {
        int[] last = new int[26];
        int length = s.length();
        for (int i = 0; i < length; i++) {
            last[s.charAt(i) - 'a'] = i;
        }
        List<Integer> partition = new ArrayList<Integer>();
        int start = 0, end = 0;
        for (int i = 0; i < length; i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);
            if (i == end) {
                partition.add(end - start + 1);
                start = end + 1;
            }
        }
        return partition;
    }

    public List<Integer> partitionLabels(String s) {
        char[] chars = s.toCharArray();
        Integer startIndex = 0;
        List<Integer> list  = new ArrayList<>();
        while (startIndex < s.length()){
            Map<String, Integer> map = new HashMap<>();
            Integer maxCount = 0;
            Integer maxIndex = 0;
            String maxStr = String.valueOf(chars[startIndex]);
            for (int i = startIndex; i < s.length(); i++) {
                MaxValue maxValue = findMost(map, String.valueOf(chars[i]));
                // 如果相等
                if(maxStr.equals(maxValue.getKey())){
                    if(maxValue.getCount() > maxCount){
                        maxIndex = i;
                        maxCount = maxValue.getCount();
                    }
                    else if(i == s.length() -1  && s.length() -1 - maxIndex < 10){
                        maxIndex = i;
                    }
                }else{
                    // 如果不相等
                    maxStr = maxValue.getKey();
                    maxIndex = i;
                    maxCount = maxValue.getCount();
                }

            }
            list.add(maxIndex + 1 - startIndex);
            startIndex = maxIndex + 1;
        }
        return list;
    }

    public MaxValue findMost(Map<String, Integer> map, String s){
        map.put(s, map.getOrDefault(s, 0) + 1);
        String mostStr = "";
        Integer mostCount = Integer.MIN_VALUE;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Integer value = entry.getValue();
            if(value >= mostCount){
                mostCount = entry.getValue();
                mostStr = entry.getKey();
            }
        }
        MaxValue maxValue = new MaxValue();
        maxValue.setKey(mostStr);
        maxValue.setCount(mostCount);
        return maxValue;
    }

    class MaxValue{
        private String key;
        private Integer count;

        public String getKey() {
            return this.key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Integer getCount() {
            return this.count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }
    }
}
