package Recursion.combination_based;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class q216_combination_sum_iii {
    public List<List<Integer>> combinationSum3(int k, int n_sum) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        dfs(k, n_sum, 1, path, res);
        return res;
    }
    private void dfs(int k, int n_sum, int idx, Deque<Integer> path, List<List<Integer>> res) {
        if (n_sum < 0 || path.size() > k) return; // 剪枝1
        if (n_sum == 0 && path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 剪枝：需选择节点数(k-path.size()) <= 可选节点数(候选数(9-i) + 本层结点(1))
        for (int i = idx; i <= 9 - (k - path.size()) + 1; i++) {
            path.addLast(i);
            dfs(k, n_sum - i,i+1,  path, res);
            path.removeLast();
        }
    }
}
