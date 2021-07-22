package DP;

public class q121_best_time_to_buy_and_sell_stock {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[] dp = new int[n+1]; // dp[0]=第0天利润=0
        int min_price_earlier = Integer.MAX_VALUE; // -inf!
        for (int i = 1; i <= n; i++) {
            min_price_earlier = Math.min(min_price_earlier, prices[i-1]);
            dp[i] = Math.max(dp[i-1], prices[i-1] - min_price_earlier);
        }
        return dp[n];
    }
}
