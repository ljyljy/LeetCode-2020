package DataStructure.Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class q523_find_bottom_left_tree_value {
    // 法1：层序
    public int findBottomLeftValue_levelOrder(TreeNode root) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        List<Integer> levelHeads = new ArrayList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll(); // 中
                if (i == 0) levelHeads.add(cur.val); // 每层的头
                if (cur.left != null) queue.offer(cur.left); // 左
                if (cur.right != null) queue.offer(cur.right); // 右
            }
        }
        return levelHeads.get(levelHeads.size()-1); // 倒一
    }

    // 法2：递归（前序）
    private int maxDepth = 0;
    private int maxLeftVal = Integer.MIN_VALUE;
    public int findBottomLeftValue_dfs(TreeNode root) {
        dfs(root, 1);
        return maxLeftVal;
    }

    private void dfs(TreeNode root, int curDepth) {
        if (root == null) return;
        if (root.left == null && root.right == null) { // 中
            if (curDepth > maxDepth) {
                maxDepth = curDepth;
                maxLeftVal = root.val;
            }
        }
        if (root.left != null) dfs(root.left, curDepth+1); // 左
        if (root.right != null) dfs(root.right, curDepth+1); // 右
    }
}
