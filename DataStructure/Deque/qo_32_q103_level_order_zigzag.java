package DataStructure.Deque;

import java.util.*;

public class qo_32_q103_level_order_zigzag {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        boolean isReverse = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            if (isReverse) Collections.reverse(level);
            res.add(level);
            isReverse = !isReverse;
        }
        return res;
    }
}
