# 给定两个数组，编写一个函数来计算它们的交集。 
# 
#  
# 
#  示例 1： 
# 
#  输入：nums1 = [1,2,2,1], nums2 = [2,2]
# 输出：[2]
#  
# 
#  示例 2： 
# 
#  输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
# 输出：[9,4] 
# 
#  
# 
#  说明： 
# 
#  
#  输出结果中的每个元素一定是唯一的。 
#  我们可以不考虑输出结果的顺序。 
#  
#  Related Topics 排序 哈希表 双指针 二分查找 
#  👍 229 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class Solution:
    # 法1：HashSet + &
    def intersection1(self, nums1: List[int], nums2: List[int]) -> List[int]:
        return list(set(nums1) & set(nums2))

    # 法2：双指针
    def intersection2(self, nums1: List[int], nums2: List[int]) -> List[int]:
        i, j, rst = 0, 0, set()
        nums1.sort()
        nums2.sort()
        while i < len(nums1) and j < len(nums2):
            if nums1[i] < nums2[j]:
                i += 1
            elif nums1[i] > nums2[j]:
                j += 1
            else:  # ==
                rst.add(nums1[i])
                i += 1
                j += 1
        return list(rst)

    # 法3：二分查找
    def intersection(self, nums1: List[int], nums2: List[int]) -> List[int]:
        def binarySearch(nums, target):
            if not nums: return False
            start, end = 0, len(nums) - 1
            while start + 1 < end:
                mid = start + end >> 1
                if nums[mid] == target:
                    return True
                elif nums[mid] < target:
                    start = mid
                else:
                    end = mid
            if nums[start] == target: return True
            if nums[end] == target: return True
            return False

        rst = set()
        nums1.sort()
        nums2.sort()
        # 在nums1中搜索nums2的每位元素，添加到intersect集合中
        for i in range(len(nums2)):
            if binarySearch(nums1, nums2[i]):
                rst.add(nums2[i])
        return list(rst)
# leetcode submit region end(Prohibit modification and deletion)
