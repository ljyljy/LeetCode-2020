package DP;

import java.util.HashMap;
import java.util.Map;

public class q337_house_robber_iii {
    // 法1：dp [必须【后序遍历】]
    public int rob(TreeNode root) {
        int[] res = dpHelper(root);
        return Math.max(res[0], res[1]);
    }

    private int[] dpHelper(TreeNode root) {
        if (root == null) return new int[]{0, 0};
        // int[] res = new int[2]; // 0-选，1-不选

        int[] resL = dpHelper(root.left);
        int[] resR = dpHelper(root.right);
        // 1. 选root - Σ左右孩子都不选（不是Math.max!是求和！?）
        int choose = root.val + resL[1] + resR[1];
        // 2. 不选root - 左右孩子随意
        int notChoose = Math.max(resL[0], resL[1]) + Math.max(resR[0], resR[1]) ;
        return new int[]{choose, notChoose};
    }


    // 法1：dfs + memo, 时间O(n), 空间O(logn)-算上算上递推系统栈的时间
    private Map<TreeNode, Integer> memo = new HashMap<>(); // <node, maxPrice>
    public int rob_dfs(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return root.val;
        return dfs(root, memo);
    }

    private int dfs(TreeNode root, Map<TreeNode, Integer> memo) {
        if (root == null) return 0;
        if (memo.containsKey(root)) return memo.get(root);

        // 1. 选root
        int choose = root.val;
        if (root.left != null) {
            choose += dfs(root.left.left, memo) + dfs(root.left.right, memo);
        }
        if (root.right != null) {
            choose += dfs(root.right.left, memo) + dfs(root.right.right, memo);
        }

        // 2. 不选root
        int notChoose = 0;
        notChoose += dfs(root.left, memo) + dfs(root.right, memo);

        int res = Math.max(choose, notChoose);
        memo.put(root, res);
        return res;
    }
}
