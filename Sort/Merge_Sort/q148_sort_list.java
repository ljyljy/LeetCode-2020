package Sort.Merge_Sort;

import DataStructure.LinkedList.ListNode;

public class q148_sort_list {
    // ���q147���������� O(n^2)

    // ��1���鲢����O(nlogn)
    public ListNode sortList(ListNode head) {
        if (head == null) return null;
        return sortListHelper(head, null); // ����-�鲢����-����ҿ�
    }

    // ����
    private ListNode sortListHelper(ListNode head, ListNode tail) {
        if (head == null) return null;
        if (head.next == tail) {
            head.next = null; // ?�ָ����ұ߽磬����ҿ�, tail��������
            return head;
        }

        ListNode mid = getMid(head, tail);
        ListNode left = sortListHelper(head, mid); // ����ҿ�
        ListNode right = sortListHelper(mid, tail);
        return merge(left, right);
    }

    // �鲢
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

    // ��� q109,143,234,876
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
