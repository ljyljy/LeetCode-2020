package DP;

import java.util.HashMap;
import java.util.Map;

public class q63_unique_paths_ii {
    // ��1: DFS + memo
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

    // [��] ��2: DP
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        // dp�ĳ�ʼ����ע���ϰ����ı߽�ȫΪ0!��
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            // �߽�����1(�ϰ�),�����dpȫΪ0!(�߱�ǰ����ϰ���ס��)
            if (obstacleGrid[i][0] == 1) break;
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            // �߽�����1(�ϰ�)�����dpȫΪ0!
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
