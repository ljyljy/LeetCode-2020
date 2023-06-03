package DP.P1_Optimize;

public class q123_best_time_to_buy_and_sell_stock_iii {
    public int maxProfit(int[] prices) {
        final int MOD = 3;
        int n = prices.length, n_states = 5;
        if (n <= 1) return 0;

        int[][] dp = new int[MOD][n_states];
        dp[0][1] = -prices[0];// 第一天买入(-成本)
        dp[0][3] = -prices[0];// 第一天买入+卖出+二次买入
        for (int i = 1; i < n; i++) {
            // 0: 无交易 = 沿用“截止前一天”都无交易(从未买卖过)
            dp[i % MOD][0] = dp[(i - 1) % MOD][0];
            // 1: 第一次买入：沿用“截止前一天”第一次买入 / 前一天无交易+今天买(-成本)
            dp[i % MOD][1] = Math.max(dp[(i - 1) % MOD][1], dp[(i - 1) % MOD][0] - prices[i]);
            // 2: 第一次卖出：沿用 / “截止前一天”已第一次买入+今天卖(+利润)
            dp[i % MOD][2] = Math.max(dp[(i - 1) % MOD][2], dp[(i - 1) % MOD][1] + prices[i]);
            // 3: 第二次买入：沿用 / “截止前一天”一次卖出+今天二次买入(-成本)
            dp[i % MOD][3] = Math.max(dp[(i - 1) % MOD][3], dp[(i - 1) % MOD][2] - prices[i]);
            // 4: 第二次卖出：沿用 / “截止前一天”已第二次买入+今天第二次卖(+利润)
            dp[i % MOD][4] = Math.max(dp[(i-1) % MOD][4], dp[(i-1) % MOD][3]+prices[i]);

        }
        return dp[(n-1) % MOD][4];
    }
}
