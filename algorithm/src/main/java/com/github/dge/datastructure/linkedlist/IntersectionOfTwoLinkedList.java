package com.github.dge.datastructure.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * @author dge
 * @version 1.0
 * @date 2022-08-10 11:28 AM
 */
public class IntersectionOfTwoLinkedList {

    public static void main(String[] args) {
        ListNode headA = new ListNode(2);
        ListNode headA2 = new ListNode(6);
        ListNode headA3 = new ListNode(4);
        headA.next = headA2;
        headA2.next = headA3;

        ListNode headB = new ListNode(1);
        ListNode headB2 = new ListNode(5);
        headB.next = headB2;

        IntersectionOfTwoLinkedList  intersectionOfTwoLinkedList = new IntersectionOfTwoLinkedList();
        ListNode intersectionNode = intersectionOfTwoLinkedList.getIntersectionNode(headA, headB);
        System.out.println(intersectionNode);
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        Set<ListNode> visited = new HashSet<ListNode>();
        ListNode temp = headA;
        while (temp != null) {
            visited.add(temp);
            temp = temp.next;
        }
        temp = headB;
        while (temp != null) {
            if (visited.contains(temp)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}
