package DP.SequenceDP;

public class q813_largest_sum_of_averages {
    // ����dp + ǰ׺��
    // д��1 & 2
    // https://leetcode.cn/problems/largest-sum-of-averages/solution/javac-dong-tai-gui-hua-qian-zhui-he-you-9xde1/
    public double largestSumOfAverages(int[] nums, int k) {
        int n = nums.length;
        double[] sum = new double[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        // dp[i][j]: ���� [0, i-1] ���зֳ� j ������������ƽ��ֵ��
        double[][] dp = new double[n + 1][k + 1];
        // 1) dp[i][1]��ʼ���������飨����=1������dpֵΪǰ׺ƽ��ֵ
        for (int i = 1; i <= n; i++) { // dp[i]��ǰ׺��sum[i]����
            dp[i][1] = sum[i] / i; // ǰ׺ƽ��ֵ
        }
        // 2) ����>1ʱ������[0,i-1]������i>=2 �� j��[2,k]
        for (int i = 2; i <= n; i++) { // v1�����α���i,j
            for (int j = 2; j <= k; j++) {
//        for (int j = 2; j <= k; j++) { // v2�����α���j,i
//            for (int i = j; i <= n; i++) {
                // ����[0,i-1]��i����, 1 <= j������) <= i
                for (int x = j - 1; x < i; x++) {
                    dp[i][j] = Math.max(dp[i][j],
                            dp[x][j - 1] + (sum[i] - sum[x]) / (i - x));
                    // �� ����=[j-1]+[1]������[0,x-1], j-1�� & [x,i-1], 1��
                    //                      �� ��x����, �� x >= ����j-1
                    // [i,j].preSum = sum[j+1]-sum[i]
                }
            }
        }
        return dp[n][k];
    }
}
