package Sort;


public class q147_insertion_sort_list {
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val){ this.val = val;}
        ListNode(int val, ListNode next) {this.val = val; this.next = next;}
    }

    // ?�����������������㣬��ǰ������������� ���Һ��ʵĲ���λ��
    // ��lastSortedά����������ķֽ磬��������[0,lS]����������[lS+1/cur,n)
    // O(n^2)
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        ListNode lastSorted = head, cur = head.next;
        while (cur != null) {
            ListNode nxt = cur.next;
            // ^->1->2->4->8->(9/lS)->(5/cur)->...
            // ..-> 4/prev ->(5) -> 8/nxt->...
            if (cur.val < lastSorted.val) {
                ListNode prev = dummy;
                while (prev.next.val <= cur.val) {
                    prev = prev.next;
                }
                lastSorted.next = nxt; // ������
                ListNode nxt2 = prev.next;
                prev.next = cur;
                cur.next = nxt2;
            } else { // ��������+1/����cur��ָ��lS��cur����
                lastSorted = lastSorted.next;
                cur = cur.next;
                continue;
            }
            cur = lastSorted.next;
        }
        return dummy.next;
    }
}
