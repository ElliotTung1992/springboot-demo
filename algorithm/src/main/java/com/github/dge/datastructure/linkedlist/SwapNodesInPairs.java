package com.github.dge.datastructure.linkedlist;

/**
 * @author dge
 * @version 1.0
 * @date 2022-08-16 9:56 AM
 */
public class SwapNodesInPairs {

    public static void main(String[] args) {
        ListNode headA = new ListNode(1);
        /*ListNode headA2 = new ListNode(2);
        ListNode headA3 = new ListNode(3);
        ListNode headA4 = new ListNode(4);
        ListNode headA5 = new ListNode(5);
        headA.next = headA2;
        headA2.next = headA3;
        headA3.next = headA4;
        headA4.next = headA5;*/

        SwapNodesInPairs swapNodesInPairs = new SwapNodesInPairs();
        ListNode listNode = swapNodesInPairs.swapPairs(headA);
        System.out.println(listNode);
    }

    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }

    public ListNode swapPairs3(ListNode head) {
        ListNode ln = new ListNode(0, head);
        ListNode temp = ln;
        while (temp.next != null && temp.next.next != null){
            ListNode next = temp.next;
            ListNode nnext = temp.next.next;
            next.next = nnext.next;
            nnext.next = next;
            temp.next = nnext;
            temp = temp.next.next;
        }
        return ln.next;
    }
}
