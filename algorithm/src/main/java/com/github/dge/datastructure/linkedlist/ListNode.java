package com.github.dge.datastructure.linkedlist;

/**
 * @author dge
 * @version 1.0
 * @date 2022-08-12 11:21 AM
 */
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode() {}
    public ListNode(int val) { this.val = val; }
    public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
