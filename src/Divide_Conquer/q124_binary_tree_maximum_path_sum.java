package Divide_Conquer;

public class q124_binary_tree_maximum_path_sum {
    int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        helper(root);
        return maxSum;
    }

    private int helper(TreeNode root) {
        if (root == null) return 0;
        // ↓可能存在负结点，尽量避免【若全是负结点，则令左右子树和为0，最后返回最小结点(as root)的val！！】
        int left = Math.max(0, helper(root.left));
        int right = Math.max(0, helper(root.right));
        maxSum = Math.max(maxSum, left + right + root.val);
        // 因为是从一个结点出发，而返回的应为当前节点及其子路径[左or右子树之一]，故应为“单边最大和”
        return Math.max(left, right) + root.val;
    }
}
