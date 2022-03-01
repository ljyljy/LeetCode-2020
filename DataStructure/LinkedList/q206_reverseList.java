package DataStructure.LinkedList;

public class q206_reverseList {
    // 法1：迭代（new）
    // 类比q25, 92, 206?
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

    // 法2：递归
    // 类比q92, 206?
    //      head -> nxt ... -> end -> ^
    // ^ <- head <- [nxt ... <- end/last]
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode nxt = head.next;
        ListNode last = reverseList(nxt); // 返回[end <- ... <- nxt/last], 尾部倒序KO
        // 将头倒序
        nxt.next = head;
        head.next = null; // ?勿忘！否则成环！原始head.next没有断开！
        return last;
    }
}
