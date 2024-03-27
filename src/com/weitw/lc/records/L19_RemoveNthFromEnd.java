package com.weitw.lc.records;


/*
给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。

提示：
链表中结点的数目为 sz
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz
 */


import com.weitw.lc.annotation.Fail;
import com.weitw.lc.domain.ListNode;

@Fail
public class L19_RemoveNthFromEnd {
    // 暴力法，循环两次
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        return null;
    }

    // 优化:遍历一次
    /*
    思想：两个指针，保持两者之间的距离保持在小于等于n，当p2遍历到链表的最后一个时，p1.next就是待删除的节点
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode p1 = new ListNode(0, head);
        int len = 0;
        ListNode p2 = head;
        while (p2 != null) {
            p2 = p2.next;
            if (len >= n) {
                p1 = p1.next;
            }
            len++;
        }
        if (p1.next == head) {
            head = head.next;
        } else {
            p1.next = p1.next.next;
        }
        return head;
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
        L19_RemoveNthFromEnd l_19RemoveNthFromEnd = new L19_RemoveNthFromEnd();
        head = l_19RemoveNthFromEnd.removeNthFromEnd2(head, 1);
        print(head);
    }

    public static void print(ListNode head) {
        while (head != null) {
            System.out.print(head.val + ",");
            head = head.next;
        }
        System.out.println();
    }
}
