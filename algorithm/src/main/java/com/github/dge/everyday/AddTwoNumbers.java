package com.github.dge.everyday;

import com.github.dge.datastructure.linkedlist.ListNode;

import java.util.Deque;
import java.util.LinkedList;

public class AddTwoNumbers {

    public static void main(String[] args) {

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(-1);
        ListNode temp = result;
        int count = 0;
        while (l1 != null || l2 != null){
            int i1 = -1;
            if(l1 == null){
                i1 = 0;
            }else{
                i1 = l1.val;
                l1 = l1.next;
            }
            int i2 = -1;
            if(l2 == null){
                i2 = 0;
            }else{
                i2 = l2.val;
                l2 = l2.next;
            }
            int i3 = i1 + i2 + count;
            int i4 = i3 % 10;
            count = i3 / 10;
            if(result.val == -1){
                result.val = i4;
            }else{
                ListNode listNode = new ListNode(i4);
                result.next = listNode;
                result = result.next;
            }

        }
        if(count > 0){
            ListNode listNode = new ListNode(count);
            result.next = listNode;
        }
        return temp;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        Deque<Integer> deque1 = new LinkedList<>();
        Deque<Integer> deque2 = new LinkedList<>();
        while (l1 != null){
            deque1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null){
            deque2.push(l2.val);
            l2 = l2.next;
        }
        ListNode result = new ListNode(-1);
        ListNode temp = result;
        int count = 0;
        while (!deque1.isEmpty() || !deque2.isEmpty()){
            Integer i1 = deque1.isEmpty() ? 0 : deque1.removeLast();
            Integer i2 = deque2.isEmpty() ? 0 : deque2.removeLast();
            int i3 = (i1 + i2 + count) % 10;
            count = (i1 + i2 + count) / 10;
            if(result.val == -1){
                result.val = i3;
            }else{
                ListNode listNode = new ListNode(i3);
                result.next = listNode;
                result = result.next;
            }
        }
        if(count > 0){
            ListNode listNode = new ListNode(count);
            result.next = listNode;
        }
        return temp;
    }
}
