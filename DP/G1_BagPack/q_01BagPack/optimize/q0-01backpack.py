class Solution:
    # 法2：优化版 - 状态压缩
    def backPack(self, m, A):
        if not A or m <= 0: return 0
        n = len(A)
        dp = [0 for _ in range(m + 1)]

        for i in range(1, n + 1):
            for j in reversed(range(1, m + 1)):
                if j - A[i - 1] >= 0:
                    dp[j] = max(dp[j], dp[j - A[i - 1]] + A[i - 1])
        return dp[-1]

    # 法1：时间 & 空间 O(n*m)
    def backPack1(self, m, A):
        if not A or m <= 0: return 0
        n = len(A)
        dp = [[0] * (m + 1) for _ in range(n + 1)]
        for i in range(1, n + 1):
            for j in range(1, m + 1):
                dp[i][j] = dp[i - 1][j]
                if j - A[i - 1] >= 0:
                    dp[i][j] = max(dp[i][j], dp[i - 1][j - A[i - 1]] + A[i - 1])
        return dp[-1][-1]
