package Recursion.combination_based;


public class q112_path_sum_tree {
    //    Definition for a binary tree node.

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    // 法0-代码随想录：∵找可行解(局部) ∴dfs有返回值！！
    public boolean hasPathSum0(TreeNode root, int sum) {
        if (root == null) return false;
        return dfs(root, sum - root.val);
    }

    private boolean dfs(TreeNode root, int sum) {
        if (root.left == null && root.right == null) {
            if (sum == 0) return true;
            else return false;
        }
        if (root.left != null) {
            if (dfs (root.left, sum - root.left.val)) {
                return true;
            }
        }
        if (root.right != null) {
            if (dfs (root.right, sum - root.right.val)) {
                return true;
            }
        }
        return false;
    }

    // 写法1
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
