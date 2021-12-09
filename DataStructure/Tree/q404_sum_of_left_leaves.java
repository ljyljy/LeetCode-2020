package DataStructure.Tree;

import java.util.ArrayDeque;
import java.util.Deque;

public class q404_sum_of_left_leaves {
    // 法1: 递归
    public int sumOfLeftLeaves_dfs(TreeNode root) {
        if (root == null) return 0;
        int leftSum = sumOfLeftLeaves(root.left); // 左孙子
        int rightSum = sumOfLeftLeaves(root.right); // 右孙子
        TreeNode leftSon = root.left;
        int leftSonVal = 0;
        if (leftSon != null && leftSon.left == null && leftSon.right == null) { // 左孙子恰是左叶子
            leftSonVal = leftSon.val;
        }
        return leftSum + rightSum + leftSonVal;
    }

    // 法2：迭代(stack/queue都行)
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        int sum = 0;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            TreeNode nodeLeftSon = node.left;
            if (nodeLeftSon != null &&
                    nodeLeftSon.left == null && nodeLeftSon.right == null) {
                sum += nodeLeftSon.val;
            }
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }
        return sum;
    }
}
