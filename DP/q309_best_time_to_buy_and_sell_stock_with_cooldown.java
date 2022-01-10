package DP;

public class q309_best_time_to_buy_and_sell_stock_with_cooldown {
    // DP状态:
    // 0: 持有（沿用i-1  /  昨日[冷冻期||不持有v1(非冷冻)]&今天买入-成本）
    // 1: 不持有v1 (沿用-截止昨天卖出过一次且昨日非冷冻/昨日冷冻期，但今天无操作(不买)-保持不持有)
    // 2: 不持有v2-卖出 (昨持有[0]&今天卖出)+利润【明天冷冻】
    // 3: 冷冻期(昨天卖出[2])
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][4];
        dp[0][0] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i-1][0],
                    Math.max(dp[i-1][3], dp[i-1][1])-prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][3]);
            dp[i][2] = dp[i-1][0] + prices[i];
            dp[i][3] = dp[i-1][2];
        }
        return Math.max(dp[n-1][3], Math.max(dp[n-1][1], dp[n-1][2]));
    }
}
