import java.util.HashSet;
import java.util.Set;


public class q141_linked_list_cycle {

    // Definition for singly-linked list.
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    // 法2：快慢指针  时间O(n), 空间O（1）
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        // ❤判环：起始必须交错开！（但是求环的起点：起点均head；二次相交时即为所求）
        ListNode slow = head, fast = head.next;
        // VS Q143/876: 求链表中点（同起点head）
        while (slow != fast) {// ❤while和if条件不要写反！
            if (fast == null || fast.next == null)
                return false;
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }



    // 法1：哈希表 时&空 O(n)
    public boolean hasCycle1(ListNode head) {
        Set<ListNode> nodeSeen = new HashSet<>();
        while (head != null) {
            if (nodeSeen.contains(head)) {
                return true;
            } else {
                nodeSeen.add(head);
            }
            head = head.next;
        }
        return false;
    }


}
