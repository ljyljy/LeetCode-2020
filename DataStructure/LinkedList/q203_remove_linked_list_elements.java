package DataStructure.LinkedList;

public class q203_remove_linked_list_elements {
    // 法1：迭代
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        ListNode dummy = new ListNode(-1);
        dummy.next = head; //  指针指向注意！dummy无需再指向head！（否则头结点删不掉）
        ListNode p = dummy; // 指针指向注意！p不是指向head
        while(p.next != null) {
            if (p.next.val == val)
                p.next = p.next.next;
            else p = p.next;
        }
        return dummy.next;
    }
    // 法2：递归
    public ListNode removeElements2(ListNode head, int val) {
        if (head == null) return null;
        ListNode dummy = new ListNode(-1);
        dummy.next = head; //  指针指向注意！dummy无需再指向head！（否则头结点删不掉）
        dfs(dummy, dummy.next, val);
        return dummy.next;
    }

    private void dfs(ListNode prev, ListNode root, int val) {
        if (root == null) return;
        if (root.val == val)
            prev.next = root.next;
        else prev = prev.next; // 或root
        dfs(prev, prev.next, val);
    }
}
