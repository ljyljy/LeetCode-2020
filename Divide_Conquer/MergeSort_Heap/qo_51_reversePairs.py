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


from typing import List


# 法2: 线段树
class SegmentTree(object):
    def __init__(self, start, end, cnt=0):
        self.start, self.end, self.cnt = start, end, cnt
        self.left, self.right = None, None

    @classmethod
    def build(cls, start, end, arr):
        if start > end: return None
        if start == end:
            return SegmentTree(start, end, 0)
        root = SegmentTree(start, end, 0)
        mid = start + end >> 1
        root.left = cls.build(start, mid, arr)
        root.right = cls.build(mid + 1, end, arr)
        root.cnt = root.left.cnt + root.right.cnt
        return root

    @classmethod
    def modify(cls, root, idx, cnt=1):
        if not root: return None
        if root.start == root.end:  # == idx
            root.cnt += cnt
            return
        mid = root.start + root.end >> 1
        if idx <= mid:
            cls.modify(root.left, idx, cnt)
        else:
            cls.modify(root.right, idx, cnt)
        root.cnt = root.left.cnt + root.right.cnt

    @classmethod
    def query(cls, root, start, end):
        if start > end or end < root.start or root.end < start:
            return 0  # 不可写等于，由于有重复元素，但位置不一样，逆序对个数也不一样
        if start <= root.start and root.end <= end:
            return root.cnt
        mid = root.start + root.end >> 1
        if end <= mid: return cls.query(root.left, start, end)
        if mid < start: return cls.query(root.right, start, end)
        return cls.query(root.left, start, mid) + \
               cls.query(root.right, mid + 1, end)


class Solution2:
    def reversePairs(self, nums: List[int]) -> int:
        rst = 0
        if not nums: return rst
        # 离散化：将nums转化成升序后对应元素的下标列表
        sorted_nums = sorted(nums)
        sorted_idx = [sorted_nums.index(num) for num in nums]
        # 线段树
        root = SegmentTree.build(0, len(nums) - 1, nums)
        for si in sorted_idx:
            rst += (root.cnt - SegmentTree.query(root, 0, si))
            print(f'si: {si}, root.cnt: {root.cnt}, query(root, 0, si):{SegmentTree.query(root, 0, si)}')
            SegmentTree.modify(root, si, 1)
        return rst
# leetcode submit region end(Prohibit modification and deletion)
