package com.weitw.lc.records;


import com.weitw.lc.domain.ListNode;

/**
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 */

public class L24_swapPairs {
    public static void main(String[] args) {
        ListNode head = new ListNode();
        ListNode node = new ListNode();
        head.next = node;
        for (int i = 0; i < 2; i++) {
            node.val = i;
            if (i < 2-1) {
                node.next = new ListNode();
                node = node.next;
            }
        }
        head.printListNode(head);
        ListNode listNode = swapPairs(head);
        System.out.println();
        listNode.printListNode(listNode);
    }

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        int i = 0;
        ListNode node = head.next;
        ListNode node1, node2;
        while (node != null) {
            node1 = node;
            node2 = node.next;
            node = node2 == null ? null : node2.next;
            swap(node1, node2);
            if (i == 0) {
                head.next = node2;
            }
            i++;
        }
        return head;
    }

    // 交换两个node
    public static void swap(ListNode node1, ListNode node2) {
        if (node1 == null || node2 == null) {
            return;
        }
        node1.next = node2.next;
        node2.next = node1;
    }
}
