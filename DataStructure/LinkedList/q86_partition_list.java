package DataStructure.LinkedList;

public class q86_partition_list {
    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(-1), head_small = small; // 两个链表(<x, >=x)
        ListNode big = new ListNode(-1), head_big = big; // ?故需俩dummy!
        ListNode p = head;
        while (p != null) {
            if (p.val < x) {
                small.next = p; // ?必须是.next! 否则会覆盖！
                small = small.next;
            } else {
                big.next = p;
                big = big.next;
            }
            p = p.next;
        }
        big.next = null;
        small.next = head_big.next; // head_XXX: dummy
        return head_small.next;
    }
}
