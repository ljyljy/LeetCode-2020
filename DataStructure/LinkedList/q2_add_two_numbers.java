package DataStructure.LinkedList;

import java.util.Scanner;

public class q2_add_two_numbers {

//          class ListNode {
//             ListNode next;
//             int val;
//             public ListNode(int val) {
//                 this.val = val;
//                 next = null;
//             }
//         }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode p1 = l1, p2 = l2;
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (p1 != null || p2 != null || carry != 0) {
            int curSum = carry;
            if (p1 != null) {
                curSum += p1.val;
                p1 = p1.next;
            }
            if (p2 != null) {
                curSum += p2.val;
                p2 = p2.next;
            }
            cur.next = new ListNode(curSum % 10);
            carry = curSum / 10;
            cur = cur.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        //        Scanner sc = new Scanner(System.in);
        int[] l1 = {2, 4, 3};
        int[] l2 = {5, 6, 4};

        q2_add_two_numbers sol = new q2_add_two_numbers();
        ListNode list1 = sol.createListNode(l1);
        ListNode list2 = sol.createListNode(l2);
        ListNode node = sol.addTwoNumbers(list1, list2);
        sol.printList(node);
    }

    /**
     * intput:
     * 3
     * 2 4 3
     * 3
     * 5 6 4
     * <p>
     * out:
     * 7, 0, 8
     *
     * @param args
     */
    public static void main02(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n1 = sc.nextInt(); // 链表长度
//        sc.nextLine();
        ListNode l1 = getListNode(n1, sc);
        int n2 = sc.nextInt(); // 链表长度
//        sc.nextLine();
        ListNode l2 = getListNode(n2, sc);
        sc.close();

        q2_add_two_numbers sol = new q2_add_two_numbers();
        sol.printList(sol.addTwoNumbers(l1, l2));
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

    public void printList(ListNode list) {
        ListNode p = list;
        while (p != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }
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


}
