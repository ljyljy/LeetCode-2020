package Recursion.combination_based;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class q39_combination_sum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        dfs(candidates, target, 0, path, res);
        return res;
    }

    private void dfs(int[] candidates, int target, int idx, Deque<Integer> path, List<List<Integer>> res) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = idx; i < candidates.length; i++) {
            path.addLast(candidates[i]);
            dfs(candidates, target - candidates[i], i, path, res);
            path.removeLast();
        }
    }
}
