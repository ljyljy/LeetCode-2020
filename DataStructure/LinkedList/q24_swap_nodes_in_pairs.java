package DataStructure.LinkedList;

public class q24_swap_nodes_in_pairs {
    public ListNode swapPairs(ListNode head) {
        if (head == null|| head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        head = dummy;
        while (head.next != null && head.next.next != null) {
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
