package DP.P1_Optimize;

public class q123_best_time_to_buy_and_sell_stock_iii {
    public int maxProfit(int[] prices) {
        final int MOD = 3;
        int n = prices.length, n_states = 5;
        if (n <= 1) return 0;

        int[][] dp = new int[MOD][n_states];
        dp[0][1] = -prices[0];// ��һ������(-�ɱ�)
        dp[0][3] = -prices[0];// ��һ������+����+��������
        for (int i = 1; i < n; i++) {
            // 0: �޽��� = ���á���ֹǰһ�족���޽���(��δ������)
            dp[i % MOD][0] = dp[(i - 1) % MOD][0];
            // 1: ��һ�����룺���á���ֹǰһ�족��һ������ / ǰһ���޽���+������(-�ɱ�)
            dp[i % MOD][1] = Math.max(dp[(i - 1) % MOD][1], dp[(i - 1) % MOD][0] - prices[i]);
            // 2: ��һ������������ / ����ֹǰһ�족�ѵ�һ������+������(+����)
            dp[i % MOD][2] = Math.max(dp[(i - 1) % MOD][2], dp[(i - 1) % MOD][1] + prices[i]);
            // 3: �ڶ������룺���� / ����ֹǰһ�족һ������+�����������(-�ɱ�)
            dp[i % MOD][3] = Math.max(dp[(i - 1) % MOD][3], dp[(i - 1) % MOD][2] - prices[i]);
            // 4: �ڶ������������� / ����ֹǰһ�족�ѵڶ�������+����ڶ�����(+����)
            dp[i % MOD][4] = Math.max(dp[(i-1) % MOD][4], dp[(i-1) % MOD][3]+prices[i]);

        }
        return dp[(n-1) % MOD][4];
    }
}
