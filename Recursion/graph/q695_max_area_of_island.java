package Recursion.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class q695_max_area_of_island {
    private int m, n;
    private int[][] grid;
//    private List<Integer> connCnts = new ArrayList<>();
    private int maxConn = 0; // 最大连通块大小
    private int[][] memo;
    private int[] _x = {0, 1, 0, -1}; // 上下左右 共4个方向
    private int[] _y = {1, 0, -1, 0};

    public int maxAreaOfIsland(int[][] grid) {
        this.grid = grid; m = grid.length; n = grid[0].length;
        memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }

        // 多源dfs
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 1) continue;
                maxConn = Math.max(maxConn, dfs(i, j, memo));
            }
        }
        return maxConn;
    }

    private int dfs(int x, int y, int[][] memo) {
        if (memo[x][y] != -1) return memo[x][y];
        int curCnt = 1;
        // ↓ 必须对graph做原地修改，visited无用！【类比q130, q695_HJ】
        grid[x][y] = -1;

        for (int dir = 0; dir < 4; dir++) {
            int newX = x + _x[dir], newY = y + _y[dir];
            if (!check(newX, newY)) continue;
            curCnt += dfs(newX, newY, memo);
        }

        memo[x][y] = curCnt;
        return curCnt;
    }

    private boolean check(int x, int y) {
        return 0 <= x && x < m && 0 <= y && y < n && grid[x][y] == 1;
    }
}
