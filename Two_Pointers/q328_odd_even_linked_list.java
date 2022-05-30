package Two_Pointers;


public class q328_odd_even_linked_list {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode odd = head, even = head.next;
        ListNode firstOdd = head, firstEven = head.next;
        // 1(odd)->2(even)->3->4         ->x...
        // 1(odd)->3   ->   2(even)->4 ->x...
        while (even != null && even.next != null) {
            ListNode next_odd = even.next; // 3rd
            ListNode next_even  = next_odd.next; // 4th
            // 重排序：1->3
            odd.next = next_odd;
            odd = next_odd;
            // 重排序：2->4
            even.next = next_even ;
            even = next_even ;
        }
        odd.next = firstEven; // oddLast->firstEven
        return firstOdd;
    }
}
