package DataStructure.LinkedList;

import java.util.Scanner;

//class ListNode {
//    int val;
//    ListNode next;
//    public ListNode(){}
//    public ListNode(int val){this.val = val; this.next = null;}
//}

public class q19_HJ51_printLastK_Node {
    // 类似LC_Q19：删除倒数第k个结点（需要slow_prev = dummy）
    private static ListNode getLastKNode(ListNode head, int k) {
        if (head == null) return null;
        ListNode slow = head, fast = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            // 生成链表?
            ListNode dummy = new ListNode(-1);
            ListNode p = dummy;
            for (int i = 0; i < n; i++) {
                p.next = new ListNode(sc.nextInt());
                p = p.next;
            }
            int k = sc.nextInt();
            ListNode res = getLastKNode(dummy.next, k);
            System.out.println(res == null? 0: res.val);
        }
    }


}

