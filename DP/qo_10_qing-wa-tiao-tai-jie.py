# 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
#
#  答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
#
#  示例 1：
#
#  输入：n = 2
# 输出：2
#
#
#  示例 2：
#
#  输入：n = 7
# 输出：21
#
#
#  提示：
#
#
#  0 <= n <= 100
#
#
#  注意：本题与主站 70 题相同：https://leetcode-cn.com/problems/climbing-stairs/
#
#
#  Related Topics 递归
#  👍 51 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def numWays(self, n: int) -> int:
        if n <= 1: return 1
        f1, f2, f3 = 1, 2, 3
        for i in range(3, n + 1):
            f3 = f1 + f2
            f1 = f2
            f2 = f3
        return mod(f2, 1000000007)

# leetcode submit region end(Prohibit modification and deletion)
