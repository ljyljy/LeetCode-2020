# 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可
# 能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
#
#
#
#  示例 1:
#
#  输入: 12258
# 输出: 5
# 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
#
#
#
#  提示：
#
#
#  0 <= num < 231
#
#  👍 123 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def translateNum(self, num: int) -> int:
        strnum = str(num)
        dp = [-1 for _ in range(len(strnum) + 1)]
        dp[0] = 1
        for i in range(1, len(strnum) + 1):
            dp[i] = dp[i - 1]
            if i >= 2:
                k = int(strnum[i - 2]) * 10 + int(strnum[i - 1])
                if 10 <= k <= 25: dp[i] += dp[i - 2]
        return dp[-1]
# leetcode submit region end(Prohibit modification and deletion)
