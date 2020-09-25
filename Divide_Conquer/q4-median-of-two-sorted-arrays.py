# 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
#
#  请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
#
#  你可以假设 nums1 和 nums2 不会同时为空。
#
#
#
#  示例 1:
#
#  nums1 = [1, 3]
# nums2 = [2]
#
# 则中位数是 2.0
#
#
#  示例 2:
#
#  nums1 = [1, 2]
# nums2 = [3, 4]
#
# 则中位数是 (2 + 3)/2 = 2.5
#
#  Related Topics 数组 二分查找 分治算法
#  👍 3227 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List

class Solution:
    # 写法1
    def findMedianSortedArrays0(self, A: List[int], B: List[int]) -> float:
        m, n = len(A), len(B)
        if (m + n) % 2 == 1:  # 总个数为奇数
            k = (m + n) // 2 + 1
            return self.getKth(A, 0, len(A) - 1, B, 0, len(B) - 1, k)
        else:  # 总个数为偶数
            k1, k2 = (m + n) // 2, (m + n) // 2 + 1
            left = self.getKth(A, 0, len(A) - 1, B, 0, len(B) - 1, k1)
            right = self.getKth(A, 0, len(A) - 1, B, 0, len(B) - 1, k2)
            return (left + right) / 2.0

    def getKth(self, A, start1, end1, B, start2, end2, k) -> float:
        lenA, lenB = end1 - start1 + 1, end2 - start2 + 1
        # 让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if lenA > lenB:
            return self.getKth(B, start2, end2, A, start1, end1, k)
        # A数组排除完毕（前提：确保A短于B↑）
        if (lenA == 0): return B[start2 + k - 1]
        # 已经找到第k小的数
        if k == 1:  return min(A[start1], B[start2])
        # 【边界完毕↑】 开始二分↓
        centerA, centerB = start1 + min(lenA, k // 2) - 1, start2 + min(lenB, k // 2) - 1
        if A[centerA] > B[centerB]:  # B[start2:centerB]不可能有解-> 剪枝
            newK = k - (centerB - start2 + 1)  # k // 2 # 错！因为k//2可能超出A的下标！越界！
            return self.getKth(A, start1, end1, B, centerB + 1, end2, newK)  # 每次并非严格二分查找'k'//2个数，要考虑是否越界
        else:  # A[start1:centerA]不可能有解-> 剪枝
            newK = k - (centerA - start1 + 1)  # k // 2 # 错！因为k//2可能超出A的下标！越界！
            return self.getKth(A, centerA + 1, end1, B, start2, end2, newK)

    # 写法2
    def findMedianSortedArrays2(self, nums1: List[int], nums2: List[int]) -> float:
        totalcnt = len(nums1) + len(nums2)
        if totalcnt % 2 == 0:  # 偶数个 --> 中位数 = 中间俩的平均
            left = self.findKth(nums1, 0, nums2, 0, totalcnt // 2)
            right = self.findKth(nums1, 0, nums2, 0, totalcnt // 2 + 1)
            return (left + right) / 2.0
        else:  # 奇数个
            return self.findKth(nums1, 0, nums2, 0, totalcnt // 2 + 1)

    # i,j: startIdx,  k: 从startIdx开始遍历的个数
    def findKth(self, nums1, i, nums2, j, k) -> float:
        if len(nums1) - i > len(nums2) - j:  # 保证nums1更短
            return self.findKth(nums2, j, nums1, i, k)
        if i == len(nums1):  # i 遍历到了末尾（nums1短于nums2） -- 返回nums2
            return nums2[j + k - 1]
        if k == 1: return min(nums1[i], nums2[j])
        ci, cj = int(min(i + k // 2, len(nums1))), int(j + k // 2)
        if nums1[ci - 1] > nums2[cj - 1]:
            return self.findKth(nums1, i, nums2, cj, int(k - (cj - j)))
        else:
            return self.findKth(nums1, ci, nums2, j, int(k - (ci - i)))


# leetcode submit region end(Prohibit modification and deletion)

if __name__ == "__main__":
    sol = Solution()
    nums1, nums2 = [1, 2, 3, 7, 8, 9], [4, 5, 6, 10, 11, 12, 13, 14, 15]
    res = sol.findMedianSortedArrays0(nums1, nums2)
    print(res)
# leetcode submit region end(Prohibit modification and deletion)
