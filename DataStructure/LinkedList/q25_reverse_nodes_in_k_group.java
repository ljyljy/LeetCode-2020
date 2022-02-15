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
}
