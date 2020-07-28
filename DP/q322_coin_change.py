# 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回
#  -1。
#
#
#
#  示例 1:
#
#  输入: coins = [1, 2, 5], amount = 11
# 输出: 3
# 解释: 11 = 5 + 5 + 1
#
#  示例 2:
#
#  输入: coins = [2], amount = 3
# 输出: -1
#
#
#
#  说明:
# 你可以认为每种硬币的数量是无限的。
#  Related Topics 动态规划
#  👍 715 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class Solution:
    # 法2：dp_arr 迭代【自底向上】 -- dp 数组的定义：当目标金额为 i 时，至少需要 dp[i] 枚硬币凑出。
    def coinChange(self, coins: List[int], amount: int) -> int:
        # 数组大小为 amount + 1，初始值也为 amount + 1 (即一个不可能为答案的数)
        dp = [amount + 1] * (amount + 1)
        dp[0] = 0  # base case
        for i in range(0, len(dp)):
            for coin in coins:
                if i - coin < 0: continue  # 子问题无解，跳过
                dp[i] = min(dp[i], 1 + dp[i - coin])
        return dp[-1] if dp[-1] != amount + 1 else -1

    # 法1：备忘录 + dfs 【自顶向下】  ——> 总的时间复杂度是 O(kn)
    # 「备忘录」子问题的冗余，即子问题数目为 O(n)。每个子问题中含有一个 for 循环，复杂度仍是 O(k)。
    def coinChange1(self, coins: List[int], amount: int) -> int:
        memo = dict()

        def dp(amount):
            # base case
            if amount == 0: return 0
            if amount < 0: return -1
            if amount in memo.keys(): return memo[amount]
            # 求最小值，所以初始化为正无穷
            res = float('INF')
            for coin in coins:
                subcase = dp(amount - coin)
                if subcase == -1: continue
                res = min(res, 1 + subcase)
            # 记入备忘录             ↓勿忘条件！
            memo[amount] = res if res != float('INF') else -1
            return memo[amount]

        return dp(amount)

    # 法0：暴力dfs 【自顶向下】  ——>  总时间复杂度为 O(k * n^k)，指数级别。
    # 子问题总数为递归树节点个数： O(n^k)，指数级。每个子问题中含有一个 for 循环，复杂度为 O(k)。
    def coinChange0(coins: List[int], amount: int):
        def dp(n):
            if n == 0: return 0  # base case
            if n < 0: return -1
            # 求最小值，所以初始化为正无穷
            res = float('INF')
            for coin in coins:
                subproblem = dp(n - coin)
                # 子问题无解，跳过
                if subproblem == -1: continue
                res = min(res, 1 + subproblem)
            return res if res != float('INF') else -1

        return dp(amount)
# leetcode submit region end(Prohibit modification and deletion)
