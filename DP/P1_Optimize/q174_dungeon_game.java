package DP.P1_Optimize;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class q174_dungeon_game {
    // 法3: DP
    public int calculateMinimumHP_dp(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        int[][] dp = new int[m+1][n+1]; // [i,j]的最小耗血量(>=0, 魔法加血则置0)
        for (int i = 0; i <= m; i++) // ❤勿忘等号 <=m!
            Arrays.fill(dp[i], Integer.MAX_VALUE); // ❤ 网格外围一圈(哨兵)-INT_MAX
        dp[m][n-1] = dp[m-1][n] = 0; // 右下角的→ & ↓
        for (int i = m-1; i >= 0; i--) {
            for (int j = n-1; j >= 0; j--) {
                dp[i][j] = Math.max(0,
                        Math.min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j]);
            }
        }
        return dp[0][0] + 1;
    }

    // 法2-2: DFS + MEMO<数组优化hash>
    private int n_row, n_col;
    private int[][] mat, memo;
    public int calculateMinimumHP(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0)
            return 0;
        this.n_row = mat.length;
        this.n_col = mat[0].length;
        this.mat = mat;
        this.memo = new int[n_row][n_col];
        for (int i = 0; i < n_row; i++)
            Arrays.fill(memo[i], -1);
        return minHealth_memo2(0, 0) + 1; // (正整数)初始健康数 = 最低耗血量 + 1
    }

    private int minHealth_memo2(int x, int y) {
        // 递归出口
        if (x >= n_row || y >= n_col)
            return Integer.MAX_VALUE;
        if (memo[x][y] != -1)
            return memo[x][y];
        if (x == n_row-1 && y == n_col-1) { // dp初始化❤
            int endVal = mat[x][y];
            // endVal>0: 终点加血 - 说明前一步最低耗血0.
            int res = endVal >= 0? 0: -endVal;
            memo[x][y] = res;
            return res;
        }
        // 下探
        int minRight = minHealth_memo2(x, y+1); // 右侧最低的耗血量
        int minDown = minHealth_memo2(x+1, y);  // 下侧最低的耗血量
        int minNeed = Math.min(minRight, minDown) - mat[x][y];
        int res = minNeed <= 0? 0: minNeed;
        memo[x][y] = res;
        return res;
    }

    // 法2-1: DFS + MEMO<Hash - TLE>
    public int calculateMinimumHP_Memo1_TLE(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0)
            return 0;
        int n_row = mat.length, n_col = mat[0].length;
        Map<String, Integer> memo = new HashMap<>();
        return minHealth_memo(mat, 0, 0, n_row, n_col, memo) + 1; // (正整数)初始健康数 = 最低耗血量 + 1
    }

    private int minHealth_memo(int[][] mat, int x, int y, int n_row, int n_col,
                               Map<String, Integer> memo) {
        // 递归出口
        String key = x + "_" + y;
        if (memo.containsKey(key)) return memo.get(key);
        if (x >= n_row || y >= n_col) {
            memo.put(key, Integer.MAX_VALUE);
            return Integer.MAX_VALUE;
        }
        if (x == n_row && y == n_col) {
            int endVal = mat[x][y];
            // endVal>0: 终点加血 - 说明前一步最低耗血0.
            int res = endVal >= 0? 0: -endVal;
            memo.put(key, res);
            return res;
        }
        // 下探
        int minRight = minHealth_dfs(mat, x, y+1, n_row, n_col); // 右侧最低的耗血量
        int minDown = minHealth_dfs(mat, x+1, y, n_row, n_col);  // 下侧最低的耗血量
        int minNeed = Math.min(minRight, minDown) - mat[x][y];
        int res = minNeed <= 0? 0: minNeed;
        memo.put(key, res);
        return res;
    }

    // 法1: 普通DFS(TLE)
    public int calculateMinimumHP_TLE(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0)
            return 0;
        int n_row = mat.length, n_col = mat[0].length;
        return minHealth_dfs(mat, 0, 0, n_row, n_col) + 1; // (正整数)初始健康数 = 最低耗血量 + 1
    }

    private int minHealth_dfs(int[][] mat, int x, int y, int n_row, int n_col) {
        if (x >= n_row || y >= n_col) return Integer.MAX_VALUE;
        if (x == n_row-1 && y == n_col-1) {
            int endVal = mat[n_row-1][n_col-1];
            if (endVal >= 0) return 0; // 终点加血 - 说明前一步最低耗血至0.
            else return -endVal; //
        }
        int minRight = minHealth_dfs(mat, x, y+1, n_row, n_col); // 右侧最低的耗血量
        int minDown = minHealth_dfs(mat, x+1, y, n_row, n_col);  // 下侧最低的耗血量
        int minNeed = Math.min(minRight, minDown) - mat[x][y];
        return minNeed < 0? 0: minNeed;
    }

}
