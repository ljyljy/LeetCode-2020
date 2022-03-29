package DP.G1_01BagPack.optimize;

/**
 * 有 n 个物品和一个大小为 m 的背包. 给定数组 A 表示每个物品的大小和数组 V 表示每个物品的价值。
 * 问最多能装入背包的总价值是多大?
 */
public class q9_125_01backpack_ii {
    public int maxValue(int bagsize, int[] weight, int[] value) {
        int n = weight.length;
        int[] dp = new int[bagsize + 1];
        for (int i = 0; i < n; i++) {
            for (int j = bagsize; j >= weight[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
        return dp[bagsize];
    }
}


/**
 * class Solution:
 *     def backPackII_0(self, m, A, V):
 *         if m <= 0 or not A or not V: return 0
 *         n = len(A)
 *         dp = [[0] * (m+1) for _ in range(n+1)]
 *         for i in range(1, n+1):
 *             for j in range(1, m+1):
 *                 dp[i][j] = dp[i-1][j]
 *                 if j - A[i-1] >= 0:
 *                     dp[i][j] = max(dp[i][j],
 *                                 dp[i-1][j-A[i-1]] + V[i-1])
 *         return dp[-1][-1]
 *
 *     # 法2：状态压缩
 *     def backPackII(self, m, A, V):
 *         if m <= 0 or not A or not V: return 0
 *         n = len(A)
 *         dp = [0] * (m+1)
 *         for i in range(1, n+1):
 *             for j in reversed(range(1, m+1)):
 *                 if j - A[i-1] >= 0:
 *                     dp[j] = max(dp[j],
 *                                 dp[j-A[i-1]] + V[i-1])
 *         return dp[-1]
 */