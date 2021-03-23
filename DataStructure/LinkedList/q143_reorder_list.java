package DataStructure.LinkedList;

import java.util.ArrayList;
import java.util.List;

public class q143_reorder_list {
    // 法1：线性表存储数组下标 - 时间O(n)，空间O(n)
    public void reorderList_1(ListNode head) {
        if (head == null) return;
        List<ListNode> listNodes = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            listNodes.add(node);
            node = node.next;
        }
        int n = listNodes.size();
        int i = 0, j = n - 1;
        for (i = 0, j = n - 1; i < j; i++, j--) {
            ListNode first_node = listNodes.get(i);
            ListNode sec_node = listNodes.get(i+1);
            ListNode last_node = listNodes.get(j);

            first_node.next = last_node;
            last_node.next = sec_node;
        }  // i == j 退出
        listNodes.get(i).next = null;
    }

    // 法2（最优）：寻找链表中点 + 链表逆序 + 合并链表 - 时间O(n)，空间O(1)
    public void reorderList(ListNode head) {
        if (head == null) return;
        ListNode mid = get_midNode(head);
        ListNode l1 = head;
        ListNode l2 = mid.next;
        // System.out.println("mid.val="+mid.val);
        mid.next = null; // 切断l1 & l2
        l2 = reverseList(l2);
        mergeList(l1, l2);
    }

    private ListNode get_midNode(ListNode head) {
        if (head == null) return head;
        ListNode slow = head, fast = head;
        /* 扩展
        * // 若为节点数为双数，则以下返回【后者】
        while (fast != null && fast.next != null) {
        * */
        // 若为节点数为双数，则以下返回【前者】
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow; // 返回中点(向[上]取整 - 与q876不同！)
    }

    private ListNode reverseList(ListNode head) {
        if (head == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = null, cur = head;
        while (cur != null) {
            ListNode nxt = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nxt;
        }
        return prev;
    }

    private void mergeList(ListNode l1, ListNode l2) {
        ListNode l1_sec, l2_sec;
        while (l1 != null && l2 != null) {
            l1_sec = l1.next;
            l2_sec = l2.next;

            l1.next = l2;
            l1 = l1_sec;  // 指针后移
            l2.next = l1;
            l2 = l2_sec;  // 指针后移
        }
    }
}
