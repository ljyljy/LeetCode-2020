# 	ACW3. 完全背包问题 https://www.acwing.com/problem/content/3/
# 有 N 件物品和一个容量是 V 的背包。每件物品能使用无限次。第 i 件物品的体积是 vi，价值是 wi。求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
# 输出最大价值。
# 输入格式：
# 第一行两个整数，N，V，用空格隔开，分别表示物品数量和背包容积。
# 接下来有 N 行，每行两个整数 vi,wi，用空格隔开，分别表示第 i 件物品的体积和价值。
# 输出格式：
# 输出一个整数，表示最大价值。
# 说明： 数据范围：
# 0<N,V≤1000
# 0<vi,wi≤1000
# 例：
# 4 5
# 1 2
# 2 4
# 3 4
# 4 5
# => 10（5个物品1-容积为1，价值为2 – 总价值5*2=10，总容积=5*1）
# 相关题目：
# 思路： https://www.acwing.com/video/945/

# 总结
# •	01背包：   f[i][j] = max( f[i-1][j], f[i-1][j-v]+String.HJ_msg)
# •	完全背包：  f[i][j] = max( f[i-1][j], f[i][j-v]+String.HJ_msg)

class Solution:
    # A[i]: 物品i的体积，V[i]：物品i的价值
    # 完全背包v1 - O(n^2 * m) -- TLE
    def backPackIII_v1(self, V, P, m):
        n = len(V)
        dp = [[0] * (m + 1) for j in range(n + 1)]

        for i in range(1, n + 1):
            for j in range(0, m + 1):
                dp[i][j] = dp[i-1][j]  # 勿漏

                k = 0
                while j - k * V[i - 1] >= 0:
                    dp[i][j] = max(dp[i][j], dp[i - 1][j - k * V[i - 1]] + k * P[i - 1])
                    k += 1
        print(dp[-1][-1])

    def backPackIII(self, V, P, m):
        n = len(V)
        dp = [[0] * (m + 1) for j in range(n + 1)]

        for i in range(1, n + 1):
            for j in range(1, m + 1):
                dp[i][j] = dp[i-1][j]  # 勿漏
                if j - V[i - 1] >= 0:
                    dp[i][j] = max(dp[i-1][j], dp[i][j - V[i - 1]] + P[i - 1])
        print(dp[-1][-1])


if __name__ == "__main__":
    n, m = map(int, input().split())
    V, P = [], []  # 物品i的体积、价值
    for i in range(n):
        vp = list(map(int, input().split()))
        V.append(vp[0])
        P.append(vp[1])
        # goods_vp.append(list(map(int, input().split())))
    sol = Solution()
    # sol.backPackIII_v1(V, P, m)
    sol.backPackIII(V, P, m)