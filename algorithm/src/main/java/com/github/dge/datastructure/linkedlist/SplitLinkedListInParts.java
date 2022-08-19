package com.github.dge.datastructure.linkedlist;

import java.util.Arrays;

/**
 * @author dge
 * @version 1.0
 * @date 2022-08-19 3:26 PM
 * https://leetcode.com/problems/split-linked-list-in-parts/description/
 * https://leetcode.cn/problems/split-linked-list-in-parts/description/
 */
public class SplitLinkedListInParts {

    public static void main(String[] args) {
        ListNode headA = new ListNode(1);
        ListNode headA2 = new ListNode(0);
        ListNode headA3 = new ListNode(2);
        /*ListNode headA4 = new ListNode(4);
        ListNode headA5 = new ListNode(5);
        ListNode headA6 = new ListNode(6);
        ListNode headA7 = new ListNode(7);
        ListNode headA8 = new ListNode(8);
        ListNode headA9 = new ListNode(9);
        ListNode headA10 = new ListNode(10);*/
        headA.next = headA2;
        headA2.next = headA3;
        /*headA3.next = headA4;
        headA4.next = headA5;
        headA5.next = headA6;
        headA6.next = headA7;
        headA7.next = headA8;
        headA8.next = headA9;
        headA9.next = headA10;*/

        SplitLinkedListInParts splitLinkedListInParts = new SplitLinkedListInParts();
        ListNode[] listNodes = splitLinkedListInParts.splitListToParts(headA, 2);
        System.out.println(Arrays.toString(listNodes));
    }

    public ListNode[] splitListToParts(ListNode head, int k) {
        int n = 0;
        ListNode temp = head;
        while (temp != null) {
            n++;
            temp = temp.next;
        }
        int quotient = n / k, remainder = n % k;

        ListNode[] parts = new ListNode[k];
        ListNode curr = head;
        for (int i = 0; i < k && curr != null; i++) {
            parts[i] = curr;
            int partSize = quotient + (i < remainder ? 1 : 0);
            for (int j = 1; j < partSize; j++) {
                curr = curr.next;
            }
            ListNode next = curr.next;
            curr.next = null;
            curr = next;
        }
        return parts;
    }
}
