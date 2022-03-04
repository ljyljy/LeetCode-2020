package DataStructure.Tree;

public class q109_convert_sorted_list_to_BST {
    // ��1���������+����
    ListNode cur;
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        cur = head;
        // �������ȣ�
        int n = 0;
        ListNode p = head;
        while (p != null) {
            n++;
            p = p.next;
        }
        return build(0, n-1);
    }

    private TreeNode build(int start, int end) {
        if (start > end) return null;
        int mid = start + end + 1>> 1; // [L, mid] [mid+1, end]
        TreeNode root = new TreeNode(); // ���Բ�����ֵ��

        root.left = build(start, mid-1); // ��
        root.val = cur.val; // �У������ڴ˴�����/����rootֵ��
        cur = cur.next;
        root.right = build(mid+1, end); // ��
        return root;
    }

    // ��2������: todo
//    https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree/solution/you-xu-lian-biao-zhuan-huan-er-cha-sou-suo-shu-1-3/
    public TreeNode sortedListToBST2(ListNode head) {
        return dfs2(head, null);
    }

    private TreeNode dfs2(ListNode left, ListNode right) {
        if (left == right) return null;
        ListNode mid = getMid(left, right);
        TreeNode root = new TreeNode(mid.val);
        root.left = dfs2(left, mid);
        // root.val = mid.val; // ����root����֤�����������ѡ��
        root.right = dfs2(mid.next, right);
        return root;
    }

    private ListNode getMid(ListNode left, ListNode right) {
        ListNode slow = left, fast = left;
        while (fast != right && fast.next != right) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
        // �����ͨ��left=head�� right=null
        // ListNode slow = head, fast = head;
        // while (fast != null && fast.next != null) {
        //     fast = fast.next.next;
        //     slow = slow.next;
        // }
        // return slow;
    }

}
