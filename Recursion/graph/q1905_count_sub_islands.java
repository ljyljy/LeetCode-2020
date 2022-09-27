package Recursion.graph;

public class q1905_count_sub_islands {
    private final int[] _x = {-1, 0, 1, 0};
    private final int[] _y = {0, -1, 0, 1};
    private static final int LAND = 1, WATER = 0;
    private int m, n;
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        m = grid1.length; n = grid1[0].length;
        // 1) 先排除非子岛的部分：A=WATER && B=LAND
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 将A相应水域, 映射到B岛并'淹掉'（FloodFill）
                if (grid1[i][j] == WATER && grid2[i][j] == LAND) {
                    dfs(grid2, i, j);
                }
            }
        }
        // 2) B中剩下的岛屿都是子岛，计算岛屿数量
        int landCnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid2[i][j] == LAND) {
                    landCnt++;
                    dfs(grid2, i, j);
                }
            }
        }
        return landCnt;
    }

    // 从 (i, j) 开始，将与之相邻的陆地都变成海水
    private void dfs(int[][] grid, int i, int j) {
        if (grid[i][j] == WATER) return;
        grid[i][j] = WATER; // '淹掉'（FloodFill）

        for (int dir = 0; dir < 4; dir++) {
            int newX = i + _x[dir], newY = j + _y[dir];
            if (!isValid(grid, newX, newY)) continue;
            dfs(grid, newX, newY);
        }
    }

    private boolean isValid(int[][] grid, int x, int y) {
        return 0 <= x && x < m && 0 <= y && y < n && grid[x][y] == LAND;
    }

}
