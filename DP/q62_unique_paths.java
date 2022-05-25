package DP;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class q62_unique_paths {
    // 法2【荐】：DP[?注意初始化 & (0,0)->(m-1, n-1)]
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        Arrays.fill(dp[0], 1); // dp[0][:] = 1
        for (int j = 0; j < m; j++) dp[j][0] = 1; // dp[:][0] = 1

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    // 写法2-2
    public int uniquePaths2(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }

    // 法1：DFS+memo[注意(1,1)->(m, n)]
    private int cnt = 0;
    private Map<String, Integer> memo = new HashMap<>();
    public int uniquePaths_DFS(int m, int n) {
        return dfs(m, n, 1, 1, memo);
    }

    private int dfs (int m, int n, int i, int j,
                     Map<String, Integer> memo) {
        String key = i + "_" + j;
        if (memo.containsKey(key)) return memo.get(key);
        if (i == m && j == n) return 1;
        else if (i > m || j > n) return 0;

        int cnt_down = dfs(m, n, i+1, j, memo);
        int cnt_right = dfs(m, n, i, j+1, memo);
        int res = cnt_down + cnt_right;
        memo.put(key, res);
        return res;
    }
}
