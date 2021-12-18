package Recursion.combination_based;

import BFS.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class q113_path_sum_ii {
//    见qjz_34
    // 代码随想录：∵找全局/所有解 ∴dfs无需返回值！
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) return res;
        Deque<Integer> path = new ArrayDeque<>();
        path.addLast(root.val);
        dfs(root, sum - root.val, path);
//        path.removeLast(); // 可以不加
        return res;
    }

    private void dfs(TreeNode root, int sum, Deque<Integer> path) {
        if (root.left == null && root.right == null) {
            if (sum == 0) {
                res.add(new ArrayList<>(path));
            }
            return;
        }

        if (root.left != null) {
            path.addLast(root.left.val);
            dfs(root.left, sum - root.left.val, path);
            path.removeLast();
        }
        if (root.right != null)  {
            path.addLast(root.right.val);
            dfs(root.right, sum - root.right.val, path);
            path.removeLast();
        }
    }
}
