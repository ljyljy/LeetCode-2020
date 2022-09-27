package Recursion.graph;

// 类比q130, 200, 1254
public class q1254_number_of_closed_islands {
    private int m, n;
    private static final int LAND = 0, WATER = 1;
    private final int[] _x = {-1, 0, 1, 0};
    private final int[] _y = {0, -1, 0, 1};
    public int closedIsland(int[][] grid) {
        m = grid.length; n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 将边界岛屿（非封闭）进行屏蔽，后续不考虑
                if (isBounded(i, j)) {
                    dfs(grid, i, j);
                }
            }
        }
        // 遍历 grid，剩下的岛屿都是封闭岛屿
        int landCnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 将边界岛屿（非封闭）进行屏蔽，后续不考虑
                if (grid[i][j] == LAND) {
                    landCnt++;
                    dfs(grid, i, j);
                }
            }
        }
        return landCnt;
    }

    // 法1：覆盖LAND->WATER, 从 (i, j) 开始，将与之相邻的陆地都变成海水
    //   法2：设置visited[i][j]=true, 不可在最后回溯重改回false！
    private void dfs(int[][] grid, int i, int j) {
        if (grid[i][j] == WATER) { // visited或本身就是海水
            return;
        }
        grid[i][j] = WATER;
        for (int dir = 0; dir < 4; dir++) {
            int newX = i + _x[dir], newY = j + _y[dir];
            if (!isValid(grid, newX, newY)) continue;
            dfs(grid, newX, newY);
        }
    }

    private boolean isValid(int[][] grid, int x, int y) {
        return 0 <= x && x < m && 0 <= y && y < n && grid[x][y] == LAND;
    }

    private boolean isBounded(int x, int y) {
        return x == 0 || x == m-1 || y == 0 || y == n-1;
    }
}
