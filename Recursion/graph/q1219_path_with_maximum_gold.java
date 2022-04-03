package Recursion.graph;

// 多源DFS
public class q1219_path_with_maximum_gold {
    private int m, n;
    private final int[] _x = {1, -1, 0, 0};
    private final int[] _y = {0,  0, 1,-1};
    private boolean[][] visited;
    private int[][] grid;
    // 不可设置【memo-WA】! 多源DFS: memo结果会动态变化，不可以用记忆化搜索！
    public int getMaximumGold(int[][] grid) {
        m = grid.length; n = grid[0].length;
        this.grid = grid;
        visited = new boolean[m][n];

        int maxAmount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0 && !visited[i][j]) {
                    visited[i][j] = true;
                    maxAmount = Math.max(maxAmount,
                            dfs(i, j));
                    visited[i][j] = false;
                }
            }
        }
        return maxAmount;
    }

    private int dfs(int i, int j) {
        int curAmount = grid[i][j];

        for (int dir = 0; dir < 4; dir++) {
            int newI = i + _x[dir], newJ = j + _y[dir];
            if (!isValid(newI, newJ) || visited[newI][newJ]) continue;
            visited[newI][newJ] = true;
            curAmount = Math.max(curAmount, grid[i][j] + dfs(newI, newJ));
            visited[newI][newJ] = false;
        }

        return curAmount;
    }

    private boolean isValid(int x, int y) {
        return 0 <= x && x < m && 0 <= y && y < n && grid[x][y] != 0;
    }
}
