# 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
#
#  ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
#
#  请找出其中最小的元素。
#
#  注意数组中可能存在重复的元素。
#
#  示例 1：
#
#  输入: [1,3,5]
# 输出: 1
#
#  示例 2：
#
#  输入: [2,2,2,0,1]
# 输出: 0
#
#  说明：
#
#
#  这道题是 寻找旋转排序数组中的最小值 的延伸题目。
#  允许重复会影响算法的时间复杂度吗？会如何影响，为什么？
#
#  Related Topics 数组 二分查找
#  👍 159 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class Solution:
    def findMin(self, nums: List[int]) -> int:
        if not nums: return -1
        left, right = 0, len(nums) - 1
        while right > 0 and nums[right] == nums[0]:  # 否则无法左右独立二分
            right -= 1  # 去除旋转后【右区间末尾 vs nums[0]】的重复元素 ↑
        # case1：不存在旋转（本身升序）
        if nums[0] <= nums[right]: return nums[0]  # 若去重后，依旧有序，直接返回min
        # case2：存在旋转
        while left < right:
            mid = left + right >> 1  # [l, mid], [mid+1, r]
            if nums[mid] < nums[0]:  # min在mid以左
                right = mid
            else:  # 若mid与0处值相等，说明min在右区间！！❤❤❤
                left = mid + 1  # 【如：[2,2,2,0,1]】
        return nums[left]

# leetcode submit region end(Prohibit modification and deletion)
