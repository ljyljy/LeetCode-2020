package DP;

import java.util.HashMap;
import java.util.Map;

public class q337_house_robber_iii {
    // ��1��dp [���롾���������]
    public int rob(TreeNode root) {
        int[] res = dpHelper(root);
        return Math.max(res[0], res[1]);
    }

    private int[] dpHelper(TreeNode root) {
        if (root == null) return new int[]{0, 0};
        // int[] res = new int[2]; // 0-ѡ��1-��ѡ

        int[] resL = dpHelper(root.left);
        int[] resR = dpHelper(root.right);
        // 1. ѡroot - �����Һ��Ӷ���ѡ������Math.max!����ͣ�?��
        int choose = root.val + resL[1] + resR[1];
        // 2. ��ѡroot - ���Һ�������
        int notChoose = Math.max(resL[0], resL[1]) + Math.max(resR[0], resR[1]) ;
        return new int[]{choose, notChoose};
    }


    // ��1��dfs + memo, ʱ��O(n), �ռ�O(logn)-�������ϵ���ϵͳջ��ʱ��
    private Map<TreeNode, Integer> memo = new HashMap<>(); // <node, maxPrice>
    public int rob_dfs(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return root.val;
        return dfs(root, memo);
    }

    private int dfs(TreeNode root, Map<TreeNode, Integer> memo) {
        if (root == null) return 0;
        if (memo.containsKey(root)) return memo.get(root);

        // 1. ѡroot
        int choose = root.val;
        if (root.left != null) {
            choose += dfs(root.left.left, memo) + dfs(root.left.right, memo);
        }
        if (root.right != null) {
            choose += dfs(root.right.left, memo) + dfs(root.right.right, memo);
        }

        // 2. ��ѡroot
        int notChoose = 0;
        notChoose += dfs(root.left, memo) + dfs(root.right, memo);

        int res = Math.max(choose, notChoose);
        memo.put(root, res);
        return res;
    }
}
