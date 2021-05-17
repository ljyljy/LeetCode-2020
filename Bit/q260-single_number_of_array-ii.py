# 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。
#
#  示例 :
#
#  输入: [1,2,1,3,2,5]
# 输出: [3,5]
#
#  注意：
#
#
#  结果输出的顺序并不重要，对于上面的例子， [5, 3] 也是正确答案。
#  你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
#
#  Related Topics 位运算
#  👍 280 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class Solution:
    def singleNumber(self, nums: List[int]) -> List[int]:
        xor2, a, b, tmp = 0, 0, 0, 1  # 所有数字异或的结果-答案之一, 分组1 & 2
        # 1. 先对所有数字进行一次异或，得到：
        #   【两个出现一次的数字的异或值xor2 - 恰是俩数字的不同之处】。
        for num in nums:
            xor2 ^= num
        # 2. 根据xor2，对其按位分组，找到第一位非0的位数，找到另一个不同的答案
        while (xor2 & tmp) == 0: tmp <<= 1  # 遍历低位->高位的每一位
        # 退出while： xor2 & tmp == 1时：当前xor2的非零位即为tmp中1所指的
        # 3. 根据该位是否为0将其分为两组
        for num in nums:
            if num & tmp:
                a ^= num  # tmp所指位 非0
            else:
                b ^= num
        return [a, b]

    def singleNumber_clear(self, nums: List[int]) -> List[int]:
        xor2, a, b, tmp = 0, 0, 0, 1
        for num in nums:
            xor2 ^= num
        while tmp & xor2 == 0: tmp <<= 1
        for num in nums:
            if tmp & num:
                a ^= num
            else:
                b ^= num
        return [a, b]
# leetcode submit region end(Prohibit modification and deletion)
