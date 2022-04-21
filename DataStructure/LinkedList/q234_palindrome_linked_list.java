package DataStructure.LinkedList;

import java.util.ArrayList;
import java.util.List;

public class q234_palindrome_linked_list {
    // ��2�����棬O(1)�ռ�
    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        // �ҵ�ǰ�벿�������β�ڵ㡾˫��Ϊ�к��ߡ�
        ListNode firstHalfEnd = getFirstHalfEnd(head);
        // ��ת��벿������
        ListNode secondHalfStart = reverseList(firstHalfEnd); // ԭend
        // �ж��Ƿ����
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        while (p2 != null) {
            if (p1.val != p2.val) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        // �ָ���벿������
        firstHalfEnd.next = reverseList(secondHalfStart); // ԭend
        return true;
    }

    private ListNode getFirstHalfEnd(ListNode head) {
        ListNode slow = head, fast = head;
        /* ��չ(q876)
        * // ��Ϊ�ڵ���Ϊ˫���������·��ء�ǰ�ߡ�
        while (fast.next != null && fast.next.next != null) {
        * */
        // ��Ϊ�ڵ���Ϊ˫���������·��ء����ߡ�
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

    // ��1�����棬O(n)�ռ�
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
