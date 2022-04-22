package DP;

public class q121_best_time_to_buy_and_sell_stock {
    // 法2：DP
    public int maxProfit(int[] prices) {
        final int MOD = 2;
        int n = prices.length;
        int[][] dp = new int[n][2]; // 0:持有 & 1:不持有
        dp[0][0] = -prices[0]; // 第0天持有第0个股票(买入)
        dp[0][1] = 0;
        for (int i = 1; i < n; i++) {
//            for(int j = 0; j < 2; j++) {
                // 第i天持有：先前就持有or今天买入(-成本/付出)
                dp[i % MOD][0] = Math.max(dp[(i-1) % MOD][0], -prices[i]);
                // 第i天不持有：先前没买or今天刚卖出(售价/获利)
                dp[i % MOD][1] = Math.max(dp[(i-1) % MOD][1], dp[(i-1) % MOD][0] + prices[i]);
//            }
        }
        return Math.max(dp[(n-1) % MOD][0], dp[(n-1) % MOD][1]);
    }

    // 获取购买日 & 卖出日
    public int maxProfit_getBuyAndSell(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2]; // 0: 持有，1: 不持有
        dp[0][0] = -prices[0];

        int buy = -1, sell = -1;

        for (int i = 1; i < n; i++) {
            if (-prices[i] > dp[i-1][0]) {
                buy = i+1; // 天数=下标i+1
            }
            dp[i][0] = Math.max(dp[i-1][0], -prices[i]);

            if (dp[i-1][1] < dp[i-1][0] + prices[i]) {
                sell = i+1;// 天数=下标i+1
            }
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] + prices[i]);
        }

        System.out.println("buy: " + buy + ", sell: " + sell); // 购买日 & 卖出日
        return Math.max(dp[n-1][0], dp[n-1][1]);
    }

    // 法1. 贪心: 求max{右max-左min}
    public int maxProfit_greedy(int[] prices) {
        int n = prices.length;
        int min = Integer.MAX_VALUE; // -inf!
        int res = 0;
        for (int i = 1; i < n; i++) {
            min = Math.min(min, prices[i-1]);
            res = Math.max(res, prices[i] - min);
        }
        return res;
    }


}
