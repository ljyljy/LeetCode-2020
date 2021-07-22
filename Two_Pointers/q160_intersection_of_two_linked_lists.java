package Two_Pointers;



public class q160_intersection_of_two_linked_lists {
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val){ this.val = val;}
        ListNode(int val, ListNode next) {this.val = val; this.next = next;}
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA != null? pA.next : headB; // 判断的不是pA.next是否为空!!!
            pB = pB != null? pB.next : headA; // 否则，若无相交节点，最终pA/pB始终不为null（不合题意）
        }
        return pA; // 或pB
    }
}
