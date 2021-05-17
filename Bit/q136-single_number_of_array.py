# 	136. 只出现一次的数字
# 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
# 说明：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
# 示例 1:
# 输入: [2,2,1]
# 输出: 1
# 示例 2:
# 输入: [4,1,2,1,2]
# 输出: 4
# 用 异或 可以轻松解决 —— 所有的数字异或起来，就是答案。
from typing import List


class Solution:
    def singleNumber(self, nums: List[int]) -> int:
        ans = 0  # [p xor 0 = p]
        for i in range(0, len(nums)):
            ans ^= nums[i]
        return ans
