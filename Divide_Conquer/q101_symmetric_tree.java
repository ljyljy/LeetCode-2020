package Divide_Conquer;

import java.util.*;

public class q101_symmetric_tree {
    // 法1：DFS
    public boolean isSymmetric_dfs(TreeNode root) {
        if (root == null) return false;
        return compare(root.left, root.right);
    }

    private boolean compare(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        else if (left == null || right == null) return false;
        else if (left.val != right.val) return false; // 两树都不为空-> 比较值

        boolean outside = compare(left.left, right.right);
        boolean inside = compare(left.right, right.left);
        return outside && inside;
    }

    // 法2：BFS(不是层次遍历！无需while内嵌水平for(i=0~size))
    TreeNode nullNode = new TreeNode(Integer.MIN_VALUE);
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return true;

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root.left == null? nullNode : root.left);
        queue.offer(root.right == null? nullNode : root.right);
        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            if (queue.isEmpty()) return false;
            TreeNode right = queue.poll();
            if (left.equals(nullNode) && right.equals(nullNode))
                continue;
            else if (left.equals(nullNode) || right.equals(nullNode))
                return false;
            else if (left.val != right.val)
                return false;

            queue.offer(left.left == null? nullNode: left.left);
            queue.offer(right.right == null? nullNode: right.right); // 外侧
            queue.offer(left.right == null? nullNode: left.right);
            queue.offer(right.left == null? nullNode: right.left); // 内侧
        }
        return true;
    }
}
