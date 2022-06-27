package DataStructure.LinkedList;


public class q92_reverse_linked_list_ii {
    // 法1：迭代（new）
    // 类比q25❤
    // dummy -> head/p -> (start, ..., end) -> next...
    // dummy -> head/p -> (end, ..., start) -> next...
    public ListNode reverseBetween1(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode start_prev = dummy, end = dummy;
        for (int i = 0; i < left-1; i++) start_prev = start_prev.next;
        for (int i = 0; i < right; i++) end = end.next;
        ListNode start = start_prev.next;
        ListNode nxt = end.next;

        end.next = null;
        start_prev.next = reverse_1(start);
        start.next = nxt;
        return dummy.next;
    }

    // 法1：q206，类比q25❤
    private ListNode reverse_1(ListNode head) {
        // pre -> (start/cur, nxt, ..., end)
        // pre <- (start/cur, nxt, ..., end)
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    // 法2：递归，类比q92, 206?
    //      head -> nxt ... -> end -> ^
    // ^ <- head <- [nxt ... <- end/last]
    private ListNode reverse_2(ListNode head) {
        if (head == null || head.next == null) return head;// 而非 返回null！
        ListNode nxt = head.next;
        ListNode last = reverse_2(nxt); // 返回[end <- ... <- nxt/last], 尾部倒序KO
        // 将头倒序
        nxt.next = head;
        head.next = null; // 勿忘！否则成环！原始head.next没有断开！
        return last;
    }

    // 法1-迭代写法2（old）
    public ListNode reverseBetween_V1_old(ListNode head, int left, int right){
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

        // 4. 同q206.反转链表(子链表), q25❤
        reverseLinkedList(leftNode);

        // 5. 接回原链表
        pre.next = rightNode;
        leftNode.next = succ;

        return dummy.next;
    }

    private void reverseLinkedList(ListNode head) {
        // pre -> (start/cur, nxt, ..., end)
        // pre <- (start/cur, nxt, ..., end)
        // 法1：迭代
        ListNode prev = null, cur = head;
        while (cur != null){
            ListNode nxt = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nxt;
        }
    }

    // 2、递归：类比q92, 206❤
    // head -> ... ->  nxt(m--) ... -> end -> ^
    // head -> ... -> [nxt(m=1) ... <- end/last] （接head）-> succ -> ...
    ListNode successor = null;// 后驱结点
    // 反转以 head 为起点的 n 个节点，返回新的头结点
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == 1) { // 走到[m, n]处，以m为头，翻转n个结点
            return reverseTopN(head, n); // 返回[2<-3<-4(head)]
        }
        head.next = reverseBetween(head.next, m-1, n-1); // head后移，m--直到1(走到m开头)
        return head;
    }

    private ListNode reverseTopN(ListNode head, int n) {
        if (n == 1) { // 走到[m, n]末尾n处(head)，需要记录后继succ
            successor = head.next; // [2<-3<-4(head, n-1)] | 5(succ)
            return head;
        }
        ListNode last = reverseTopN(head.next, n-1); // 2, [3<-4]
        head.next.next = head; // 正序head.next == last.tail
        head.next = successor;
        return last;
    }
}
