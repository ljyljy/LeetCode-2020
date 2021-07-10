package DataStructure.Tree;

import java.util.*;

public class q938_range_sum_of_bst {
    class TreeNode {
        int val;
        TreeNode left, right;
        public TreeNode(){}
        public TreeNode(int val) {this.val = val;}
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val; this.left = left; this.right = right;
        }
    }

    private int ans = 0;
    // 法1：递归 - 中序
    public int rangeSumBST1(TreeNode root, int low, int high) {
        inorder(root, low, high);
        return ans;
    }

    private void inorder(TreeNode root, int low, int high) {
        if (root == null) return;
        inorder(root.left, low, high);
        if (low <= root.val && root.val <= high) ans += root.val;
        inorder(root.right, low, high);
    }

    // 法2：非递归 - 中序
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) return 0;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left; // 1. 左
            } else{
                p = stack.pop(); // 2. 根
                if (low <= p.val && p.val <= high)
                    ans += p.val;
                p = p.right; // 3. 右
            }
        }
        return ans;
    }
}
