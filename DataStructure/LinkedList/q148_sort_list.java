package DataStructure.LinkedList;

public class q148_sort_list {
    public ListNode sortList(ListNode head) {
        if (head == null) return null;
        return sortList(head, null); // 二分-归并排序-左闭右开
    }

    private ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) return null;
        if (head.next == tail) {
            head.next = null; // ?分割左右边界！
            return head;
        }

        ListNode mid = getMid(head, tail);
        ListNode left = sortList(head, mid); // 左闭右开
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
        // 取中后(如len=4, mid=3rd)
        while (fast != tail && fast.next != tail) { // ?不是null！而写tail！
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
