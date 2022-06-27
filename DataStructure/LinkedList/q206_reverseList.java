package DataStructure.LinkedList;

import java.util.Scanner;

public class q206_reverseList {
    // 法1：迭代（new）
    // 类比q206, 92, 25
    // dummy/pre -> head/cur -> nxt ...
    // dummy/pre <- head/cur <- nxt
    public ListNode reverseList1(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    // 法2：递归
    // 类比q206, 92, 25
    //      head -> nxt ... -> end -> ^
    // ^ <- head <- [nxt ... <- end/last]
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode nxt = head.next;
        ListNode last = reverseList(nxt); // 返回[end <- ... <- nxt/last], 尾部倒序KO
        // 将头倒序
        nxt.next = head;
        head.next = null; // ?勿忘！否则成环！原始head.next没有断开！
        return last;
    }


    private void printListNode (ListNode head) {
        StringBuilder sb = new StringBuilder();
        ListNode p = head;
        while (p != null) {
            sb.append(p.val).append("->");
            p = p.next;
        }
        // 去掉最后的"->"
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
    }

    // 练习：链表的输入输出(见q206, q25)
    public static void main(String[] args) {
        q206_reverseList sol = new q206_reverseList();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            ListNode dummy = new ListNode();
            ListNode p = dummy;
            for (int i = 0; i < n; i++) {
                p.next = new ListNode(sc.nextInt()); // 必须是.next！
                p = p.next;
            }
            p = null;

            System.out.println("origin ListNode: ");
            sol.printListNode(dummy.next);

            System.out.println("after reversion: ");
            ListNode reversed = sol.reverseList(dummy.next);
            sol.printListNode(reversed);

            /**
             * 输入：
             * 5
             * 1 2 3 4 5
             * 输出：
             * origin ListNode:
             * 1->2->3->4->5
             * after reversion:
             * 5->4->3->2->1
             */
        }
    }
}
