# 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
#
#  例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
#
#
#
#  示例 1：
#
#  输入：n = 12
# 输出：5
#
#
#  示例 2：
#
#  输入：n = 13
# 输出：6
#
#
#
#  限制：
#
#
#  1 <= n < 2^31
#
#
#  注意：本题与主站 233 题相同：https://leetcode-cn.com/problems/number-of-digit-one/
#  Related Topics 数学
#  👍 76 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
"""
example 设数字为abcde，当前位数为c位，
        c位1的个数即为高位个数+低位个数
        高位范围为 00 ~ ab-1 ：
            有 ab*100 个(因为c在百位)
        低位分为三种情况：
            c = 0 ,有 0 个
            c = 1 ，有 de + 1 个
            c > 1 , 有 100 个 ('1'00~'1'99)
        依次遍历每一位数相加，即为总共1的个数
"""


class Solution:
    def countDigitOne(self, n: int) -> int:
        res = 0
        n = str(n)
        for i, v in enumerate(n):  # v: abcde每一位上的数
            if i >= 1:  # 2位数起步 # ↓ 先处理高位部分，有 ab*100（c在百位）个
                res += int(n[:i]) * (10 ** (len(n) - i - 1))
            if int(v) == 1:  # c == 1时，处理低位: 00~de个1，即de+1个
                if i == len(n) - 1:  # 个位（个位恰好为1 -- v）
                    res += 1
                else:
                    res += int(n[i + 1:]) + 1  # 00~de个1，即de+1个
            elif int(v) > 1:  # c > 1: 共'1'00~'1'99个1，即100个
                res += 10 ** (len(n) - i - 1)
        return res

# leetcode submit region end(Prohibit modification and deletion)
