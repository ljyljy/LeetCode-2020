# 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
#
#
#
#  示例 1:
#
#  输入: [7,5,6,4]
# 输出: 5
#
#
#
#  限制：
#
#  0 <= 数组长度 <= 50000
#  👍 222 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class Solution:
    # =============== 归并排序 - 写法1 ===============
    def reversePairs1(self, nums: List[int]) -> int:
        return self.merge_and_cnt(nums, 0, len(nums) - 1)

    def merge_and_cnt(self, nums, lt, rt):
        if lt >= rt: return 0
        mid = lt + rt >> 1
        l_cnt = self.merge_and_cnt(nums, lt, mid)
        r_cnt = self.merge_and_cnt(nums, mid + 1, rt)
        total_cnt = l_cnt + r_cnt  # 此时，[lt, mid] 和 [mid+1, rt] 已经完成了排序并且计算好逆序对
        # ↓ 加速优化：此时不用计算横跨两个区间的逆序对，直接返回 reverse_pairs
        if nums[mid] <= nums[mid + 1]: return total_cnt

        i, j, tmp = lt, mid + 1, []
        while i <= mid and j <= rt:
            if nums[i] <= nums[j]:
                tmp.append(nums[i])
                i += 1
            else:  # 当前j(后者)严格小于i(前者)
                total_cnt += mid - i + 1  # 则j能与前面的[i, mid]个数组成逆序对
                tmp.append(nums[j])
                j += 1
        while i <= mid:
            tmp.append(nums[i])
            i += 1
        while j <= rt:
            tmp.append(nums[j])
            j += 1
        # 将nums[start:end]升序排列
        for ii in range(len(tmp)):
            nums[lt + ii] = tmp[ii]
        return total_cnt

    # =============== 归并排序 - 写法2 ===================
    def reversePairs3(self, nums: List[int]) -> int:
        self.total_cnt = 0

        # 不同：传统merge是传入(arr1, arr2)
        def merge(nums, start, mid, end):
            i, j, tmp = start, mid + 1, []
            while i <= mid and j <= end:
                if nums[i] <= nums[j]:
                    tmp.append(nums[i])
                    i += 1
                else:  # 当前j(后者)严格小于i(前者)
                    # 则j能与前面的[i, mid]个数组成逆序对
                    self.total_cnt += mid - i + 1
                    tmp.append(nums[j])
                    j += 1
            while i <= mid:
                tmp.append(nums[i])
                i += 1
            while j <= end:
                tmp.append(nums[j])
                j += 1
            for ii in range(len(tmp)):
                nums[start + ii] = tmp[ii]

        def mergeSort(nums, start, end):
            if start >= end: return
            mid = start + end >> 1
            mergeSort(nums, start, mid)
            mergeSort(nums, mid + 1, end)
            merge(nums, start, mid, end)

        mergeSort(nums, 0, len(nums) - 1)
        return self.total_cnt

    # =============== 归并排序 - 写法3(略) ===================
    def reversePairs(self, nums: List[int]) -> int:
        def cnt_reverse_pairs(nums, left, right):
            if left >= right: return 0
            mid = left + right >> 1
            l_cnt = cnt_reverse_pairs(nums, left, mid)
            r_cnt = cnt_reverse_pairs(nums, mid + 1, right)
            lr_cnt = l_cnt + r_cnt  # 此时，[lt, mid] 和 [mid+1, rt] 已经完成了排序并且计算好逆序对
            # ↓ 加速优化：此时不用计算横跨两个区间的逆序对，直接返回 reverse_pairs
            if nums[mid] <= nums[mid + 1]: return lr_cnt
            cross_cnt = merge_and_cnt(nums, left, mid, right)
            return lr_cnt + cross_cnt  # 左右区间内 + 左&右跨区间的逆序对

        def merge_and_cnt(nums, left, mid, right):
            """ [left, mid] 有序，[mid + 1, right] 有序
            前：[2, 3, 5, 8]，后：[4, 6, 7, 12]
            只在后面数组元素出列的时候，数一数前面这个数组还剩下多少个数字，
            由于"前"数组和"后"数组都有序，
            此时"前"数组剩下的元素个数 mid - i + 1 就是与"后"数组元素出列的这个元素构成的逆序对个数
            """
            ### 略 ###

        if len(nums) <= 1: return 0
        sorted_arr = [0 for _ in range(len(nums))]
        return cnt_reverse_pairs(nums, 0, len(nums) - 1)

# leetcode submit region end(Prohibit modification and deletion)
