package Recursion.combination_based;

import java.util.*;

public class q40_combination_sum_ii {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        Arrays.sort(candidates); // 组合求和 - 排序 & 剪枝
        boolean[] used = new boolean[candidates.length];
        dfs(candidates, target, 0, used, path, res);
        return res;
    }

    private void dfs(int[] candidates, int target, int idx,
                     boolean[] used, Deque<Integer> path, List<List<Integer>> res) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = idx; i < candidates.length; i++) {
            if (i > 0 && candidates[i] == candidates[i-1] && !used[i-1])
                continue; // 剪枝：同一层
            used[i] = true;
            path.addLast(candidates[i]);
            dfs(candidates, target - candidates[i], i+1, used, path, res);
            used[i] = false;
            path.removeLast();
        }
    }
}
