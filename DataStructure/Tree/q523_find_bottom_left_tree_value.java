package DataStructure.Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class q523_find_bottom_left_tree_value {
    // ��1������
    public int findBottomLeftValue_levelOrder(TreeNode root) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        List<Integer> levelHeads = new ArrayList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll(); // ��
                if (i == 0) levelHeads.add(cur.val); // ÿ���ͷ
                if (cur.left != null) queue.offer(cur.left); // ��
                if (cur.right != null) queue.offer(cur.right); // ��
            }
        }
        return levelHeads.get(levelHeads.size()-1); // ��һ
    }

    // ��2���ݹ飨ǰ��
    private int maxDepth = 0;
    private int maxLeftVal = Integer.MIN_VALUE;
    public int findBottomLeftValue_dfs(TreeNode root) {
        dfs(root, 1);
        return maxLeftVal;
    }

    private void dfs(TreeNode root, int curDepth) {
        if (root == null) return;
        if (root.left == null && root.right == null) { // ��
            if (curDepth > maxDepth) {
                maxDepth = curDepth;
                maxLeftVal = root.val;
            }
        }
        if (root.left != null) dfs(root.left, curDepth+1); // ��
        if (root.right != null) dfs(root.right, curDepth+1); // ��
    }
}
