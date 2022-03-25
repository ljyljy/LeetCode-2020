package Recursion.combination_based;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class q216_combination_sum_iii {
    // 新写法
    List<List<Integer>> res = new ArrayList<>();
    Deque<Integer> path = new ArrayDeque<>();
    int k, n;
    public List<List<Integer>> combinationSum3_v2(int k, int n) {
        if (k <= 0 || n <= 0) return res;
        this.k = k; this.n = n;
        dfs(0, 0);
        return res;
    }

    private void dfs(int idx, int sum) {
        if (path.size() == k) {
            if (sum == n) {
                res.add(new ArrayList<>(path));
                return;
            }
        }
        // 剪枝1：需选择节点数(k-path.size()) <= 可选节点数(候选数(9-i) + 本层结点(1))
        // 剪枝2： sum + i <= n
        for (int i = idx+1; i <= 9 - (k-path.size()) + 1 && sum + i <= n; i++) { // idx+1: 为了让i从1起步
            path.addLast(i);
            dfs(i, sum + i);
            path.removeLast();
        }
    }

    // old
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
