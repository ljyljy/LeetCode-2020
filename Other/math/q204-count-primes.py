# 统计所有小于非负整数 n 的质数的数量。
#
#
#
#  示例 1：
#
#  输入：n = 10
# 输出：4
# 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
#
#
#  示例 2：
#
#  输入：n = 0
# 输出：0
#
#
#  示例 3：
#
#  输入：n = 1
# 输出：0
#
#
#
#
#  提示：
#
#
#  0 <= n <= 5 * 106
#
#  Related Topics 哈希表 数学
#  👍 495 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
import math


class Solution:
    def countPrimes(self, n: int) -> int:
        isPrim = [True for _ in range(n)]
        for i in range(2, int(math.sqrt(n)) + 1):
            if isPrim[i]:  # 将i的倍数均设为false
                # j ∈ (2i: i: n) --> 去冗余(ii: i: n)
                for j in range(i * i, n, i):
                    isPrim[j] = False

        cnt = 0
        for i in range(2, n):
            if isPrim[i]: cnt += 1
        return cnt

# leetcode submit region end(Prohibit modification and deletion)
