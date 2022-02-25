package DataStructure.LinkedList;

public class q83_remove_duplicates_from_sorted_list {
    // 类比q82：删除所有重复结点
    // Q83: 保留重复的头结点
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p = head, q = head; // 不同1：Q82:p=dummy(∵head可能重复->被删)
        while (p != null) {// 不同2：Q82:p.next != null (∵p是dummy)
            q = p.next;
            // 不同3：Q82需全删：p.next.val == q.val，p是重复结点的前驱
            // Q83：保留重复的第一个结点，p无需指向前驱，指向第一个重复结点自己即可
            while (q != null && p.val == q.val) {
                q = q.next;
            }
            if (q == p.next) {
                p = p.next;
            } else {
                p.next = q;
            }
        }
        return dummy.next;
    }
}
