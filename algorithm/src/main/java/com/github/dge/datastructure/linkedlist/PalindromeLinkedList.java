package com.github.dge.datastructure.linkedlist;

/**
 * @author dge
 * @version 1.0
 * @date 2022-08-18 4:57 PM
 * https://leetcode.cn/problems/palindrome-linked-list/description/
 * https://leetcode.com/problems/palindrome-linked-list/description/
 * 回文链表
 */
public class PalindromeLinkedList {

    public static void main(String[] args) {

        ListNode headA = new ListNode(2);
        ListNode headA2 = new ListNode(6);
        ListNode headA3 = new ListNode(4);
        headA.next = headA2;
        headA2.next = headA3;

        PalindromeLinkedList palindromeLinkedList = new PalindromeLinkedList();
        boolean palindrome = palindromeLinkedList.isPalindrome(headA);
    }

    public boolean isPalindrome(ListNode head) {
        ListNode reverse = reverse(head);
        return false;
    }

    private ListNode reverse(ListNode head){
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

}
