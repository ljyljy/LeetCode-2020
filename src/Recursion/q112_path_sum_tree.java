package Recursion;


public class q112_path_sum_tree {
    //    Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        return helper(root, 0, sum);
    }

    private boolean helper(TreeNode root, int cur_plus, int sum) {
        if (root == null) return false;
        cur_plus += root.val; // 不可以只写在else中，if中也应该是加上本层root.val后的cur_sum!!!
        // 1. 叶子结点 & 目标点
        if (cur_plus == sum && root.left == null && root.right == null) {
            return true;
        } else { // 2. 非叶子 || 非目标
            return helper(root.left, cur_plus, sum)
                    || helper(root.right, cur_plus, sum);
        }
    }
}
