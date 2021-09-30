package Divide_Conquer;

import java.util.HashMap;
import java.util.Map;

public class q437_path_sum_iii {
    // 前缀和 + 回溯dfs
    private Map<Integer, Integer> map = new HashMap<>(); // <pre_sum, cnt>
    private int cnt, targetSum;
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) return 0;
        this.targetSum = targetSum;
        map.put(0, 1); // 哨兵dummy<前缀和=0, cnt=1>
        // Deque<Integer> path = new ArrayDeque<>();
        dfs(root, root.val);
        return cnt;
    }

    private void dfs(TreeNode root, int pre_sum) {
        if (map.containsKey(pre_sum - targetSum))
            cnt += map.get(pre_sum - targetSum);

        map.put(pre_sum, map.getOrDefault(pre_sum, 0) + 1);
        if (root.left != null) dfs(root.left, pre_sum + root.left.val);
        if (root.right != null) dfs(root.right, pre_sum + root.right.val);
        map.put(pre_sum, map.getOrDefault(pre_sum, 0) - 1); // 回溯！！！
    }
}


