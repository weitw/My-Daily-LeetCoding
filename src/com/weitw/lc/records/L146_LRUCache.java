package com.weitw.lc.records;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现LRU算法
 * @author weitw
 * @date 2023/2/3 9:08
 */
public class L146_LRUCache {
    class Node {
        int key, value;
        Node pre, next;

        Node() {
        }

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    Map<Integer, Node> cache;
    int capacity;
    Node head, tail;

    // 题意指明了capacity是正整数。如果是0则没有任何意义
    public L146_LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>(capacity);
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node != null) {
            remove(node);
            addFirst(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        // node存在，则更新node.value，并把node节点放到链表的首位（head.next）
        if (node != null) {
            node.value = value;
            remove(node);
            addFirst(node);
            return;
        }
        // 如果超出容量，则要把最久未使用的节点删除
        if (cache.size() == capacity) {
            Node removeNode = removeLast();
            cache.remove(removeNode.key);
        }
        // 链表头部新增一个节点
        Node newNode = new Node(key, value);
        addFirst(newNode);
        cache.put(key, newNode);
    }

    public void remove(Node node) {
        node.pre.next = node.next;  // node.pre至少也是head，所以不必考虑空指针
        node.next.pre = node.pre;  // node.next至少也是tail，所以不必考虑空指针
    }

    public Node removeLast() {
        Node node = tail.pre;
        remove(node);
        return node;
    }

    public void addFirst(Node node) {
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;  // head.next至少也是tail，所以不必考虑空指针
        head.next = node;
    }

    public static void main(String[] args) {
//        L146_LRUCache lruCache = new L146_LRUCache(2);
//        lruCache.put(1, 1);
//        lruCache.put(2, 2);
//        System.out.println(lruCache.get(1));
//        lruCache.put(3, 3);
//        System.out.println(lruCache.get(2));
//        lruCache.put(4, 4);
//        System.out.println(lruCache.get(1));
//        System.out.println(lruCache.get(3));
//        System.out.println(lruCache.get(4));

        String s1 = "abcde";
        String s2 = "";
        System.out.println(indexOf(s1.toCharArray(), 0, s1.length(),
                s2.toCharArray(), 0, s2.length(), 0));
    }

//    indexOf(value, 0, value.length, str.value, 0, str.value.length, fromIndex);
    static int indexOf(char[] source, int sourceOffset, int sourceCount,
                       char[] target, int targetOffset, int targetCount,
                       int fromIndex) {
        if (fromIndex >= sourceCount) {
            return (targetCount == 0 ? sourceCount : -1);
        }
        if (fromIndex < 0) {
            fromIndex = 0;
        }
        if (targetCount == 0) {
            return fromIndex;
        }

        char first = target[targetOffset];
        int max = sourceOffset + (sourceCount - targetCount);

        for (int i = sourceOffset + fromIndex; i <= max; i++) {
            /* Look for first character. */
            if (source[i] != first) {
                while (++i <= max && source[i] != first);
            }

            /* Found first character, now look at the rest of v2 */
            if (i <= max) {
                int j = i + 1;
                int end = j + targetCount - 1;
                for (int k = targetOffset + 1; j < end && source[j]
                        == target[k]; j++, k++);

                if (j == end) {
                    /* Found whole string. */
                    return i - sourceOffset;
                }
            }
        }
        return -1;
    }
}