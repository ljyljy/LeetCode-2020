package DP.P1_Optimize;

public class q122_best_time_to_buy_and_sell_stock_ii {
    public int maxProfit(int[] prices) {
        final int MOD = 2;
        int n = prices.length;
        int[][] dp = new int[n][2];// 0:持有 & 1:不持有
        dp[0][0] = -prices[0];// 第0天持有第0个股票(买入)
        dp[0][1] = 0;
        for (int i = 1; i < n; i++) {
            // 第i天持有：先前就持有  or  “先前不持有”-今天买入成本
            dp[i % MOD][0] = Math.max(dp[(i-1) % MOD][0], dp[(i-1) % MOD][1] - prices[i]);
            // 第i天不持有：先前没买  or  今天刚卖出(售价/获利)
            dp[i % MOD][1] = Math.max(dp[(i-1) % MOD][1], dp[(i-1) % MOD][0] + prices[i]);
        }
        return Math.max(dp[(n-1) % MOD][0], dp[(n-1) % MOD][1]);
    }
}
