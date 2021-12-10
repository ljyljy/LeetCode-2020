package Recursion.permutation_based;

import java.util.*;

public class q51_n_queens {
    private Set<Integer> cols, xyDiff, xySum; // 主对角xy_diff, 副对角xy_sum
    private int n;
    private List<List<String>> res;

    // 法2：❤回溯--传入Deque<Integer> paths（传入每行的Q_col）
    public List<List<String>> solveNQueens(int n) {
        // 设置成员变量，减少参数传递，具体作为方法参数还是作为成员变量，请参考团队开发规范
        this.n = n;
        cols = new HashSet<>();
        xyDiff = new HashSet<>();
        xySum = new HashSet<>();
        res = new ArrayList<>();

        // 二维棋盘转一维（只保存每行Q_col）
        Deque<Integer> paths = new ArrayDeque<>();
        dfs(n, 0, paths);// 深搜 -- 行row_i
        return res;
    }

    private void dfs(int n, int row, Deque<Integer> paths) {
        if (row == n) {
            res.add(convert(paths));
            return;
        }
        // 固定当前row_i, 遍历列col_j
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

    private List<String> convert(Deque<Integer> paths) {
        List<String> chessboard = new ArrayList<>();
        for (Integer colQ: paths) {
            // ❤ String -- 【".".repeat(n)】 等价py: "."*n ↓
            StringBuilder row = new StringBuilder(".".repeat(n));
            row.replace(colQ, colQ+1, "Q");
            chessboard.add(new String(row));// 共后缀n行(棋盘)
        }					// ↑StringBuilder转String
        return chessboard;
    }

    // 法1【不荐，String复杂】：传入String[] chessboard
    public List<List<String>> solveNQueens2(int n) {
        this.n = n;
        cols = new HashSet<>();
        xyDiff = new HashSet<>();
        xySum = new HashSet<>();
        res = new ArrayList<>();

        // 初始化棋盘(看做二维path)
        // 法1【荐】:
        String[] chessboard = new String[n]; // 最后
        String row = ".".repeat(n); // py: "."*n
        for (int i = 0; i < n; i++) {
            Arrays.fill(chessboard, row);// 按序填充
        }

        // 法2[replace需要StringBuilder，转换太麻烦]:
        // StringBuilder row = new StringBuilder(".".repeat(n)); // py: "."*n
        // List<StringBuilder> chessboard = Collections.nCopies(n, row); //❤❤等价py: [row]*n

        dfs(n, 0, chessboard);
        return res;
    }

    private void dfs(int n, int row, String[] chessboard) {
        if (row == n) {
            List<String> newborad = Arrays.asList(chessboard);
            res.add(new ArrayList<>(newborad)); // 不可直接传入asList(引用), 最终结果全为'....'
            return;
        }
        for (int col = 0; col < n; col++) {
            if (isValid(row, col)) {
                StringBuilder newRow = new StringBuilder();
                newRow.append(".".repeat(n)); // Math.max(0, n),共后缀n行(棋盘)
                newRow.replace(col, col+1, "Q");
                String originRow = chessboard[row];

                chessboard[row] = new String(newRow);
                cols.add(col); xyDiff.add(row-col); xySum.add(row+col);
                dfs(n, row+1, chessboard);
                cols.remove(col); xyDiff.remove(row-col); xySum.remove(row+col);
                chessboard[row] = originRow;
            }
        }
    }

    private boolean isValid(int row, int col) {
        if (cols.contains(col) || xyDiff.contains(row-col)
                || xySum.contains(row+col)) {
            return false;
        }
        return true;
    }




}
