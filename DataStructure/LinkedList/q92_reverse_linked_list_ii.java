package DataStructure.LinkedList;


public class q92_reverse_linked_list_ii {
//    法1： 递归
    private ListNode successor = null;  // 后驱结点

    // 反转以 head 为起点的 n 个节点，返回新的头结点
    private ListNode reverseN(ListNode head, int n){
        if (n == 1) {
            successor = head.next;  // 记录第 n + 1 个节点
            return head;
        }
        // 以 head.next 为起点，需要反转前 n - 1 个节点
        ListNode dummy = reverseN(head.next, n-1);
        head.next.next = head;
        head.next = successor;
        return dummy;
    }

    public ListNode reverseBetween0(ListNode head, int m, int n){
        if (m == 1){
            return reverseN(head, n);
        }
        head.next = reverseBetween0(head.next, m - 1, n - 1);
        return head;
    }

//    法2：迭代
    public ListNode reverseBetween(ListNode head, int left, int right){
        if (left >= right || head == null) return head;

        // 因为头节点有可能发生变化，使用虚拟头节点可以避免复杂的分类讨论
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = dummy;
        // 1. 从dummy开始，后移left-1步(举例画图)，到达pre（left前继）
        for (int i = 0; i < left-1; i++){
            pre = pre.next;
        }

        // 2. 从pre开始, 后移 right - left + 1步，到达right
        ListNode rightNode = pre;
        for (int i = 0; i < right - left + 1; i++){
            rightNode = rightNode.next;
        }

        // 3. 截取[left, right]子链表
        ListNode leftNode = pre.next;
        ListNode succ = rightNode.next;
        // 切断连接
        pre.next = null;
        rightNode.next = null;

        // 4. 同q206.反转链表(子链表)
        reverseLinkedList(leftNode);

        // 5. 接回原链表
        pre.next = rightNode;
        leftNode.next = succ;

        return dummy.next;
    }

    private void reverseLinkedList(ListNode head) {
        // 法1：迭代
        ListNode prev = null, cur = head;
        while (cur != null){
            ListNode nxt = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nxt;
        }
    }

    private ListNode reverseLinkedList2(ListNode head) {
        // 法2：递归
        if(head == null || head.next == null) return head;

        ListNode dummy = reverseLinkedList2(head.next);
        head.next.next = head;
        head.next = null;
        return dummy;
    }
}
