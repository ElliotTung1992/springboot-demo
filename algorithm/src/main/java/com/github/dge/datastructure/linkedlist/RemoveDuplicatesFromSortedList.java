package com.github.dge.datastructure.linkedlist;

/**
 * @author dge
 * @version 1.0
 * @date 2022-08-12 3:56 PM
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list/description/
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-list/description/
 * 删除排序链表中重复的元素
 */
public class RemoveDuplicatesFromSortedList {

    public static void main(String[] args) {
        ListNode headA = new ListNode(1);
        ListNode headA2 = new ListNode(1);
        ListNode headA3 = new ListNode(2);
        ListNode headA4 = new ListNode(4);
        ListNode headA5 = new ListNode(4);
        headA.next = headA2;
        headA2.next = headA3;
        headA3.next = headA4;
        headA4.next = headA5;

        RemoveDuplicatesFromSortedList removeDuplicatesFromSortedList = new RemoveDuplicatesFromSortedList();
        ListNode listNode = removeDuplicatesFromSortedList.deleteDuplicates(headA);
        System.out.println(listNode);
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        while (head != null && head.next != null){
            if(cur.val == cur.next.val){
                cur.next = cur.next.next;
            }else{
                cur = cur.next;
            }
        }
        return head;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        ListNode result = new ListNode(-1);
        result.next = head;
        while (head != null && head.next != null){
            ListNode next = head.next;
            if(head.val == next.val){
                head.next = next.next;
            }else{
                head = next;
            }
        }
        return result.next;
    }
}
