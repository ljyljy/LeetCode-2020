package DataStructure.LinkedList;

import java.util.ArrayDeque;
import java.util.Deque;

public class q19_remove_nth_node_from_end_of_list {
    // 法3(一趟遍历)：双指针  -  时间：O(N); 空间：O(1)。
    public ListNode removeNthFromEnd(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast = head, slow_prev = dummy; // slow=dummy，则最终恰好指向待删的前驱
        for (int i = 0; i < k; i++) {
            fast = fast.next; // fast先于slow: k(+1)步(∵dummy)
        }
        while (fast != null){
            fast = fast.next;
            slow_prev = slow_prev.next;
        }  // fast到结尾null，slow到达待删前驱
        slow_prev.next = slow_prev.next.next;
        return dummy.next;
    }


    // 法1：思路由剑指22启发（需先遍历求链表长度n）
    // 时间：O(N); 空间：O(1)。
    public ListNode removeNthFromEnd_0(ListNode head, int k) {
        // 倒数第k == 正数第n-k+1
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p = head;
        ListNode prev = dummy;
        int n = 0;

        while (p != null) {
            p = p.next;
            n++;
        }

        p = head;
        for(int i = 0; i < n-k; i++){
            prev = p;
            p = p.next; // p从head，后移(n-k+1)-1步
        }
        prev.next = p.next;
        return dummy.next;
    }

    // 法2：栈  -  时间：O(N); 空间：O(N)。
    public ListNode removeNthFromEnd_2(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy; // 注意不是cur = head！！！
        Deque<ListNode> deque = new ArrayDeque<>();
        while (cur != null) {
            deque.push(cur);
            cur = cur.next;
        }
        while (k > 0) {
            deque.removeFirst();
            k--;
        }
        ListNode prev = deque.peekFirst();
        prev.next = prev.next.next;
        return dummy.next;
    }


}
