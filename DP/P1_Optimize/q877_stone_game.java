package DP.P1_Optimize;

import java.util.HashMap;
import java.util.Map;

public class q877_stone_game {
    // 法1：DFS
    // ↓ <key="i_j", val=剩余石子>
    private Map<String, Integer> memo = new HashMap<>();
    private int[] piles; // 减少传参
    public boolean stoneGame_dfs(int[] piles) {
        this.piles = piles;
        return dfs(0, piles.length-1) > 0;
    }

    private int dfs(int i, int j) {
        if (i == j) return piles[i];
        String key = i + "_" + j;
        if (memo.containsKey(key)) return memo.get(key);
        int pickL = piles[i] - dfs(i+1, j); // 要让下一轮对方拿的更小（比自己的差值更大）
        int pickR = piles[j] - dfs(i, j-1); // 若捡右边，则需与右-1的石子差值更大(右5-4<左5-3,捡左5)
        int res = Math.max(pickL, pickR);
        memo.put(key, res);
        return res;
    }

    // 法2：动归
    public boolean stoneGame_dp1(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n][n];
        // i,j相等时，只剩这堆石子，必捡起
        //     (而另一个人该轮捡不到，差值为piles[i]-0) ↓
        for (int i = 0; i < n; i++) dp[i][i] = piles[i];
        // j >= i
        for (int i = n-2; i >= 0; i--) { // i+1不可越界
            for (int j = i+1; j < n; j++) { // [j-1]不可越界(>=i)
                // <i,j>需要<i+1, j-1>, ∴i倒序，j正序
                dp[i][j] = Math.max(piles[i] - dp[i+1][j],
                        piles[j] - dp[i][j-1]);
            }
        }
        return dp[0][n-1] > 0;
    }

    // 法2-2：动归（空间优化）- 只用到dp[i] & dp[i+1]，∴可省
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int[] dp = new int[n];
        // i,j相等时，只剩这堆石子，必捡起
        //     (而另一个人该轮捡不到，差值为piles[i]-0) ↓
        for (int i = 0; i < n; i++) dp[i] = piles[i];
        // j >= i
        for (int i = n-2; i >= 0; i--) { // i+1不可越界
            for (int j = i+1; j < n; j++) { // [j-1]不可越界(>=i)
                // <i,j>需要<i+1, j-1>, ∴i倒序，j正序
                dp[j] = Math.max(piles[i] - dp[j],
                        piles[j] - dp[j-1]);
            }
        }
        return dp[n-1] > 0;
    }
}
