# 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
#
#
#
#  示例 1：
#
#  输入: n = 3
# 输出: 6
#
#
#  示例 2：
#
#  输入: n = 9
# 输出: 45
#
#
#
#
#  限制：
#
#
#  1 <= n <= 10000
#
#  👍 199 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def __init__(self):
        self.res = 0

    def sumNums(self, n: int) -> int:
        #  “当 n = 1n=1 时终止递归” 的需求，可通过短路效应实现。
        n > 1 and self.sumNums(n - 1)
        self.res += n
        return self.res
# leetcode submit region end(Prohibit modification and deletion)
