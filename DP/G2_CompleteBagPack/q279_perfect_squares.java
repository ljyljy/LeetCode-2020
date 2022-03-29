package DP.G2_CompleteBagPack;

import java.util.Arrays;

public class q279_perfect_squares {
    public int numSquares(int n) {
        int bagsize = n;
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i * i <= n; i++) { // 遍历物品(完全平方数)
            for (int j = i*i; j <= bagsize; j++) {
                dp[j] = Math.min(dp[j], dp[j - i*i] + 1); // 完全背包(最值, 方案数)
            }
        }
        return dp[bagsize];
    }
}