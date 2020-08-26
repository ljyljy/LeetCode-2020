# 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
#
#
#
#  示例 1:
#
#  输入: [10,2]
# 输出: "102"
#
#  示例 2:
#
#  输入: [3,30,34,5,9]
# 输出: "3033459"
#
#
#
#  提示:
#
#
#  0 < nums.length <= 100
#
#
#  说明:
#
#
#  输出结果可能非常大，所以你需要返回一个字符串而不是整数
#  拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
#
#  Related Topics 排序
#  👍 87 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class Solution:
    # 法1：sort_rule
    def minNumber(self, nums: List[int]) -> str:
        import functools
        def sort_rule(x, y):
            a, b = x + y, y + x
            if a > b:
                return 1
            elif a < b:
                return -1
            else:
                return 0

        strs = [str(num) for num in nums]
        strs.sort(key=functools.cmp_to_key(sort_rule))
        return ''.join(strs)

    # 法2：快排
    def minNumber_WHY(self, nums: List[int]) -> str:
        def fast_sort(l, r):
            if l >= r: return
            i, j = l, r  # L: pivot  快排结果：(L, i)小于L，(j, R)大于L
            while i < j:  # 一定要j--在前， i++在后？？？
                while i < j and strs[j] + strs[l] >= strs[l] + strs[j]: j -= 1
                while i < j and strs[i] + strs[l] <= strs[l] + strs[i]: i += 1
                strs[i], strs[j] = strs[j], strs[i]
            strs[l], strs[i] = strs[i], strs[l]  # L: pivot分区的分界线,将其与i位置的数字互换
            fast_sort(l, i - 1)  # 经过互换，strs[i]为分区的分界线
            fast_sort(i + 1, r)

        strs = [str(num) for num in nums]
        fast_sort(0, len(strs) - 1)
        return ''.join(strs)

# leetcode submit region end(Prohibit modification and deletion)
