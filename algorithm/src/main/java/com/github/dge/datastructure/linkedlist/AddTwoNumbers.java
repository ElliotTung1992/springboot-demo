package com.github.dge.datastructure.linkedlist;

import java.util.*;

/**
 * @author dge
 * @version 1.0
 * @date 2022-08-17 9:53 AM
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
        ListNode headA = new ListNode(2);
        ListNode headA2 = new ListNode(6);
        ListNode headA3 = new ListNode(5);
        headA.next = headA2;
        headA2.next = headA3;

        ListNode headB = new ListNode(1);
        ListNode headB2 = new ListNode(5);
        headB.next = headB2;

        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        ListNode listNode = addTwoNumbers.addTwoNumbers(headA, headB);
        System.out.println(listNode);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> stack1 = new ArrayDeque<>();
        Deque<Integer> stack2 = new ArrayDeque<>();

        while (l1 != null){
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null){
            stack2.push(l2.val);
            l2 = l2.next;
        }

        ListNode result = null;
        int temp = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() || temp != 0){
            int i1 = stack1.isEmpty() ? 0 : stack1.pop();
            int i2 = stack2.isEmpty() ? 0 : stack2.pop();
            int sum = i1 + i2 + temp;
            int resultInt = sum % 10;
            temp = sum / 10;
            ListNode listNode = new ListNode(resultInt);
            listNode.next = result;
            result = listNode;
        }
        return result;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        List<Integer> l1List = new ArrayList<>();
        while (l1 != null){
            l1List.add(l1.val);
            l1 = l1.next;
        }

        List<Integer> l2List = new ArrayList<>();
        while (l2 != null){
            l2List.add(l2.val);
            l2 = l2.next;
        }

        Collections.reverse(l1List);
        Collections.reverse(l2List);

        List<Integer> l3List = new ArrayList<>();
        int i = 1;
        int temp = 0;
        while (l1List.size() >= i || l2List.size() >= i){
            int result = 0;
            result += temp;
            temp = 0;
            result += l1List.size() >= i ? l1List.get(i - 1): 0;
            result += l2List.size() >= i ? l2List.get(i - 1): 0;
            if(result >= 10){
                result -= 10;
                temp = 1;
            }
            l3List.add(result);
            i++;
        }
        if(temp > 0){
            l3List.add(1);
        }

        Collections.reverse(l3List);
        ListNode result = new ListNode(0);
        ListNode haha = result;
        for (int j = 0; j < l3List.size(); j++) {
            ListNode listNode = new ListNode(l3List.get(j));
            haha.next = listNode;
            haha = haha.next;
        }
        return result.next;
    }
}
