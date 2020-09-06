# 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
#
#
#
#  示例 1：
#
#  输入：nums = [2,7,11,15], target = 9
# 输出：[2,7] 或者 [7,2]
#
#
#  示例 2：
#
#  输入：nums = [10,26,30,31,47,60], target = 40
# 输出：[10,30] 或者 [30,10]
#
#
#
#
#  限制：
#
#
#  1 <= nums.length <= 10^5
#  1 <= nums[i] <= 10^6
#
#  👍 40 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class Solution:
    # dict 1: 类似方法二，不需要 mun2 不需要在整个 dict 中去查找。可以在 num1 之前的 dict 中查找，因此就只需要一次循环可解决。
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        if target < nums[0]: return []
        dict = {}
        for i, num in enumerate(nums):
            j = dict.get(target - num, -1)
            if j != -1 and i != j: return [nums[j], nums[i]]
            dict[num] = i
        return []

    # dict 2
    def twoSum2(self, nums: List[int], target: int) -> List[int]:
        if target < nums[0]: return []
        dict = {}
        for idx, num in enumerate(nums):
            dict[num] = idx
        for i, num in enumerate(nums):
            j = dict.get(target - num, -1)
            if j != -1 and i != j:
                return [nums[i], nums[j]]
        return []

# leetcode submit region end(Prohibit modification and deletion)
