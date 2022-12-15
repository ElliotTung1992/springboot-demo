package com.github.dge.everyday;

import com.github.dge.datastructure.linkedlist.ListNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MergeKSortedLists {

    public static void main(String[] args) {
        MergeKSortedLists mergeKSortedLists = new MergeKSortedLists();

        int[][] arr = {{1,4,5},{1,3,4},{2,6}};
        ListNode[] lists = new ListNode[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ListNode listNode = null;
            ListNode temp = null;
            int[] ints = arr[i];
            for (int j = 0; j < ints.length; j++) {
                if(j == 0){
                    listNode = new ListNode(ints[j]);
                    temp = listNode;
                }else{
                    ListNode cur = new ListNode(ints[j]);
                    temp.next = cur;
                    temp = temp.next;
                }
            }
            lists[i] = listNode;
        }
        ListNode listNode = mergeKSortedLists.mergeKLists(lists);
        System.out.println(listNode);
    }

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode result = new ListNode();
        ListNode out = result;
        Map<Integer, List<ListNode>> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (ListNode listNode : lists) {
            if(listNode == null){
                continue;
            }
            int val = listNode.val;
            map.computeIfAbsent(val, v -> new ArrayList<>()).add(listNode);
            if(!list.contains(val)){
                list.add(val);
            }
        }
        while (map.size() > 0) {
            list = list.stream().sorted().collect(Collectors.toList());
            Integer min = list.remove(0);
            List<ListNode> listNodes = map.get(min);
            map.remove(min);
            for (ListNode listNode : listNodes) {

                ListNode next = new ListNode(listNode.val);
                result.next = next;
                result = result.next;

                if(listNode.next != null){
                    ListNode next1 = listNode.next;
                    map.computeIfAbsent(next1.val, v -> new ArrayList<>()).add(next1);
                    if(!list.contains(next1.val)){
                        list.add(next1.val);
                    }
                }

            }
        }
        return out.next;
    }
}
