package DP.P1_Optimize;

public class q122_best_time_to_buy_and_sell_stock_ii {
    public int maxProfit(int[] prices) {
        final int MOD = 2;
        int n = prices.length;
        int[][] dp = new int[n][2];// 0:���� & 1:������
        dp[0][0] = -prices[0];// ��0����е�0����Ʊ(����)
        dp[0][1] = 0;
        for (int i = 1; i < n; i++) {
            // ��i����У���ǰ�ͳ���  or  ����ǰ�����С�-��������ɱ�
            dp[i % MOD][0] = Math.max(dp[(i-1) % MOD][0], dp[(i-1) % MOD][1] - prices[i]);
            // ��i�첻���У���ǰû��  or  ���������(�ۼ�/����)
            dp[i % MOD][1] = Math.max(dp[(i-1) % MOD][1], dp[(i-1) % MOD][0] + prices[i]);
        }
        return Math.max(dp[(n-1) % MOD][0], dp[(n-1) % MOD][1]);
    }
}
