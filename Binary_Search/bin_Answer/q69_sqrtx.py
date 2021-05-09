# 实现 int sqrt(int x) 函数。
#
#  计算并返回 x 的平方根，其中 x 是非负整数。
#
#  由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
#
#  示例 1:
#
#  输入: 4
# 输出: 2
#
#
#  示例 2:
#
#  输入: 8
# 输出: 2
# 说明: 8 的平方根是 2.82842...,
#      由于返回类型是整数，小数部分将被舍去。
#
#  Related Topics 数学 二分查找
#  👍 449 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# 思路：直接对答案可能存在的区间进行二分 => 二分答案
# 注意：判断区间的时候一个小技巧： mid * mid == x 中使用乘法可能会溢出，写成 mid == x / mid 即可防止溢出，不需要使用long或者BigInteger
class Solution:
    def mySqrt(self, x: int) -> int:
        left, right = 0, x
        while left + 1 < right:
            mid = left + (right - left) // 2
            # 为避免溢出，勿写 "nums[mid] * nums[mid]"
            if mid == x / mid:
                return mid
            elif mid > x / mid:
                right = mid
            else:
                left = mid
        if right * right == x:
            return right
        return left

# leetcode submit region end(Prohibit modification and deletion)
