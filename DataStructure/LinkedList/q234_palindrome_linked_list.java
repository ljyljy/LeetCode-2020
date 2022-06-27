package DataStructure.LinkedList;

import java.util.ArrayList;
import java.util.List;

public class q234_palindrome_linked_list {
    // 法2-2（更简单，复杂度介于法1~2之间）
    //      先复制链表，然后反转-》rev。同向双指针。
    public boolean isPalindrome2_2(ListNode head) {
        ListNode copy = copyNode(head);
        ListNode rev = reverseNode(copy);
        // while (rev != null) {
        //     System.out.println(rev.val);
        //     rev = rev.next;
        // }

        while (rev != null && head != null) {
            if (rev.val != head.val) return false;
            rev = rev.next;
            head = head.next;
        }
        return true;
    }

    private ListNode copyNode (ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode node = new ListNode(-1);
        dummy = node;
        ListNode p = head;
        while (p != null) {
            node.next = new ListNode(p.val);
            p = p.next;
            node = node.next;
        }
        return dummy.next;
    }

    private ListNode reverseNode (ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode nxt = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nxt;
        }
        return prev;
    }

    // 法2：常规，O(1)空间
    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        // 找到前半部分链表的尾节点【双数为中后者】
        ListNode firstHalfEnd = getFirstHalfEnd(head);
        // 反转后半部分链表
        ListNode secondHalfStart = reverseList(firstHalfEnd); // 原end
        // 判断是否回文
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        while (p2 != null) {
            if (p1.val != p2.val) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        // 恢复后半部分链表
        firstHalfEnd.next = reverseList(secondHalfStart); // 原end
        return true;
    }

    private ListNode getFirstHalfEnd(ListNode head) {
        ListNode slow = head, fast = head;
        /* 扩展(q876)
        * // 若为节点数为双数，则以下返回【前者】
        while (fast.next != null && fast.next.next != null) {
        * */
        // 若为节点数为双数，则以下返回【后者】
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reverseList(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    // 法1：常规，O(n)空间
    public boolean isPalindrome1(ListNode head) {
        if (head == null) return true;
        List<Integer> nodes = new ArrayList<>();
        ListNode p = head;
        while (p != null) {
            nodes.add(p.val);
            p = p.next;
        }
        int n = nodes.size();
        int i = 0, j = n-1;
        while (i < j) {
            int valL = nodes.get(i++);
            int valR = nodes.get(j--);
            if (valL != valR) {
                return false;
            }
        }
        return true;
    }
}
