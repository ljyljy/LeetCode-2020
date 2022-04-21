package DP;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class q120_triangle_min_pathsum {
    List<List<Integer>> triangle;
    int m, n;
    // 法0：DP（荐）
    public int minimumTotal(List<List<Integer>> triangle) {
        this.triangle = triangle;
        m = triangle.size(); n = triangle.get(m-1).size();
        int[][] dp = new int[m+1][n+1]; // 【哨兵：维度+1】在叶子底下加一行全0行，作为哨兵；
        for (int i = m-1; i >= 0; i--) {
            List<Integer> row = triangle.get(i);
            for (int j = row.size()-1; j >= 0; j--) {
                dp[i][j] = Math.min(dp[i+1][j], dp[i+1][j+1]) + row.get(j);
            }
        }
        return dp[0][0];
    }

    // 法1：DFS+memo
    public int minimumTotal_dfs(List<List<Integer>> triangle) {
        this.triangle = triangle;
        m = triangle.size(); n = triangle.get(m-1).size();
        Map<String, Integer> memo = new HashMap<>();
        return dfs (0, 0, memo);
    }

    private int dfs(int i, int j, Map<String, Integer> memo) {
        if (i >= m || j >= triangle.get(i).size()) return 0;
        String key = i + "_" + j;
        if (memo.containsKey(key)) return memo.get(key);
        int left = dfs(i+1, j, memo);
        int right = dfs(i+1, j+1, memo);
        int minSum = Math.min(left, right) + triangle.get(i).get(j);
        memo.put(key, minSum);
        return minSum;
    }
}
