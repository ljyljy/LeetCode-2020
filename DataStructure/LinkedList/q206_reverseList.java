package DataStructure.LinkedList;

public class q206_reverseList {
    // ��1��������new��
    // ���q25, 92, 206?
    // dummy/pre -> head/cur -> nxt ...
    // dummy/pre <- head/cur <- nxt
    public ListNode reverseList1(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    // ��2���ݹ�
    // ���q92, 206?
    //      head -> nxt ... -> end -> ^
    // ^ <- head <- [nxt ... <- end/last]
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode nxt = head.next;
        ListNode last = reverseList(nxt); // ����[end <- ... <- nxt/last], β������KO
        // ��ͷ����
        nxt.next = head;
        head.next = null; // ?����������ɻ���ԭʼhead.nextû�жϿ���
        return last;
    }
}
