# 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
#
#  你的算法时间复杂度必须是 O(log n) 级别。
#
#  如果数组中不存在目标值，返回 [-1, -1]。
#
#  示例 1:
#
#  输入: nums = [5,7,7,8,8,10], target = 8
# 输出: [3,4]
#
#  示例 2:
#
#  输入: nums = [5,7,7,8,8,10], target = 6
# 输出: [-1,-1]
#  Related Topics 数组 二分查找
#  👍 573 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class Solution:
    # 法1：九章模板
    def searchRange1(self, nums: List[int], target: int) -> List[int]:
        if not nums or target is None: return [-1, -1]
        left_idx = self.bSearch(nums, target, isFindLeft=True)
        if left_idx == -1: return [-1, -1]
        right_idx = self.bSearch(nums, target, isFindLeft=False)
        return [left_idx, right_idx]

    def bSearch(self, nums, target, isFindLeft=True):
        start, end = 0, len(nums) - 1
        while start + 1 < end:
            mid = start + end >> 1
            if target > nums[mid]:
                start = mid
            elif target < nums[mid]:
                end = mid
            else:  # target == nums[mid]
                if isFindLeft:
                    end = mid  # 1-优先查找左边界
                else:
                    start = mid
        if isFindLeft:  # 2-优先查找左边界
            if nums[start] == target: return start
            if nums[end] == target: return end
            return -1
        else:
            if nums[end] == target: return end
            if nums[start] == target: return start
            return -1

    # 法2：ACW模板
    def searchRange(self, nums, k):
        if not nums: return [-1, -1]
        l, r = 0, len(nums) - 1
        while l < r:
            mid = l + r >> 1
            if k > nums[mid]:
                l = mid + 1
            else:
                r = mid
        if nums[l] != k: return [-1, -1]
        l0 = l
        l, r = 0, len(nums) - 1
        while l < r:
            mid = l + r + 1 >> 1
            if k < nums[mid]:
                r = mid - 1
            else:
                l = mid
        return [l0, r]


if __name__ == '__main__':
    sol = Solution()
    nums, target = [0, 0, 0, 1, 2, 3], 0
    print(sol.searchRange1(nums, target))

# leetcode submit region end(Prohibit modification and deletion)
