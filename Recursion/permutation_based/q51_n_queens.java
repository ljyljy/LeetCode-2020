package Recursion.permutation_based;

import java.util.*;

public class q51_n_queens {

    private Set<Integer> col, xy_diff, xy_sum; // 主对角xy_diff, 副对角xy_sum
    private int n;
    private List<List<String>> res;

    public List<List<String>> solveNQueens(int n) {
        // 设置成员变量，减少参数传递，具体作为方法参数还是作为成员变量，请参考团队开发规范
        this.n = n; // n皇后
        res = new ArrayList<>();
        col = new HashSet<>();
        xy_diff = new HashSet<>();
        xy_sum = new HashSet<>();

        Deque<Integer> path = new ArrayDeque<>();
        dfs(0, path); // 深搜行row_i
        return res;
    }


    private void dfs(int row_i, Deque<Integer> path) {
        if (row_i == n) {
            List<String> res_i = convert(path);
            res.add(res_i);
            return;
        }
        // 固定当前row_i, 遍历列col_j
        for (int j = 0; j < n; j++) {
            if (col.contains(j) || xy_diff.contains(row_i - j) || xy_sum.contains(row_i + j))
                continue;

            col.add(j); xy_diff.add(row_i - j); xy_sum.add(row_i + j);
            path.addLast(j);
            dfs(row_i + 1, path);
            col.remove(j); xy_diff.remove(row_i - j); xy_sum.remove(row_i + j);
            path.removeLast();
        }

    }

    private List<String> convert(Deque<Integer> path) {
        List<String> res_i = new ArrayList<>();
        for (Integer col_j : path) {
            StringBuilder row = new StringBuilder();
            row.append(".".repeat(Math.max(0, n))); // 共后缀n行(棋盘)
            row.replace(col_j, col_j+1, "q1269_cnts_2stay_same_place");
            res_i.add(row.toString());
        }
        return res_i;
    }


}
