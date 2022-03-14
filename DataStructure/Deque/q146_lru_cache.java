package DataStructure.Deque;

import java.util.*;

public class q146_lru_cache {
    class DLinkedNode {
        int key, val;
        DLinkedNode prev, next;
        public DLinkedNode() {}
        public DLinkedNode(int k, int v) {key = k; val = v;} // ?<k,v>¶¼ÒªÓÐ£¡
    }

    int size, capacity;
    Map<Integer, DLinkedNode> cache = new HashMap<>();
    DLinkedNode head, tail;

    public q146_lru_cache(int capacity) {// public LRUCache(){...}
        this.size = 0;
        this.capacity = capacity;
        head = new DLinkedNode(); // dummyNode
        tail = new DLinkedNode(); // dummyNode
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node != null) {
            removeNode(node);
            addToHead(node);
            return node.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            DLinkedNode node = cache.get(key);
            node.val = value;
            removeNode(node); // ÎðÍü£¡
            addToHead(node);// ÎðÍü£¡
        } else {
            DLinkedNode node = new DLinkedNode(key, value);
            cache.put(key, node);
            addToHead(node);// ÎðÍü£¡
            size++;
            if (size > capacity) {
                DLinkedNode tail2rm = removeTail();
                cache.remove(tail2rm.key);
                size--;
            }
        }

    }

    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToHead(DLinkedNode node) {
        DLinkedNode nxt = head.next;
        node.next = nxt;
        nxt.prev = node;
        head.next = node;
        node.prev = head;
    }

    private DLinkedNode removeTail() {
        DLinkedNode tail2rm = tail.prev;
        removeNode(tail2rm);
        // tail2rm.prev.next = tail;
        // tail.prev = tail2rm.prev;
        return tail2rm;
    }
}
