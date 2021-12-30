package DP;

public class q343_integer_break {
    // 法1: dp
    public int integerBreak(int n) {
        int[] dp = new int[n+1]; // dp[i]:整数i的最大拆分乘积
        dp[2] = 1;
        // dp[i] = max(dp[i], (i-j)*j, dp[i-j]*j); j=[1,n)
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i],
                        Math.max((i-j)*j, dp[i-j]*j)); // ?
                // 不放心，可以再嵌套一个max(+dp[i-j]*dp[j]);
                // 事实上，只有i <= 3时，dp[j]<j
            }
        }
        return dp[n];
    }

}
