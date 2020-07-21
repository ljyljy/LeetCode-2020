# 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
#
#  ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
#
#  编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
#
#  示例 1:
#
#  输入: nums = [2,5,6,0,0,1,2], target = 0
# 输出: true
#
#
#  示例 2:
#
#  输入: nums = [2,5,6,0,0,1,2], target = 3
# 输出: false
#
#  进阶:
#
#
#  这是 搜索旋转排序数组 的延伸题目，本题中的 nums 可能包含重复元素。
#  这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
#
#  Related Topics 数组 二分查找
#  👍 191 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class Solution:
    def search(self, nums: List[int], target: int) -> bool:
        if not nums: return False
        # step1: find pivot（旋转后的最低点, minPos）
        pivot = self._get_pivot(nums)
        # step2: split
        left, right = self._split(nums, pivot, target)
        # step3: find target
        return self._binary_search(left, right, nums, target)

    def _get_pivot(self, nums):
        left, right = 0, len(nums) - 1
        while left < right:
            mid = left + (right - left) // 2
            if nums[mid] < nums[right]:  # 右半段
                right = mid
            elif nums[mid] > nums[right]:
                left = mid + 1  # mid在左半段, pivot在mid右边
            else:  # nums[mid] == nums[right]
                if nums[right - 1] > nums[right] and right >= 1:
                    left = right  # 退出，这就是pivot
                else:
                    right -= 1  # 否则前移
        return left

    def _split(self, nums, pivot, target):
        if pivot == 0:  # nums未旋转
            left, right = 0, len(nums) - 1
        elif nums[0] <= target:  # 有旋转，目标在左半边
            left, right = 0, pivot - 1
        else:  # 有旋转，目标在右半边
            # pivot是最低点，本身处于右半边
            left, right = pivot, len(nums) - 1
        return [left, right]

    def _binary_search(self, left, right, A, target):
        while left + 1 < right:
            mid = left + (right - left) // 2
            if A[mid] < target:
                left = mid
            else:
                right = mid
        if A[left] == target: return True
        if A[right] == target: return True
        return False

    # 写法2：
    def search2(self, A, target):
        if not len(A): return False
        # step1: find pivot
        left, right = 0, len(A) - 1
        if A[left] > A[right]:  # 存在旋转
            while left < right:
                mid = left + (right - left) // 2
                if A[mid] < A[right]:
                    right = mid
                elif A[mid] > A[right]:
                    left = mid
                else:
                    if A[right - 1] > A[right] and right >= 1:
                        left = right
                    else:
                        right -= 1
        pivot = left
        # step2: split
        if pivot == 0:  # 不存在旋转，本身有序
            left, right = 0, len(A) - 1
        elif target < A[right]:  # 存在旋转，目标位于右半段
            left, right = pivot, len(A) - 1
        else:  # 存在旋转，目标位于左半段
            left, right = 0, pivot - 1
        # step3: find target
        while left + 1 < right:
            mid = left + (right - left) // 2
            if A[mid] < target:
                left = mid
            else:
                right = mid
        if A[left] == target: return True
        if A[right] == target: return True
        return False

# leetcode submit region end(Prohibit modification and deletion)
