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
    def findMedianSortedArrays(self, nums1: List[int], nums2: List[int]) -> float:
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
        # ❤ 若i + k // 2超出nums1长度，则令i=len(nums1)，下次判断时，触发边界条件↑
        ci, cj = int(min(i + k // 2, len(nums1))), int(j + k // 2)
        if nums1[ci - 1] > nums2[cj - 1]:
            return self.findKth(nums1, i, nums2, cj, int(k - (cj - j)))
        else:  # nums1 中位数 < nums2中位数 -- nums1[i:ci]-> ∵min ∴舍弃
            # ∴ --> 遍历剩余的 (k-(ci-i))个数
            return self.findKth(nums1, ci, nums2, j, int(k - (ci - i)))


if __name__ == "__main__":
    sol = Solution()
    nums1, nums2 = [1, 2, 3, 7, 8, 9], [4, 5, 6, 10, 11, 12, 13, 14, 15]
    res = sol.findMedianSortedArrays(nums1, nums2)
    print(res)
# leetcode submit region end(Prohibit modification and deletion)
