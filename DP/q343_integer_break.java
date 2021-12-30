package DP;

public class q343_integer_break {
    // ��1: dp
    public int integerBreak(int n) {
        int[] dp = new int[n+1]; // dp[i]:����i������ֳ˻�
        dp[2] = 1;
        // dp[i] = max(dp[i], (i-j)*j, dp[i-j]*j); j=[1,n)
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i],
                        Math.max((i-j)*j, dp[i-j]*j)); // ?
                // �����ģ�������Ƕ��һ��max(+dp[i-j]*dp[j]);
                // ��ʵ�ϣ�ֻ��i <= 3ʱ��dp[j]<j
            }
        }
        return dp[n];
    }

}
