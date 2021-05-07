package DP;

import java.util.Arrays;

public class q265_paint_house_ii {
    private int n, m; // 类成员变量， 减少传参
    // 法1：dp
    public int minCostII_1(int[][] costs) {
        n = costs.length; m = costs[0].length;
        if (n == 0) return 0;
        // 定义&初始化dp
        int[][] dp = new int[n][m];
        dp[0] = costs[0].clone();
        for(int i = 1; i < n; i++) // 每一行整体赋值为INF
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        for (int i = 1; i < n; i++) { // 房号i
            for (int j = 0; j < m; j++) { // 颜色j
                for (int k = 0; k < m; k++) { // 前一个相邻颜色k
                    if (j == k) continue;
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][k] + costs[i][j]);
                }
            }
        }
        return Arrays.stream(dp[n-1]).min().getAsInt();
    }

    // 法2-2：dp + 滚动数组优化
    public int minCostII(int[][] costs) {
        n = costs.length; m = costs[0].length;
        if (n == 0) return 0;
        // 定义&初始化dp
        int mod = 2;
        int[][] dp = new int[mod][m];
        dp[0] = costs[0].clone();
        for(int i = 1; i < n; i++) {
            Arrays.fill(dp[i % mod], Integer.MAX_VALUE); // 初始化: 整体一行全部赋值INF
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < m; k++) {
                    if (j == k) continue;
                    dp[i % mod][j] = Math.min(dp[i % mod][j], dp[(i-1) % mod][k] + costs[i][j]);
                }
            }
        }
        return Arrays.stream(dp[(n-1) % mod]).min().getAsInt();
    }
}
