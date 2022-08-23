package com.github.dge.datastructure.linkedlist;

/**
 * @author dge
 * @version 1.0
 * @date 2022-08-22 5:37 PM
 * https://leetcode.cn/problems/odd-even-linked-list/description/
 * https://leetcode.com/problems/odd-even-linked-list/description/
 * 奇偶链表
 */
public class OddEvenLinkedList {

    public static void main(String[] args) {
        ListNode headA = new ListNode(0);
        ListNode headA2 = new ListNode(1);
        ListNode headA3 = new ListNode(2);
        ListNode headA4 = new ListNode(4);
        ListNode headA5 = new ListNode(5);
        ListNode headA6 = new ListNode(6);
        ListNode headA7 = new ListNode(7);
        ListNode headA8 = new ListNode(8);
        ListNode headA9 = new ListNode(9);
        ListNode headA10 = new ListNode(10);
        headA.next = headA2;
        headA2.next = headA3;
        headA3.next = headA4;
        headA4.next = headA5;
        headA5.next = headA6;
        headA6.next = headA7;
        headA7.next = headA8;
        headA8.next = headA9;
        headA9.next = headA10;

        OddEvenLinkedList oddEvenLinkedList = new OddEvenLinkedList();
        ListNode listNode = oddEvenLinkedList.oddEvenList(headA);
        System.out.println(listNode);
    }

    public ListNode oddEvenList(ListNode head) {
        if(head == null){
            return head;
        }
        ListNode even = head.next;
        ListNode oddHead = head, evenHead = even;
        while (evenHead != null && evenHead.next != null){
            oddHead.next = evenHead.next;
            evenHead.next = evenHead.next.next;
            oddHead = oddHead.next;
            evenHead = evenHead.next;
        }
        oddHead.next = even;
        return head;
    }

    public ListNode oddEvenList2(ListNode head) {
        ListNode odd = new ListNode(0);
        ListNode oddTemp = odd;
        ListNode even = new ListNode(0);
        ListNode evenTemp = even;
        int i = 0;
        while (head != null){
            ListNode current = head;
            if(i % 2 == 0){
                odd.next = current;
                odd = odd.next;
            }else{
                even.next = current;
                even = even.next;
            }
            head = head.next;
            current.next = null;
            i++;
        }
        odd.next = evenTemp.next;
        return oddTemp.next;
    }
}
