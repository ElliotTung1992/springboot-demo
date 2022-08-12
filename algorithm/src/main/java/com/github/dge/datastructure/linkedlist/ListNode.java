package com.github.dge.datastructure.linkedlist;

/**
 * @author dge
 * @version 1.0
 * @date 2022-08-12 11:21 AM
 */
public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
