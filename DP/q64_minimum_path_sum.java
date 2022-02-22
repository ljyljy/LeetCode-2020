package DP;

import java.util.Arrays;

public class q64_minimum_path_sum {
    // 写法1-常规（注意初始化&grid下标！）
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) dp[i][j] = grid[0][0];
                else if (i == 0) dp[0][j] = dp[0][j-1] + grid[0][j];
                else if (j == 0) dp[i][0] = dp[i-1][0] + grid[i][j];
                else dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }

    // 写法-（错误）
    public int minPathSum_WA(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m+1][n+1];
        Arrays.fill(dp[0], Integer.MAX_VALUE);
        for (int j = 0; j <= n; j++) dp[0][j] = Integer.MAX_VALUE;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i-1][j-1];
            }
        }
        return dp[m][n];
    }
}
