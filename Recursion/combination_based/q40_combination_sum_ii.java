package Recursion.combination_based;

import java.util.*;

public class q40_combination_sum_ii {
    List<List<Integer>> res = new ArrayList<>();
    Deque<Integer> path = new ArrayDeque<>();

    // 法1
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates); // 组合求和 - 排序 & 剪枝
        boolean[] used = new boolean[candidates.length];
        dfs(candidates, target, 0, used);
        return res;
    }

    private void dfs(int[] candidates, int target, int idx, boolean[] used) {
        if (target < 0) return; // 否则TLE
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = idx; i < candidates.length; i++) {
            if (i > 0 && candidates[i] == candidates[i-1] && !used[i-1])
                continue; // 剪枝：同一层
            used[i] = true;
            path.addLast(candidates[i]);
            dfs(candidates, target - candidates[i], i+1, used);
            used[i] = false;
            path.removeLast();
        }
    }

    // 法2：使用set去重-同一树层 1) 定义在dfs内(只控制某节点的下一层) 2)无需对应写set.remove
    public List<List<Integer>> combinationSum2_2(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(candidates, target, 0);
        return res;
    }

    private void dfs(int[] candidates, int target, int idx) {
        if (target < 0) return;
        if (target == 0) res.add(new ArrayList<>(path));
        Set<Integer> usedSet = new HashSet<>(); // 定义在dfs内(只控制某节点的下一层)
        for(int i = idx; i < candidates.length; i++) {
            if (usedSet.contains(candidates[i]))
                continue; // 去重-同一树层
            usedSet.add(candidates[i]); // 无需对应写set.remove
            path.addLast(candidates[i]);
            dfs(candidates, target - candidates[i], i+1);
            path.removeLast();
        }
    }
}
