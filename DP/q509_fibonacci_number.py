# 斐波那契数，通常用 F(n) 表示，形成的序列称为斐波那契数列。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
#
#  F(0) = 0,   F(1) = 1
# F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
#
#
#  给定 N，计算 F(N)。
#
#
#
#  示例 1：
#
#  输入：2
# 输出：1
# 解释：F(2) = F(1) + F(0) = 1 + 0 = 1.
#
#
#  示例 2：
#
#  输入：3
# 输出：2
# 解释：F(3) = F(2) + F(1) = 1 + 1 = 2.
#
#
#  示例 3：
#
#  输入：4
# 输出：3
# 解释：F(4) = F(3) + F(2) = 2 + 1 = 3.
#
#
#
#
#  提示：
#
#
#  0 ≤ N ≤ 30
#
#  Related Topics 数组
#  👍 142 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    # 法3：动归 + 迭代 + dp_table->「状态压缩」【自底向上】
    def fib(self, N: int) -> int:
        if N <= 0: return 0
        if N == 1 or N == 2: return 1
        pre, cur = 1, 1  # base case
        for i in range(3, N + 1):
            sum = pre + cur
            pre = cur
            cur = sum
        return cur

    # 法2：动归 + 迭代 + dp_table【自底向上】
    def fib2(self, N: int) -> int:
        dp = [0] * (N + 1)
        dp[1], dp[2] = 1, 1  # base case
        for i in range(3, N + 1):
            dp[i] = dp[i - 1] + dp[i - 2]
        return dp[N]

    # 法1：备忘录memo + dfs —— 子问题结点数O(n)*O(1) = O(n) 「自顶向下」
    def fib1(self, N: int) -> int:
        if N <= 0: return 0
        memo = [0] * (N + 1)

        def helper(memo, n):
            # base case
            if n == 1 or n == 2: return 1
            # 已经计算过
            if memo[n] != 0: return memo[n]
            memo[n] = helper(memo, n - 1) + helper(memo, n - 2)
            return memo[n]

        return helper(memo, N)

    # 法0：暴力 —— 节点总数O(2^n)*单结点时间O(1) = O(2^n) 「自顶向下」
    def fib0(self, N: int) -> int:
        if N == 1 or N == 2: return 1
        return self.fib(N - 1) + self.fib(N - 2)

# leetcode submit region end(Prohibit modification and deletion)
