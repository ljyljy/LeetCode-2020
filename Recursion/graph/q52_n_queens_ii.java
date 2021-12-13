package Recursion.graph;

import java.util.*;

public class q52_n_queens_ii {
    int n, cntQ = 0;
    Set<Integer> cols, xyDiff, xySum;
    public int totalNQueens(int n) {
        this.n = n;
        cols = new HashSet<>();
        xyDiff = new HashSet<>();
        xySum = new HashSet<>();

        Deque<Integer> paths = new ArrayDeque<>();
        dfs(n, 0, paths);
        return cntQ;
    }

    private void dfs(int n, int row, Deque<Integer> paths) {
        if (row == n) {
            // res.add(convert(paths));
            cntQ++;
            return;
        }
        for (int col = 0; col < n; col++) {
            if (!check(row, col)) continue;
            cols.add(col); xyDiff.add(row-col); xySum.add(row+col);
            paths.addLast(col);
            dfs(n, row+1, paths);
            paths.removeLast();
            cols.remove(col); xyDiff.remove(row-col); xySum.remove(row+col);
        }
    }

    private boolean check(int row, int col) {
        return !(cols.contains(col) || xyDiff.contains(row-col)
                || xySum.contains(row+col));
    }

//    private int n;
//    private Set<Integer> col, xy_dim, xy_sum;
//    private List<List<Integer>> res;
//    public int totalNQueens(int n) {
//        this.n = n;
//        col = new HashSet<>();
//        xy_dim = new HashSet<>();
//        xy_sum = new HashSet<>();
//        res = new ArrayList<>();
//        Deque<Integer> path = new ArrayDeque<>();
//        dfs(0, path);
//        return res.size();
//    }
//
//    private void dfs(int row_i, Deque<Integer> path) {
//        if (row_i == n) {
//            res.add(new ArrayList<>(path));
//            return;
//        }
//        for (int j = 0; j < n; j++) {
//            if (col.contains(j) || xy_sum.contains(row_i + j) || xy_dim.contains(row_i - j))
//                continue;
//            col.add(j); xy_sum.add(row_i + j); xy_dim.add(row_i - j);
//            path.addLast(j);
//            dfs(row_i + 1, path);
//            col.remove(j); xy_sum.remove(row_i + j); xy_dim.remove(row_i - j);
//            path.removeLast();
//        }
//    }
}
