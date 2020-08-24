# 数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，
# 等等。
#
#  请写一个函数，求任意第n位对应的数字。
#
#
#
#  示例 1：
#
#  输入：n = 3
# 输出：3
#
#
#  示例 2：
#
#  输入：n = 11
# 输出：0
#
#
#
#  限制：
#
#
#  0 <= n < 2^31
#
#
#  注意：本题与主站 400 题相同：https://leetcode-cn.com/problems/nth-digit/
#  Related Topics 数学
#  👍 54 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
import math


class Solution:
    def findNthDigit(self, n: int) -> int:
        if n < 10: return n  # 10以内的输入单独处理，便于后面实现
        num_i = 9  # 用来确定第n个数字的区间: [i位数]共有多少数字（2: 90, 3: 900...）
        i = 1  # 用来确定第n个数字的位数i: 1*9, 2*90，3*900, ...（此处考虑'0': +1, 下标:-1 --抵消）
        base = 1  # [i位数]的起始数字； 10, 100, 1000 ...
        while n > i * num_i:
            n -= i * num_i
            i += 1
            num_i *= 10
            base *= 10  # ↓ i位数字集中的第几个
        number = base + math.ceil(n / i) - 1  # 还原所求数字
        r = n % i if n % i else i  # 求是该数中的第几位
        # 法2：
        # j = 0
        # while j < i-r: number /= 10; j += 1  # --> return number % 10
        return int(str(number)[r - 1])

# leetcode submit region end(Prohibit modification and deletion)
