package DP;

public class q309_best_time_to_buy_and_sell_stock_with_cooldown {
    // DP״̬:
    // 0: ���У�����i-1  /  ����[�䶳��||������v1(���䶳)]&��������-�ɱ���
    // 1: ������v1 (����-��ֹ����������һ�������շ��䶳/�����䶳�ڣ��������޲���(����)-���ֲ�����)
    // 2: ������v2-���� (�����[0]&��������)+���������䶳��
    // 3: �䶳��(��������[2])
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
