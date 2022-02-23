package DataStructure.LinkedList;



/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class q61_rotate_list {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) return head;
        int n = 0;
        ListNode p = head;
        while (p != null) {
            p = p.next;
            n++;
        }
        k = k % n;
        if (k == 0) return head;// 无需rotate

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode lastK_prev = getLastKthPrevNode(head, k);//  倒数第k个结点的【前驱】
        // 链表[-k, end]断裂，移到链表首部
        ListNode newHead = lastK_prev.next;
        lastK_prev.next = null; // 作为结尾
        // 连接newHead->...->head
        p = newHead;
        while (p.next != null) {
            p = p.next;
        } // 达到newHead尾部
        p.next = head; // 连接
        return newHead;
    }

    // 快慢指针 - q19.获取倒数第k个结点的【前驱】！
    private ListNode getLastKthPrevNode(ListNode dummy, int k) {
        ListNode slow_prev = dummy, fast = dummy.next; // fast=head

        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            slow_prev = slow_prev.next;
            fast = fast.next;
        }
        return slow_prev;
    }
}