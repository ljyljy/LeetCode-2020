package Divide_Conquer;

import java.util.ArrayDeque;
import java.util.Deque;

public class q104_maximum_depth_of_binary_tree {
    // 法1：[变相求{根的高度==最大深度}]（高度↑, 左右根，后序）
    public int maxDepth_1(TreeNode root) {
        if (root == null) return 0;
        int depth_L = maxDepth_1(root.left);
        int depth_R = maxDepth_1(root.right);
        return 1 + Math.max(depth_L, depth_R);
    }

    // 法2：[常规]（深度↓，根左右，前序）
    private int result = 0;
    public int maxDepth_2(TreeNode root) {
        if (root == null) return 0;
        getHeight(root, 1);
        return result;
    }

    private void getHeight(TreeNode root, int height) {
        result = height > result? height: result;
        if (root == null) return;
        if (root.left != null)
            getHeight(root.left, height+1); // 回溯
        if (root.right != null)
            getHeight(root.right, height+1);
    }

    // 法3：[迭代](层次遍历)
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int n_level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            n_level++;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return n_level;
    }


}
