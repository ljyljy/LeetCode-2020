# 给定两个数组，编写一个函数来计算它们的交集。 
# 
#  
# 
#  示例 1： 
# 
#  输入：nums1 = [1,2,2,1], nums2 = [2,2]
# 输出：[2,2]
#
#
#  示例 2:
#
#  输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
# 输出：[4,9]
#
#
#
#  说明：
#
#
#  输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
#  我们可以不考虑输出结果的顺序。
#
#
#  进阶：
#
#
#  如果给定的数组已经排好序呢？你将如何优化你的算法？
#  如果 nums1 的大小比 nums2 小很多，哪种方法更优？
#  如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
#
#  Related Topics 排序 哈希表 双指针 二分查找
#  👍 392 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
import collections
from typing import List


class Solution:
    # 法一：Hash / Dict
    def intersect1(self, nums1: List[int], nums2: List[int]) -> List[int]:
        num1 = collections.Counter(nums1)  # Counter({1: 2, 2: 2})
        num2 = collections.Counter(nums2)  # Counter({2: 2})
        num = num1 & num2  # Counter({2: 2})
        return list(num.elements())

    # 法2：
    def intersect(self, nums1: List[int], nums2: List[int]) -> List[int]:
        nums1.sort()
        nums2.sort()
        i, j, rst = 0, 0, []
        while i < len(nums1) and j < len(nums2):
            if nums1[i] < nums2[j]:
                i += 1
            elif nums1[i] > nums2[j]:
                j += 1
            else:
                rst.append(nums1[i])
                i += 1
                j += 1
        return rst
# leetcode submit region end(Prohibit modification and deletion)
