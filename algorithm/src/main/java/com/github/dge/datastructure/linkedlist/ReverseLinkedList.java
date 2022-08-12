package com.github.dge.datastructure.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dge
 * @version 1.0
 * @date 2022-08-11 4:01 PM
 * 反转链表
 * https://leetcode.com/problems/reverse-linked-list/description/
 * https://leetcode.cn/problems/reverse-linked-list/description/
 */
public class ReverseLinkedList {

    public static void main(String[] args) {

        ListNode headA = new ListNode(1);
        ListNode headA2 = new ListNode(2);
        ListNode headA3 = new ListNode(3);
        headA.next = headA2;
        headA2.next = headA3;

        ReverseLinkedList reverseLinkedList = new ReverseLinkedList();

        reverseLinkedList.reverseList(headA);
    }

    public ListNode reverseList(ListNode head) {
        ListNode current = null;
        ListNode ln = head;
        while (ln != null){
            ListNode next = ln.next;
            ln.next = current;
            current = ln;
            ln = next;
        }
        return current;
    }

    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public ListNode reverseList3(ListNode head) {
        List<Integer> list = new ArrayList<>();
        if(head == null){
            return head;
        }
        while (head != null){
            list.add(head.val);
            head = head.next;
        }
        ListNode result = new ListNode();
        ListNode currentResult = result;
        result.val = list.get(list.size() - 1);
        for (int i = list.size() - 2; i >= 0; i--) {
            ListNode next = new ListNode(list.get(i));
            currentResult.next = next;
            currentResult = next;
        }
        return result;
    }
}


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
