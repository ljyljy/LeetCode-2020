package DP.P1_Optimize;

public class q188_best_time_to_buy_and_sell_stock_iv {
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        // 0: �����
        // 1: ���У��棩
        // 2: �����У�ż��
        int n = prices.length;
        int[][] dp = new int[n][2 * k + 1];
        for (int j = 1; j < 2 * k; j += 2) {
            dp[0][j] = -prices[0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 2 * k; j += 2) {
                // 1. ���� 2. ǰһ������(żj)&��������-�ɱ�
                dp[i][j + 1] = Math.max(dp[i - 1][j + 1], dp[i - 1][j] - prices[i]);
                // 1. ���� 2. ǰһ������(��j-1)&��������+����
                dp[i][j+2] = Math.max(dp[i-1][j+2],  dp[i-1][j+1] + prices[i]);
            }
        }
        return dp[n-1][2*k];
    }
}
