package Recursion.permutation_based;

import java.util.*;

public class q52_n_queens_ii {
    private int n;
    private Set<Integer> col, xy_dim, xy_sum;
    private List<List<Integer>> res;
    public int totalNQueens(int n) {
        this.n = n;
        col = new HashSet<>();
        xy_dim = new HashSet<>();
        xy_sum = new HashSet<>();
        res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        dfs(0, path);
        return res.size();
    }

    private void dfs(int row_i, Deque<Integer> path) {
        if (row_i == n) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int j = 0; j < n; j++) {
            if (col.contains(j) || xy_sum.contains(row_i + j) || xy_dim.contains(row_i - j))
                continue;
            col.add(j); xy_sum.add(row_i + j); xy_dim.add(row_i - j);
            path.addLast(j);
            dfs(row_i + 1, path);
            col.remove(j); xy_sum.remove(row_i + j); xy_dim.remove(row_i - j);
            path.removeLast();
        }
    }
}
