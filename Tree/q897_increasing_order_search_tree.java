package Tree;

import java.util.*;

public class q897_increasing_order_search_tree {
    class TreeNode {
        int val;
        TreeNode left, right;
        public TreeNode(){}
        public TreeNode(int val) {this.val = val;}
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val; this.left = left; this.right = right;
        }
    }
    // 法1：递归中序 + res后新建链表
    List<Integer> res = new ArrayList<>();
    public TreeNode increasingBST1(TreeNode root) {
        if (root == null) return null;
        inorder(root);

        TreeNode dummy = new TreeNode(-1, null, root);
        TreeNode p = dummy;
        for (int val: res) {
            p.right = new TreeNode(val);
            p = p.right;
        }
        return dummy.right;
    }

    private void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        res.add(root.val);
        inorder(root.right);
    }

    // 法1-2：递归中序 + 过程中新建
    TreeNode dummy = new TreeNode(-1);
    TreeNode p = dummy;
    public TreeNode increasingBST1_2(TreeNode root) {
        if (root == null) return null;
        inorder2(root);
        return dummy.right;
    }

    private void inorder2(TreeNode root) {
        if (root == null) return;
        inorder2(root.left);
        p.right = new TreeNode(root.val);
        p = p.right;
        inorder2(root.right);
    }

    // 法2：非递归中序 + 过程中新建
    public TreeNode increasingBST(TreeNode root) {
        if (root == null) return null;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode dummy = new TreeNode(-1, null, root);
        TreeNode rt = dummy; // 用于创建dummy新链表
        TreeNode p = root; // 用于指向root，遍历
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left; // 1. 左
            } else {
                p = stack.pop(); // 2. 根
                rt.right = new TreeNode(p.val); // 必须新建，否则会牵连p后结点
                rt = rt.right;
                p = p.right; // 3. 右
            }
        }
        return dummy.right;
    }
}
