package DataStructure.LinkedList;

public class q83_remove_duplicates_from_sorted_list {
    // ���q82��ɾ�������ظ����
    // Q83: �����ظ���ͷ���
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p = head, q = head; // ��ͬ1��Q82:p=dummy(��head�����ظ�->��ɾ)
        while (p != null) {// ��ͬ2��Q82:p.next != null (��p��dummy)
            q = p.next;
            // ��ͬ3��Q82��ȫɾ��p.next.val == q.val��p���ظ�����ǰ��
            // Q83�������ظ��ĵ�һ����㣬p����ָ��ǰ����ָ���һ���ظ�����Լ�����
            while (q != null && p.val == q.val) {
                q = q.next;
            }
            if (q == p.next) {
                p = p.next;
            } else {
                p.next = q;
            }
        }
        return dummy.next;
    }
}
