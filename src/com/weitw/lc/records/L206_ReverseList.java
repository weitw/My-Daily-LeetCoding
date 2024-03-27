package com.weitw.lc.records;


/*
给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 

示例 1：

输入：head = [1,2,3,4,5]
输出：[5,4,3,2,1]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/reverse-linked-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import com.weitw.lc.annotation.Success;
import com.weitw.lc.domain.ListNode;

@Success
public class L206_ReverseList {

    /**
     * 常规方法
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode newList = new ListNode(head.val);
        while (head.next != null) {
            head = head.next;
            newList = new ListNode(head.val, newList);
        }
        return newList;
    }

    /**
     * 优化
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        ListNode prev = null;
        ListNode next;
        while (head != null) {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        ListNode head = node;
        node.next = new ListNode(2); node = node.next;
        node.next = new ListNode(3); node = node.next;
        node.next = new ListNode(4); node = node.next;
        node.next = new ListNode(5); node = node.next;
        node.next = new ListNode(6); node = node.next;
        print(head);
        L206_ReverseList l_206ReverseList = new L206_ReverseList();
        print(l_206ReverseList.reverseList2(head));
        System.out.println('0' + '0' - 96);
        System.out.println((int)'0');
    }

    public static void print(ListNode head) {
        while (head != null) {
            System.out.print(head.val + ",");
            head = head.next;
        }
        System.out.println();
    }
}

