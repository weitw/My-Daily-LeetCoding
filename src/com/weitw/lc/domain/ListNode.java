package com.weitw.lc.domain;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode() {}
    public ListNode(int val) { this.val = val; }
    public ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    public void printListNode(ListNode node) {
        while (hasNext(node)) {
            System.out.print(node.val);
            System.out.print(",");
            node = node.next;
        }
        if (node != null) {
            System.out.print(node.val);
        }
    }

    public  boolean hasNext(ListNode node) {
        return node != null && node.next != null;
    }

}
