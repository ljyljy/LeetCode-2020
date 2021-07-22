package DataStructure.LinkedList;

import java.util.ArrayDeque;
import java.util.Deque;

public class q445_add_two_numbers_ii {
    // 法1：栈（FIFO）-- 无需翻转链表，就可从后向前做加法
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        Deque<ListNode> deq1 = new ArrayDeque<>();
        Deque<ListNode> deq2 = new ArrayDeque<>();
        while (l1 != null) {
            deq1.push(l1);
            l1 = l1.next;
        }
        while (l2 != null) {
            deq2.push(l2);
            l2 = l2.next;
        }
        int carry = 0; // 进位(就算链表遍历结束,进位!=0仍需在前面新加carryNode)
        ListNode reversedNode = null; // 弹栈相加，依次向'前'串联(头<-尾)
        ListNode cur = null;
        while (!deq1.isEmpty() || !deq2.isEmpty()|| carry != 0) {
            int num1 = deq1.isEmpty()? 0: deq1.pop().val;
            int num2 = deq2.isEmpty()? 0: deq2.pop().val;
            int val = num1 + num2 + carry;
            carry = val / 10;
            val = val % 10; // ∴注意carry应在val之前被赋值
            // 不可以直接reversedNode = reversedNode.next(最后一步会变为null)
            // 【头插法】
            cur = new ListNode(val);// 如例1: cur=[8], reversedNode=[0->7]
            cur.next = reversedNode;// 拼接 新->旧, 变成8->0->7
            reversedNode = cur; // 串联好后，赋给reversedNode
        }
        return reversedNode;
    }
}
