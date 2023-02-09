# 	115. 不同的子序列
# 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
# 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
# 题目数据保证答案符合 32 位带符号整数范围。
# 示例 1：
# 输入：s = "rabbbit", t = "rabbit"
# 输出：3
# 解释：
# 如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
# (上箭头符号 ^ 表示选取的字母)
# rabbbit
# ^^^^ ^^
# rabbbit
# ^^ ^^^^
# rabbbit
# ^^^ ^^^
# 示例 2：
# 输入：s = "babgbag", t = "bag"
# 输出：5
# 解释：
# 如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。
# (上箭头符号 ^ 表示选取的字母)
# babgbag
# ^^ ^
# babgbag
# ^^    ^
# babgbag
# ^    ^^
# babgbag
#   ^  ^^
# babgbag
#     ^^^
# 挑战：
# 挑战时间复杂度 O(n^2 ), 空间复杂度 O(n) 的算法.
# 如果你不知道如何优化空间, O(n^2 ) 的空间复杂度也是可以通过的.
# 相关题目:  29. 交叉字符串
# 动态规划
# dp[i][j] 代表 T 前 i 字符串可以由 S的 j 字符串组成最多个数.
# 所以动态方程:
# 当 S[j] == T[i] , dp[i][j] = dp[i-1][j-1] + dp[i][j-1];
# 当 S[j] != T[i] , dp[i][j] = dp[i][j-1]
# 举个例子,如示例的
#
# 对于第一行, T 为空,因为空集是所有字符串子集, 所以我们第一行都是 1
# 对于第一列, S 为空,这样组成 T 个数当然为 0` 了

class Solution:
    def numDistinct0(self, S1, T0):
        n1, n0 = len(S1), len(T0)
        # if n1 <= 0 or n0 <= 0: return 1  # 如: 例3, T = ""  解释: 只有删除 S 中的所有字符这一种方式得到 T

        # dp[i][j] 代表 T0(子/短/0) 前 i 字符串可以由 S1(父/长/1) j 字符串组成最多个数.
        dp = [[0 for j in range(n1+1)] for i in range(n0+1)]

        for j in range(n1):
            dp[0][j] = 1
        for i in range(1, n0 + 1):
            for j in range(1, n1 + 1):
                if T0[i-1] == S1[j-1]:  # 左上 + 左
                    dp[i][j] = dp[i-1][j-1] + dp[i][j-1]
                else:  # 子序列T0[i]
                    dp[i][j] = dp[i][j-1]  # 左

        return dp[-1][-1]

    def numDistinct0_1(self, S1, T0):
        n1, n0 = len(S1), len(T0)
        # if n1 <= 0 or n0 <= 0: return 1  # 如: 例3, T = ""  解释: 只有删除 S 中的所有字符这一种方式得到 T

        # dp[i][j] 代表 T0(子/短/0) 前 j 字符串可以由 S1(父/长/1) i 字符串组成最多个数.
        dp = [[0 for i in range(n0 + 1)] for j in range(n1 + 1)]

        for i in range(n1):
            dp[i][0] = 1
        for i in range(1, n1 + 1):
            for j in range(1, n0 + 1):
                if T0[j - 1] == S1[i - 1]:  # 左上 + 左
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j]
                else:  # 子序列T0[i]
                    dp[i][j] = dp[i - 1][j]  # 左

        return dp[-1][-1]

    # 法2：状态压缩
    def numDistinct(self, S1, T0):
        n1, n0 = len(S1), len(T0)
        dp = [0 for j in range(n0 + 1)]

        dp[0] = 1  # 初始化
        for i in range(1, n1 + 1):
            for j in range(n0, 0, -1):
                if S1[i - 1] == T0[j - 1]:
                    dp[j] += dp[j - 1]
        return dp[n0]

