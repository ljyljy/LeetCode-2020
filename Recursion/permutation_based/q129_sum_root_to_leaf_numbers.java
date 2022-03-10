package Recursion.permutation_based;

import java.util.*;

public class q129_sum_root_to_leaf_numbers {
    // 法2：递归，累加路径和
    public int sumNumbers_dfs(TreeNode root) {
        if (root == null) return 0;
        return dfs2(root, 0);
    }

    private int dfs2(TreeNode root, int curSum) {
        if (root == null) return 0;
        curSum = curSum * 10 + root.val;
        if (root.left == null && root.right == null) {  // 叶子
            return curSum;
        } else { // 非叶子：上层+自己+左右子树
            return dfs2(root.left, curSum) + dfs2(root.right, curSum);
        }
    }

    // 法1：普通-记录path（sb）：dfs回溯
    List<Integer> res = new ArrayList<>(); // 每个path的和
    StringBuilder path = new StringBuilder();
    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        path.append(root.val); // 勿漏? 起点要额外加入path！类比q257/113/129/126
        dfs(root);
        int sum = 0;
        for (int path: res) {
            sum += path;
        }
        return sum;
    }

    private void dfs(TreeNode root) {
        if (root.left == null && root.right == null) {
            res.add(Integer.valueOf(path.toString()));
            return;
        }
        if (root.left != null) {
            path.append(root.left.val);
            dfs(root.left);
            path.deleteCharAt(path.length()-1);
        }
        if (root.right != null) {
            path.append(root.right.val);
            dfs(root.right);
            path.deleteCharAt(path.length()-1);
        }
    }
}
