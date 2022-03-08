package DP;

public class q714_best_time_to_buy_and_sell_stock_with_transaction_fee {
    // 0: 持有(沿用i-1 / 昨日不持有[1]&今日买入-成本)
    // 1: 不持有(沿用i-1 / 昨日持有[0]&今日卖出+利润-手续费)
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] + prices[i] - fee);
        }
        return dp[n-1][1];
    }


    // 法2：DP+空间压缩
    public int maxProfit2(int[] prices, int fee) {
        int n = prices.length;
        int MOD = 2;
        int[][] dp = new int[MOD][2];
        dp[0][0] = -prices[0]; // 持有
        for (int i = 1; i < n; i++) {
            // 持有
            dp[i % MOD][0] = Math.max(dp[(i-1) % MOD][0],
                    dp[(i-1) % MOD][1] - prices[i]);
            // 不持有
            dp[i % MOD][1] = Math.max(dp[(i-1) % MOD][1],
                    dp[(i-1) % MOD][0] + prices[i] - fee);
        }
        return dp[(n-1) % MOD][1];
    }

}
