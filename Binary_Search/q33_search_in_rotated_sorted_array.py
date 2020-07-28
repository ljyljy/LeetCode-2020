# 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
#
#  ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
#
#  搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
#
#  你可以假设数组中不存在重复的元素。
#
#  你的算法时间复杂度必须是 O(log n) 级别。
#
#  示例 1:
#
#  输入: nums = [4,5,6,7,0,1,2], target = 0
# 输出: 4
#
#
#  示例 2:
#
#  输入: nums = [4,5,6,7,0,1,2], target = 3
# 输出: -1
#  Related Topics 数组 二分查找
#  👍 832 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    # 法1
    def search(self, nums: List[int], target: int) -> int:
        if not nums: return -1
        start, end = 0, len(nums) - 1
        while start + 1 < end:
            mid = start + (end - start) // 2
            if nums[start] <= nums[mid]:  # 递增序列(在第一段)
                if nums[start] <= target <= nums[mid]:
                    end = mid
                else:  # nums[mid] < target or nums[start] > target(第二段):
                    start = mid
            else:  # 有旋转部分 且mid在第二段(矮/小段)
                if nums[mid] <= target <= nums[end]:
                    start = mid
                else:  # nums[mid] > target or target > nums[end](第一段)
                    end = mid
        if nums[start] == target: return start
        if nums[end] == target: return end
        return -1

    # 法2：用2次二分——先找到分割点，再对target所处的某一边的子递增序列二分
    def search2(self, A, target):
        if not A:
            return -1

        index = self.find_min_index(A)
        if A[index] <= target <= A[-1]:
            return self.binary_search(A, index, len(A) - 1, target)
        return self.binary_search(A, 0, index - 1, target)

    def find_min_index(self, A):
        start, end = 0, len(A) - 1
        while start + 1 < end:
            mid = (start + end) // 2
            if A[mid] < A[end]:
                end = mid
            else:
                start = mid

        if A[start] < A[end]:
            return start
        return end

    def binary_search(self, A, start, end, target):
        while start + 1 < end:
            mid = (start + end) // 2
            if A[mid] < target:
                start = mid
            else:
                end = mid
        if A[start] == target:
            return start
        if A[end] == target:
            return end
        return -1

# leetcode submit region end(Prohibit modification and deletion)
