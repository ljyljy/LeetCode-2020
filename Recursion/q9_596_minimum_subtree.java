package Recursion;




public class q9_596_minimum_subtree {
    //Definition of TreeNode:
    public class TreeNode {
        public int val;
        public TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    private TreeNode minNode = null;
    private int minSum = Integer.MAX_VALUE;
    public TreeNode findSubtree(TreeNode root) {
        minSum = dfs(root);
        return minNode;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int sum = root.val + dfs(root.left) + dfs(root.right);
        if (sum < minSum) {
            minSum = sum;
            minNode = root;
        }
        return sum;
    }
}
