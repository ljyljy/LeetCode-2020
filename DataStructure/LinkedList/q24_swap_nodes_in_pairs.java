package DataStructure.LinkedList;

public class q24_swap_nodes_in_pairs {
    public ListNode swapPairs_new(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p = dummy;
        while (p.next != null && p.next.next != null) { // 成对{n1, n2}
            // p -> n1 -> n2 -> ...
            // p -> n2 -> n1 -> ...
            ListNode n1 = p.next, n2 = p.next.next;
            ListNode nxt = n2.next;
            p.next = n2;
            n1.next = nxt;
            n2.next = n1;

            p = p.next.next;
        }
        return dummy.next;
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null|| head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        head = dummy;
        while (head.next != null && head.next.next != null) { // 成对{n1, n2}
            // head'(dummy) -> n1 -> n2 -> ...
            // head'(dummy) -> n2 -> n1 -> ...
            ListNode n1 = head.next, n2 = head.next.next;
            head.next = n2;
            n1.next = n2.next;
            n2.next = n1;
            // move to next pair
            head = n1; // 下一对的前继
        }
        return dummy.next;
    }
}
