package Recursion.permutation_based;


import java.util.*;

// 见q113 同一道题
public class qjz_34_path_sum_tree {
    //    Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    // 法1：回溯1(加法) —— 关注自己这一层
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        List<Integer> path = new ArrayList<Integer>(){{add(root.val);}};
        int cur_sum = root.val;
        helper(root, path, cur_sum, sum, res);
        return res;
    }

    private void helper(TreeNode root, List<Integer> path, int cur_sum, int sum, List<List<Integer>> res) {
        if (root == null) return;
        // meet leaf
        if (root.left == null && root.right == null) {
            if (cur_sum == sum) {
                res.add(new ArrayList<>(path));
                return;
            }
        }
        // go left
        if (root.left != null) {
            path.add(root.left.val);
            helper(root.left, path, cur_sum + root.left.val, sum, res);
            path.remove(path.size()-1);
        }
        // go right
        if (root.right != null) {
            path.add(root.right.val);
            helper(root.right, path, cur_sum + root.right.val, sum, res);
            path.remove(path.size()-1);
        }
    }


    // 法2：回溯2（减法）
    public List<List<Integer>> pathSum2(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        // 用栈来存储路径
        LinkedList<Integer> path = new LinkedList<>();
        dfs(root, path, sum, res);
        return res;
    }

    private void dfs(TreeNode root, LinkedList<Integer> path, int cur_left, List<List<Integer>> res) {
        if (root == null) return;

        path.addLast(root.val);

        // 1. 叶节点
        if (root.left == null && root.right == null) {
            if (cur_left == root.val)
                res.add(new ArrayList<>(path)); // 新的内存
        } else { // 2.非叶节点 （减去自己这一层，下探到下一层的时候，保证不受上一层约束）
            dfs(root.left, path, cur_left - root.val, res); // 并非 减去root.left.val!!!
            dfs(root.right, path, cur_left - root.val, res); // 并非 减去root.right.val!!!
        }
        // backtrack 回溯
        path.removeLast();
    }
}

