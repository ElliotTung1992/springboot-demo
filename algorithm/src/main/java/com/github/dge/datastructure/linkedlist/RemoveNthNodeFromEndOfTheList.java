package com.github.dge.datastructure.linkedlist;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author dge
 * @version 1.0
 * @date 2022-08-15 9:56 AM
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/
 * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/description/
 * 删除链表倒数第N个节点
 */
public class RemoveNthNodeFromEndOfTheList {

    public static void main(String[] args) {
        ListNode headA = new ListNode(1);
        ListNode headA2 = new ListNode(2);
        ListNode headA3 = new ListNode(3);
        ListNode headA4 = new ListNode(4);
        ListNode headA5 = new ListNode(5);
        headA.next = headA2;
        headA2.next = headA3;
        headA3.next = headA4;
        headA4.next = headA5;


        RemoveNthNodeFromEndOfTheList removeNthNodeFromEndOfThe = new RemoveNthNodeFromEndOfTheList();
        ListNode listNode = removeNthNodeFromEndOfThe.removeNthFromEnd(headA, 2);
        System.out.println(listNode);
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode temp = new ListNode(0, head);
        ListNode slow = temp;
        ListNode quick = head;
        for (int i = 0; i < n; i++) {
            quick = quick.next;
        }
        while (quick != null){
            quick = quick.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return temp.next;
    }

    public ListNode removeNthFromEnd3(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        Deque<ListNode> stack = new LinkedList<>();
        ListNode cur = dummy;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        for (int i = 0; i < n; ++i) {
            stack.pop();
        }
        ListNode prev = stack.peek();
        prev.next = prev.next.next;
        ListNode ans = dummy.next;
        return ans;
    }


    public ListNode removeNthFromEnd4(ListNode head, int n) {
        ListNode listNode = new ListNode(0, head);
        int deep = getLength(listNode);
        ListNode temp = listNode;
        for (int i = deep; i > n + 1; i--) {
            temp = temp.next;
        }
        temp.next = temp.next.next;
        return listNode.next;
    }

    public int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            ++length;
            head = head.next;
        }
        return length;
    }

    public ListNode removeNthFromEnd5(ListNode head, int n) {
        int deep = getDeep(head);
        n = deep - n;
        ListNode temp = head;
        if(n == 0){
            return head.next;
        }
        while (n > 0 && temp.next != null){
            if(n == 1){
                ListNode next = temp.next.next;
                temp.next = next;
            }
            temp = temp.next;
            n--;
        }
        return head;
    }

    private int getDeep(ListNode head){
        if(head == null){
            return 0;
        }
        int deep = 1;
        while (head.next != null){
            head = head.next;
            deep++;
        }
        return deep;
    }
}
