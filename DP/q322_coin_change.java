package DP;

import java.util.Arrays;

public class q322_coin_change {
    // 完全背包(内循环正序), 组合(外循环-物品)/排列, 方案数的【最值】问题
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int bagsize = amount;
        int[] dp = new int[bagsize + 1];
        // dp初始化
        Arrays.fill(dp, Integer.MAX_VALUE); // ∵min
        dp[0] = 0; // 总金额=0，硬币个数必为0
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                // 防止溢出?
                if (dp[j - coins[i]] != Integer.MAX_VALUE) {
                    // 不考虑--旧值dp[j];   考虑--硬币数+1
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }
        return dp[bagsize] == Integer.MAX_VALUE? -1: dp[bagsize];
    }
}
