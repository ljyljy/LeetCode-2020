package DP.P2_Composition;

public class q799_champagne_tower {
    // dp模拟
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] dp = new double[105][105];
        dp[0][0] = poured;

        for (int i = 0; i <= query_row; i++) {
            for (int j = 0; j <= i; j++) {
                // 若dp[i][j]>1,则溢出量(>0)会均摊至下一行的左右
                double overFlow = (double)(dp[i][j] - 1.0) / 2.0;
                if (overFlow > 0) {
                    dp[i+1][j] += overFlow;
                    dp[i+1][j+1] += overFlow;
                }
            }
        }
        return Math.min(1, dp[query_row][query_glass]);
    }
}
