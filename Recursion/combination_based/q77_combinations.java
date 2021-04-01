package Recursion.combination_based;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class q77_combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        dfs(n, k, 1, path, res);
        return res;
    }

    private void dfs(int n, int k, int idx, Deque<Integer> path, List<List<Integer>> res) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 剪枝思路：(1)在for中，而非循环体内——因为path.size()会因递归&回溯动态变化
        // (2) 应保证：还需选的节点数 <= 可选择的数目（候选结点数 + 本层结点(1)）
        //     i.e.,  k - path.size() <=  (n-i) + 1, 化简为 i <= n - (k-path.size()) + 1
        for (int i = idx; i <= n - (k - path.size()) + 1; i++) {
            // for：横向遍历，i∈[startIdx, n - (k - path.size()) + 1]
            path.addLast(i);
            dfs(n, k, i+1, path, res); // 递归：纵向遍历
            path.removeLast();
        }
    }
}
