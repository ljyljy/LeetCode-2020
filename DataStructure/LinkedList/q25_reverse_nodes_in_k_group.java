package DataStructure.LinkedList;

public class q25_reverse_nodes_in_k_group {
    // dummy/pre -> (start, ..., end) -> next...
    // dummy/pre -> (end, ..., start/pre2/end2) -> next
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

    // 练习：链表的输入输出
    public static void main(String[] args) {
        q25_reverse_nodes_in_k_group sol = new q25_reverse_nodes_in_k_group();
        int n = 5; // sc
        int[] head = new int[]{1,2,3,4,5}; // sc
        int k = 2; // sc
        ListNode dummy = new ListNode(-1);
        ListNode cur = new ListNode(-1);
        dummy = cur; // 【非.next！】
        for (int i = 0; i < n; i++) {
            cur.next = new ListNode(head[i]);// sc 【非cur=...】
            cur = cur.next;
        }

        ListNode reversed = sol.reverseKGroup(dummy.next, k);
        printNodes(reversed);

    }

    private static void printNodes(ListNode reversed) {
        ListNode p = reversed; // dummy.next;
        while (p != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }
    }
}
