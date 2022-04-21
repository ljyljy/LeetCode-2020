package Recursion.graph;

public class q695_maxAreaOfIsland {
    private int[] _x = {0, 0, 1, -1};
    private int[] _y = {1, -1, 0, 0};
    private int m, n;
    public int maxAreaOfIsland(int[][] grid) {
        int ans = 0, m = grid.length, n = grid[0].length;

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                // 最值类，用memo会WA! 类比q1219,695
                ans = Math.max(ans, dfs(grid, i, j));
            }
        }
        return ans;
    }

    public int dfs(int[][] grid, int x, int y) {
        if (x < 0 || y < 0 || x >= m || y >= n || grid[x][y] != 1) {
            return 0;
        }
        grid[x][y] = 0; // visited // 把当前坐标标记为0防止重复录入

        int ans = 1;
        for (int dir = 0; dir < 4; ++dir) {
            int next_i = x + _x[dir], next_j = y + _y[dir];
            ans += dfs(grid, next_i, next_j);
        }
        return ans;
    }

}
