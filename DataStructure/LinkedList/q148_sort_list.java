package DataStructure.LinkedList;

public class q148_sort_list {
    public ListNode sortList(ListNode head) {
        if (head == null) return null;
        return sortList(head, null); // ����-�鲢����-����ҿ�
    }

    private ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) return null;
        if (head.next == tail) {
            head.next = null; // ?�ָ����ұ߽磡
            return head;
        }

        ListNode mid = getMid(head, tail);
        ListNode left = sortList(head, mid); // ����ҿ�
        ListNode right = sortList(mid, tail);
        return merge(left, right);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        if (l1 != null) p.next = l1;
        if (l2 != null) p.next = l2;
        return dummy.next;
    }

    private ListNode getMid(ListNode head, ListNode tail) {
        ListNode slow = head, fast = head;
        // ȡ�к�(��len=4, mid=3rd)
        while (fast != tail && fast.next != tail) { // ?����null����дtail��
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
