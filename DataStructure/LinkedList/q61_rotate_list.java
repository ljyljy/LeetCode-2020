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
        if (head == null) return null;
        ListNode dummy = new ListNode(-1, head);
        ListNode p = head;
        int n = 0;
        while (p != null) {
            n++;
            p = p.next;
        }
        k = k % n;
        if (k == 0) return dummy.next; // 无需rotate

        ListNode lastK_prev = getLastKthNode(dummy, k); //  倒数第k个结点的【前驱】

        ListNode head_new = lastK_prev.next;
        lastK_prev.next = null;
        p = head_new;
        while (p.next != null) {
            p = p.next;
        } // 退出后，p为尾巴
        p.next = dummy.next;
        dummy.next = head_new;
        return dummy.next;
    }

    // 快慢指针 - q19.获取倒数第k个结点的【前驱】！
    private ListNode getLastKthNode(ListNode dummy, int k) {
        ListNode fast = dummy.next, slow_prev = dummy;
        while(k - 1 > 0) {
            fast = fast.next;
            k--;
        }
        while (fast.next != null){
            fast = fast.next;
            slow_prev = slow_prev.next;
        }
        return slow_prev;
    }
}