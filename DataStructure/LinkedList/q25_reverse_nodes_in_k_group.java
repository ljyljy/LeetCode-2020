package DataStructure.LinkedList;

public class q25_reverse_nodes_in_k_group {
    // 类比q206, 92, 25
    // dummy/pre -> (start, ..., end) -> next...
    // dummy/pre -> (end, ..., start/pre'、end') -> next
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy, end = dummy;

        while (end.next != null) {
            // end指向k_1组末尾
            for (int i = 0; i < k && end != null; i++) end = end.next;
            if (end == null) break;
            // nxt指向k_2组的开头
            ListNode next = end.next, start = pre.next;

            end.next = null;
            pre.next = reverse(start);
            start.next = next;
            pre = start;
            end = start;
        }
        return dummy.next;
    }

    private ListNode reverse(ListNode head) {
        // pre(^) | cur -> next...
        // pre <- cur <- ...
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    // 练习：链表的输入输出(见q206, q25)
    public static void main(String[] args) {
        q25_reverse_nodes_in_k_group sol = new q25_reverse_nodes_in_k_group();
        int n = 5; // sc
        int[] head = new int[]{1,2,3,4,5}; // sc
        int k = 2; // sc
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        for (int i = 0; i < n; i++) {
            p.next = new ListNode(head[i]); // sc 【非cur=...】
            p = p.next;
        }

        ListNode reversed = sol.reverseKGroup(dummy.next, k);
        reversed.printNodes(); // 法1
        System.out.println();
        printNodes(reversed); // 法2
    }
    private static void printNodes(ListNode head) {
        ListNode p = head; // dummy.next;
        while (p != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }
    }
}
