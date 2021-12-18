package Recursion.permutation_based;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class q9_246_binary_tree_path_sum_ii {
    //    Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> binaryTreePathSum2(TreeNode root, int sum) {
        if (root == null) return res;
        List<Integer> buffer = new ArrayList<>();
        dfs(root, buffer, 0, sum);
        return res;
    }

    private void dfs(TreeNode root, List<Integer> buffer, int level, int sum) {
        if (root == null) return;

        buffer.add(root.val);
        int cur_left = sum;
        for (int i = level; i >= 0; --i) {// 本层节点，接着往上回溯找路径
            cur_left -= buffer.get(i);// ←cur_node
            if (cur_left == 0) { // 找到路径后，再按序放入path
                LinkedList<Integer> path = new LinkedList<>();
                for (int j = i; j <= level; ++j) // 【由头i下探到cur_node】
                    path.addLast(buffer.get(j));
                res.add(new ArrayList<>(path));
            }
        }
        dfs(root.left, buffer, level+1, sum);
        dfs(root.right, buffer, level+1, sum);
        buffer.remove(buffer.size()-1);
    }
}
