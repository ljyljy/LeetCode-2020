package DP;

public class q714_best_time_to_buy_and_sell_stock_with_transaction_fee {
    // 0: ����(����i-1 / ���ղ�����[1]&��������-�ɱ�)
    // 1: ������(����i-1 / ���ճ���[0]&��������+����-������)
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


    // ��2��DP+�ռ�ѹ��
    public int maxProfit2(int[] prices, int fee) {
        int n = prices.length;
        int MOD = 2;
        int[][] dp = new int[MOD][2];
        dp[0][0] = -prices[0]; // ����
        for (int i = 1; i < n; i++) {
            // ����
            dp[i % MOD][0] = Math.max(dp[(i-1) % MOD][0],
                    dp[(i-1) % MOD][1] - prices[i]);
            // ������
            dp[i % MOD][1] = Math.max(dp[(i-1) % MOD][1],
                    dp[(i-1) % MOD][0] + prices[i] - fee);
        }
        return dp[(n-1) % MOD][1];
    }

}
