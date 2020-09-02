# 	qo_69. 数组中数值和下标相等的元素
# 假设一个单调递增的数组里的每个元素都是整数并且是唯一的。
# 请编程实现一个函数找出数组中任意一个数值等于其下标的元素。
#
# 例如，在数组[-3, -1, 1, 3, 5]中，数字3和它的下标相等。

class Solution(object):
    def getNumberSameAsIndex(self, nums):
        if not nums: return -1
        l, r = 0, len(nums) - 1
        while l < r:
            mid = l + r >> 1
            if nums[mid] >= mid:
                r = mid
            else:
                l = mid + 1
        if nums[r] == r: return r
        return -1
