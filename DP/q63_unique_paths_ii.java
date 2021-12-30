package DP;

import java.util.HashMap;
import java.util.Map;

public class q63_unique_paths_ii {
    // 法1: DFS + memo
    Map<String, Integer> memo = new HashMap<>();
    int m, n;
    public int uniquePathsWithObstacles_dfs(int[][] obstacleGrid) {
        m = obstacleGrid.length; n = obstacleGrid[0].length;
        return dfs(obstacleGrid, 0, 0, memo);
    }

    private int dfs(int[][] obstacleGrid, int i, int j, Map<String, Integer> memo) {
        if (i >= m || j >= n) return 0;
        String key = i + "_" + j;
        if (memo.containsKey(key)) return memo.get(key);
        if (obstacleGrid[i][j] == 1) {
            memo.put(key, 0);
            return 0;
        }
        if (i == m-1 && j == n-1 && obstacleGrid[i][j] != 1) return 1;

        int cnt_down = dfs(obstacleGrid, i+1, j, memo);
        int cnt_right = dfs(obstacleGrid, i, j+1, memo);
        int res = cnt_down + cnt_right;
        memo.put(key, res);
        return res;
    }

    // [荐] 法2: DP
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        // dp的初始化（注意障碍物后的边界全为0!）
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            // 边界遇到1(障碍),后面的dp全为0!(∵被前面的障碍堵住了)
            if (obstacleGrid[i][0] == 1) break;
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            // 边界遇到1(障碍)后面的dp全为0!
            if (obstacleGrid[0][j] == 1) break;
            dp[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] != 1) {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }
}
