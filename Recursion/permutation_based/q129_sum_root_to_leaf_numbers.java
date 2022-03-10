package Recursion.permutation_based;

import java.util.*;

public class q129_sum_root_to_leaf_numbers {
    // ��2���ݹ飬�ۼ�·����
    public int sumNumbers_dfs(TreeNode root) {
        if (root == null) return 0;
        return dfs2(root, 0);
    }

    private int dfs2(TreeNode root, int curSum) {
        if (root == null) return 0;
        curSum = curSum * 10 + root.val;
        if (root.left == null && root.right == null) {  // Ҷ��
            return curSum;
        } else { // ��Ҷ�ӣ��ϲ�+�Լ�+��������
            return dfs2(root.left, curSum) + dfs2(root.right, curSum);
        }
    }

    // ��1����ͨ-��¼path��sb����dfs����
    List<Integer> res = new ArrayList<>(); // ÿ��path�ĺ�
    StringBuilder path = new StringBuilder();
    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        path.append(root.val); // ��©? ���Ҫ�������path�����q257/113/129/126
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
