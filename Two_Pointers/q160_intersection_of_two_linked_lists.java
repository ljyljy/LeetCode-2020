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
        ListNode pA = headA; ListNode pB = headB;
        while (pA != pB){
            pA = pA != null? pA.next : headB; // if判断的不是pA.next!
            pB = pB != null? pB.next : headA;
        }
        return pA; // 或pB
    }
}
