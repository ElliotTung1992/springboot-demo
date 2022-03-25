package com.github.dge.algorithm.breadthfirstsearch;

import java.util.*;

/**
 * @author dge
 * @version 1.0
 * @date 2022-03-25 9:44 上午
 * https://leetcode.com/problems/word-ladder/description/
 * https://leetcode-cn.com/problems/word-ladder/description/
 * 单词接龙
 */
public class WordLadder {

    public static void main(String[] args) {
        WordLadder wordLadder = new WordLadder();
        String beginWord = "leet";
        String endWord = "code";
        List<String> wordList = new ArrayList<>();
        wordList.add("lest");
        wordList.add("leet");
        wordList.add("lose");
        wordList.add("code");
        wordList.add("lode");
        wordList.add("robe");
        wordList.add("lost");
        int i = wordLadder.ladderLength(beginWord, endWord, wordList);
        System.out.println(i);
    }

    Map<String, Integer> wordId = new HashMap<String, Integer>();
    List<List<Integer>> edge = new ArrayList<List<Integer>>();
    int nodeNum = 0;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        for (String word : wordList) {
            addEdge(word);
        }
        addEdge(beginWord);
        if (!wordId.containsKey(endWord)) {
            return 0;
        }
        int[] dis = new int[nodeNum];
        Arrays.fill(dis, Integer.MAX_VALUE);
        int beginId = wordId.get(beginWord), endId = wordId.get(endWord);
        dis[beginId] = 0;

        Queue<Integer> que = new LinkedList<Integer>();
        que.offer(beginId);
        while (!que.isEmpty()) {
            int x = que.poll();
            if (x == endId) {
                return dis[endId] / 2 + 1;
            }
            for (int it : edge.get(x)) {
                if (dis[it] == Integer.MAX_VALUE) {
                    dis[it] = dis[x] + 1;
                    que.offer(it);
                }
            }
        }
        return 0;
    }

    public void addEdge(String word) {
        addWord(word);
        int id1 = wordId.get(word);
        char[] array = word.toCharArray();
        int length = array.length;
        for (int i = 0; i < length; ++i) {
            char tmp = array[i];
            array[i] = '*';
            String newWord = new String(array);
            addWord(newWord);
            int id2 = wordId.get(newWord);
            edge.get(id1).add(id2);
            edge.get(id2).add(id1);
            array[i] = tmp;
        }
    }

    public void addWord(String word) {
        if (!wordId.containsKey(word)) {
            wordId.put(word, nodeNum++);
            edge.add(new ArrayList<Integer>());
        }
    }

    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)){
            return 0;
        }
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int deapLength = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            deapLength++;
            while (size-- > 0){
                String poll = queue.poll();
                if(poll.equals(endWord)){
                    return deapLength;
                }
                int cs = wordList.size();
                for (int i = cs - 1; i >= 0; i--) {
                    String s = wordList.get(i);
                    if(compareLength(poll, s)){
                        queue.add(s);
                        wordList.remove(s);
                    }
                }
            }
        }
        return 0;
    }

    private boolean compareLength(String left, String right){
        char[] lc = left.toCharArray();
        char[] rc = right.toCharArray();
        int count = 0;
        for (int i = 0; i < lc.length; i++) {
            if(lc[i] != rc[i]){
                count ++;
            }
            if(count > 1){
                return false;
            }
        }
        return true;
    }
}
