package Divide_Conquer;

import java.util.ArrayDeque;
import java.util.Deque;

public class q111_minimum_depth_of_binary_tree {
    // 1.递归
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int minDepth_L = minDepth(root.left);
        int minDepth_R = minDepth(root.right);

        // 1.2.左/右子树其一为空（此时的根并非最低点(∵叶子)，还需往非空子树下探）
        if (root.left == null) return 1 + minDepth_R;
        if (root.right == null) return 1 + minDepth_L;

        return 1 + Math.min(minDepth_L, minDepth_R); // 3.左右子树非空
    }

    // 2.迭代 [层次遍历]
    public int minDepth_bfs(TreeNode root) {
        if (root == null) return 0;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int min_level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            min_level++;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null)
                    return min_level;
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return min_level;
    }
}
