package com.github.dge.datastructure.linkedlist;

/**
 * @author dge
 * @version 1.0
 * @date 2022-08-12 11:20 AM
 * https://leetcode.cn/problems/merge-two-sorted-lists/description/
 * https://leetcode.com/problems/merge-two-sorted-lists/description/
 * 合并两个有序链表
 */
public class MergeTwoSortedList {

    public static void main(String[] args) {

        ListNode headA = new ListNode(1);
        ListNode headA2 = new ListNode(2);
        ListNode headA3 = new ListNode(4);
        headA.next = headA2;
        headA2.next = headA3;

        ListNode headB = new ListNode(1);
        ListNode headB2 = new ListNode(3);
        ListNode headB3 = new ListNode(4);
        headB.next = headB2;
        headB2.next = headB3;

        MergeTwoSortedList mergeTwoSortedList = new MergeTwoSortedList();
        ListNode listNode = mergeTwoSortedList.mergeTwoLists(headA, headB);
        System.out.println(listNode);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(-1);
        ListNode temp = result;
        while (l1 != null && l2 != null){
            if(l1.val <= l2.val){
                temp.next = l1;
                l1 = l1.next;
            }else{
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }
        temp.next = l1 == null ? l2 : l1;
        return result.next;
    }

    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        ListNode result = null;
        while (list1 != null || list2 != null){
            int cv = Integer.MIN_VALUE;
            if(list1 == null){
                cv = list2.val;
                list2 = list2.next;
                result = build(result, cv);
                continue;
            }
            if(list2 == null){
                cv = list1.val;
                list1 = list1.next;
                result = build(result, cv);
                continue;
            }
            int val1 = list1.val;
            int val2 = list2.val;
            if(val1 <= val2){
                cv = list1.val;
                list1 = list1.next;
            }else{
                cv = list2.val;
                list2 = list2.next;
            }
            result = build(result, cv);
        }
        return result;
    }

    private ListNode build(ListNode listNode, int value){
        if(listNode == null){
            listNode = new ListNode(value);
        }else{
            ListNode next = new ListNode(value);
            ListNode find = listNode;
            while (find.next != null){
                find = find.next;
            }
            find.next = next;
        }
        return listNode;
    }
}
