package DataStructure.LinkedList;

import java.util.Scanner;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode() {}
    public ListNode(int val){ this.val = val;}
    public ListNode(int val, ListNode next) {this.val = val; this.next = next;}

    public void printNodes() {
        ListNode p = this; // dummy.next;
        while (p != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }
    }

    public ListNode createListNode(int[] nums) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        for (int i = 0; i < nums.length; i++) {
            cur.next = new ListNode(nums[i]);
            cur = cur.next;
        }
        return dummy.next;
    }

    private ListNode createListNode2(int n, Scanner sc) {
        ListNode dummy = new ListNode(-1);
        ListNode l1 = dummy;
        for (int i = 0; i < n; i++) {
            l1.next = new ListNode(sc.nextInt());
            l1 = l1.next;
        }
//        print(dummy.next);
        return dummy.next;
    }

    public void printList(ListNode list) {
        ListNode p = list;
        while (p != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }
    }

     public ListNode reverse(ListNode list) {
         ListNode cur = list, prev = null;
         while (cur != null) {
             ListNode nxt = cur.next;
             cur.next = prev;
             prev = cur;
             cur = nxt;
         }
         return prev;
     }

}
