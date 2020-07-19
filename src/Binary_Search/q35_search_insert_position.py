# 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
#
#  你可以假设数组中无重复元素。
#
#  示例 1:
#
#  输入: [1,3,5,6], 5
# 输出: 2
#
#
#  示例 2:
#
#  输入: [1,3,5,6], 2
# 输出: 1
#
#
#  示例 3:
#
#  输入: [1,3,5,6], 7
# 输出: 4
#
#
#  示例 4:
#
#  输入: [1,3,5,6], 0
# 输出: 0
#
#  Related Topics 数组 二分查找
#  👍 574 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class Solution:
    def searchInsert(self, nums: List[int], target: int) -> int:
        if not nums: return -1
        left, right = 0, len(nums) - 1

        while (left + 1 < right):
            mid = int(left + (right - left) / 2)
            if nums[mid] == target:
                return mid
            elif nums[mid] < target:
                left = mid
            else:
                right = mid

        if nums[left] == target:
            return max(0, left)
        elif nums[right] == target:
            return max(0, right)
        elif nums[right] > target and nums[left] < target:
            return max(0, left + 1)
        elif nums[left] > target:
            return max(0, left - 1)
        elif nums[right] < target:
            return right + 1

# leetcode submit region end(Prohibit modification and deletion)
