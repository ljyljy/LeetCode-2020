package DataStructure.LinkedList;

import java.util.Random;

public class q92_reverse_n_linked_node {
    ListNode successor = null;  // 后驱结点

    // 反转以 head 为起点的 n 个节点，返回新的头结点
    public ListNode reverseN(ListNode head, int n){
        if (n == 1) {
            successor = head.next;  // 记录第 n + 1 个节点
            return head;
        }
        // 以 head.next 为起点，需要反转前 n - 1 个节点g
        ListNode dummy = reverseN(head.next, n-1);
        head.next.next = head;
        head.next = successor;
        return dummy;
    }

    public void printLinkedList(ListNode listNode){
        ListNode cur0 = listNode;
        while (cur0 != null) {
            System.out.print(cur0.val + " ");
            cur0 = cur0.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode cur = listNode;
        for (int i = 2; i < 6; i++) {
            cur.next = new ListNode(i);// new Random().nextInt(xxx); 或 Math.random()❤
            cur = cur.next;
        }


        q92_reverse_n_linked_node sol = new q92_reverse_n_linked_node();
        sol.printLinkedList(listNode);

        ListNode listNode_ = sol.reverseN(listNode, 3);
        sol.printLinkedList(listNode_);
    }
}

