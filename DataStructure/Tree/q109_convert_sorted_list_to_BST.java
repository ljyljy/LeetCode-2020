package DataStructure.Tree;

public class q109_convert_sorted_list_to_BST {
    // 法1：中序遍历+构造
    ListNode cur;
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        cur = head;
        // 求链表长度！
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
        TreeNode root = new TreeNode(); // 可以不传入值！

        root.left = build(start, mid-1); // 左
        root.val = cur.val; // 中：必须在此处更新/覆盖root值！
        cur = cur.next;
        root.right = build(mid+1, end); // 右
        return root;
    }

    // 法2：分治: todo
//    https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree/solution/you-xu-lian-biao-zhuan-huan-er-cha-sou-suo-shu-1-3/
    public TreeNode sortedListToBST2(ListNode head) {
        return dfs2(head, null);
    }

    private TreeNode dfs2(ListNode left, ListNode right) {
        if (left == right) return null;
        ListNode mid = getMid(left, right);
        TreeNode root = new TreeNode(mid.val);
        root.left = dfs2(left, mid);
        // root.val = mid.val; // 更新root，保证中序遍历（可选）
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
        // 类比普通：left=head， right=null
        // ListNode slow = head, fast = head;
        // while (fast != null && fast.next != null) {
        //     fast = fast.next.next;
        //     slow = slow.next;
        // }
        // return slow;
    }

}
