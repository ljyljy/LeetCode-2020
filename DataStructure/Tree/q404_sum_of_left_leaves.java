package DataStructure.Tree;

import java.util.ArrayDeque;
import java.util.Deque;

public class q404_sum_of_left_leaves {
    // ��1: �ݹ�
    public int sumOfLeftLeaves_dfs(TreeNode root) {
        if (root == null) return 0;
        int leftSum = sumOfLeftLeaves(root.left); // ������
        int rightSum = sumOfLeftLeaves(root.right); // ������
        TreeNode leftSon = root.left;
        int leftSonVal = 0;
        if (leftSon != null && leftSon.left == null && leftSon.right == null) { // ������ǡ����Ҷ��
            leftSonVal = leftSon.val;
        }
        return leftSum + rightSum + leftSonVal;
    }

    // ��2������(stack/queue����)
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
