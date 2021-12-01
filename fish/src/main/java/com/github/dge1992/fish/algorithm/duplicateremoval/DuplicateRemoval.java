package com.github.dge1992.fish.algorithm.duplicateremoval;

import com.google.common.collect.Lists;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * @author dge
 * @version 1.0
 * @date 2021-11-30 2:15 PM
 * 有序集合去重算法
 */
public class DuplicateRemoval {

    private static List<Integer> listTwo = new ArrayList<>();//Arrays.asList(1, 2, 3, 4, 5, 7, 8, 9, 11, 13, 15, 17, 19);
    private static List<Integer> listOne = new ArrayList<>();//Arrays.asList(0, 1, 2, 4, 6, 8, 10, 11, 12, 13, 14, 16, 18, 20, 21, 22);

    public static void main(String[] args) throws InterruptedException {

        Random random = new Random();
        int k = 0;
        for (int i = 0; i < 10100000; i++) {
            int j = random.nextInt(5);
            if(j == 0){
                j = 1;
            }
            k += j;
            listOne.add(k);
        }

        int q = 0;
        for (int i = 0; i < 10000000; i++) {
            int j = random.nextInt(5);
            if(j == 0){
                j = 1;
            }
            q += j;
            listTwo.add(q);
        }

        //System.out.println(listOne);
        //System.out.println(listTwo);

        //listOne = Arrays.asList(1, 4, 5, 6, 8, 12, 13, 17, 18, 20);
        //listTwo = Arrays.asList(2, 4, 6, 7, 8, 10, 11, 12, 15, 18);

        DuplicateRemoval duplicateRemoval = new DuplicateRemoval();
        //duplicateRemoval.caseOne();
        //TimeUnit.SECONDS.sleep(1);
        duplicateRemoval.caseTwo(listOne, listTwo);
        TimeUnit.SECONDS.sleep(1);
        duplicateRemoval.caseThree();
        //TimeUnit.SECONDS.sleep(1);
        //duplicateRemoval.caseFour();
    }

    private void caseOne(){
        long start = System.currentTimeMillis();
        List<Integer> listThree = new ArrayList<>();
        for (Integer one : listOne) {
            for (Integer two : listTwo) {
                if(one > two){
                    if(!listThree.contains(two)){
                        listThree.add(two);
                    }
                }else if(one.equals(two)){
                    listThree.add(two);
                    break;
                }else if(one < two){
                    if(!listThree.contains(one)){
                        listThree.add(one);
                        break;
                    }
                }
            }
            if(!listThree.contains(one)){
                listThree.add(one);
            }
        }
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(listThree);
    }

    private List<Integer> caseTwo(List<Integer> listOne, List<Integer> listTwo){
        long start = System.currentTimeMillis();
        List<Integer> listThree = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i != listOne.size() && j != listTwo.size()){
            Integer one = listOne.get(i);
            Integer two = listTwo.get(j);
            if(one > two){
                listThree.add(two);
                j++;
            }else if(one.equals(two)){
                listThree.add(two);
                i++;
                j++;
            }else if(one < two){
                listThree.add(one);
                i++;
            }
            if(i == listOne.size()){
                for (; j < listTwo.size(); j++) {
                    listThree.add(listTwo.get(j));
                }
            }
            if(j == listTwo.size()){
                for (; i < listOne.size(); i++) {
                    listThree.add(listOne.get(i));
                }
            }
        }
        System.out.println(System.currentTimeMillis() - start);
        //System.out.println(listThree);
        return listThree;
    }

    private void caseThree(){
        long start = System.currentTimeMillis();
        List<Integer> listThree = new ArrayList<>();
        List<List<Integer>> partition = Lists.partition(listOne, 5000000);
        int startIndex = 0;
        List<MergeListForkJoinTask> tasks = new ArrayList<>();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        for (List<Integer> integers : partition) {
            Integer maxInteger = integers.get(integers.size() - 1);
            Integer index = returnIndex(listTwo, maxInteger);
            List<Integer> subList = listTwo.subList(startIndex, index);
            MergeListForkJoinTask task = new MergeListForkJoinTask(integers, subList);
            forkJoinPool.submit(task);
            tasks.add(task);
            startIndex = index;
        }
        ForkJoinTask.invokeAll(tasks);
        for (MergeListForkJoinTask task : tasks) {
            listThree.addAll(task.join());
        }
        System.out.println(System.currentTimeMillis() - start);
        //System.out.println(listThree);
    }

    private Integer returnIndex(List<Integer> lists, Integer maxInteger){
        if(lists.contains(maxInteger)){
            return lists.indexOf(maxInteger) + 1;
        }
        for (Integer i : lists) {
            if(i > maxInteger){
                return lists.indexOf(i);
            }
        }
        return lists.size();
    }

    private void caseFour(){
        // 初始化byte数组
        byte[] oneBytes = new byte[(listOne.get(listOne.size() - 1))/8 + 1];
        byte[] twoBytes = new byte[(listTwo.get(listTwo.size() - 1))/8 + 1];
        for (Integer integer : listOne) {
            add(oneBytes, integer);
        }
        for (Integer integer : listTwo) {
            add(twoBytes, integer);
        }
        byte[] oneBytesReverse = reverse(oneBytes, (listOne.get(listOne.size() - 1)) / 8 + 1);
        byte[] twoBytesReverse = reverse(twoBytes, (listTwo.get(listTwo.size() - 1)) / 8 + 1);
        System.out.println(new BigInteger(1, oneBytesReverse).toString(10));
        System.out.println(new BigInteger(1, twoBytesReverse).toString(10));
        int value = new BigInteger(1, oneBytesReverse).intValue() & new BigInteger(1, twoBytesReverse).intValue();
        System.out.println(7699799 & 699326);
    }

    private byte[] reverse(byte a[], int n) {
        byte[] b = new byte[n];
        int j = n;
        for (int i = 0; i < n; i++) {
            b[j - 1] = a[i];
            j = j - 1;
        }
        return b;
    }

    /**
     * 获取在byte[]的位置
     */
    private int getIndex(int i){
        return i >>> 3;
    }

    /**
     * 获取在byte[index]的位置
     */
    private int getPosition(int i){
        return i & 0x07;
    }

    /**
     * 标志位置
     */
    private void add(byte[] b, int num){
        b[getIndex(num)] |= 1 << getPosition(num);
    }
}

class MergeListForkJoinTask extends RecursiveTask<List<Integer>> {

    private List<Integer> listTwo;
    private List<Integer> listOne;

    MergeListForkJoinTask(List<Integer> listOne, List<Integer> listTwo){
        this.listOne = listOne;
        this.listTwo = listTwo;
    }

    @Override
    protected List<Integer> compute() {
        List<Integer> listThree = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i != listOne.size() && j != listTwo.size()){
            Integer one = listOne.get(i);
            Integer two = listTwo.get(j);
            if(one > two){
                listThree.add(two);
                j++;
            }else if(one.equals(two)){
                listThree.add(two);
                i++;
                j++;
            }else if(one < two){
                listThree.add(one);
                i++;
            }
            if(i == listOne.size()){
                for (; j < listTwo.size(); j++) {
                    listThree.add(listTwo.get(j));
                }
            }
            if(j == listTwo.size()){
                for (; i < listOne.size(); i++) {
                    listThree.add(listOne.get(i));
                }
            }
        }
        return listThree;
    }
}
