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
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head, fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) { // 只看fast即可
                return false;
            }
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
