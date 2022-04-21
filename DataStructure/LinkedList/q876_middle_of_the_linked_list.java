package DataStructure.LinkedList;

public class q876_middle_of_the_linked_list {
    // 法2：单指针
    public ListNode middleNode_2(ListNode head) {
        if (head == null) return head;
        int n = 0; // 链表长度
        ListNode cur = head;
        while (cur != null) {
            n++;
            cur = cur.next;
        }
        int k = 0;
        cur = head;
        while (k < n / 2) {
            k++;
            cur = cur.next;
        }
        return cur;
    }
    // 法3：快慢指针 - 双指针, 应用：q876, 234,
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        /* 扩展
        * // 若为节点数为双数，则以下返回【前者】
        while (fast.next != null && fast.next.next != null) {
        * */
        // 若为节点数为双数，则以下返回【后者】
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

}
