# 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
#
#
#
#  说明:
#
#
#  初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
#  你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
#
#
#
#
#  示例:
#
#  输入:
# nums1 = [1,2,3,0,0,0], m = 3
# nums2 = [2,5,6],       n = 3
#
# 输出: [1,2,2,3,5,6]
#  Related Topics 数组 双指针
#  👍 599 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class Solution:
    def merge(self, A: List[int], m: int, B: List[int], n: int) -> None:
        # 从后往前保存： 初始化双指针和当前元素应存储在新数组的位置
        idx, pA, pB = m + n - 1, m - 1, n - 1
        while pA >= 0 and pB >= 0:  # 比较大小，并将其存入新数组
            if A[pA] > B[pB]:
                A[idx] = A[pA]
                idx -= 1
                pA -= 1
            else:
                A[idx] = B[pB]
                idx -= 1
                pB -= 1
        # 若未遍历完毕，继续把相应数组的剩余元素存入新数组
        while pA >= 0:
            A[idx] = A[pA]
            idx -= 1
            pA -= 1
        while pB >= 0:
            A[idx] = B[pB]
            idx -= 1
            pB -= 1

# leetcode submit region end(Prohibit modification and deletion)
