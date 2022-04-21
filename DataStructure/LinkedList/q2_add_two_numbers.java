package DataStructure.LinkedList;

import java.util.Scanner;

public class q2_add_two_numbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            if (l1 != null) {
                carry += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                carry += l2.val;
                l2 = l2.next;
            }
            cur.next = new ListNode(carry % 10);
            cur = cur.next;
            carry /= 10;
        }
        return dummy.next;
    }


    /**
     * intput:
     * 3
     * 2 4 3
     * 3
     * 5 6 4
     *
     * out:
     * 7, 0, 8
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n1 = sc.nextInt(); // 链表长度
//        sc.nextLine();
        ListNode l1 = getListNode(n1, sc);
        int n2 = sc.nextInt(); // 链表长度
//        sc.nextLine();
        ListNode l2 = getListNode(n2, sc);
        sc.close();

        q2_add_two_numbers sol = new q2_add_two_numbers();
        print(sol.addTwoNumbers(l1, l2));
    }

    private static ListNode getListNode(int n, Scanner sc) {
        ListNode dummy = new ListNode(-1);
        ListNode l1 = dummy;
        for (int i = 0; i < n; i++) {
            l1.next = new ListNode(sc.nextInt());
            l1 = l1.next;
        }
//        print(dummy.next);
        return dummy.next;
    }

    private static void print(ListNode list) {
        ListNode p = list;
        while (p != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }
    }

}
