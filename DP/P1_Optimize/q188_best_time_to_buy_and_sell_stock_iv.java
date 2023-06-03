package DP.P1_Optimize;

public class q188_best_time_to_buy_and_sell_stock_iv {
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        // 0: 误操作
        // 1: 持有（奇）
        // 2: 不持有（偶）
        int n = prices.length;
        int[][] dp = new int[n][2 * k + 1];
        for (int j = 1; j < 2 * k; j += 2) {
            dp[0][j] = -prices[0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 2 * k; j += 2) {
                // 1. 沿用 2. 前一天卖出(偶j)&今日买入-成本
                dp[i][j + 1] = Math.max(dp[i - 1][j + 1], dp[i - 1][j] - prices[i]);
                // 1. 沿用 2. 前一天买入(奇j-1)&今日卖出+利润
                dp[i][j+2] = Math.max(dp[i-1][j+2],  dp[i-1][j+1] + prices[i]);
            }
        }
        return dp[n-1][2*k];
    }
}
