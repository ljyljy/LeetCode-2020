# 魔术索引。 在数组A[0...n-1]中，有所谓的魔术索引，满足条件A[i] = i。给定一个有序整数数组，编写一种方法找出魔术索引，若有的话，在数组A中找
# 出一个魔术索引，如果没有，则返回-1。若有多个魔术索引，返回索引值最小的一个。
#
#  示例1:
#
#   输入：nums = [0, 2, 3, 4, 5]
#  输出：0
#  说明: 0下标的元素为0
#
#
#  示例2:
#
#   输入：nums = [1, 1, 1]
#  输出：1
#
#
#  说明:
#
#
#  nums长度在[1, 1000000]之间
#  此题为原书中的 Follow-up，即数组中可能包含重复元素的版本
#
#  Related Topics 数组 二分查找
#  👍 50 👎 0

# 思路：
# 题中强调的数组是排序的，如果还一个个查找，总感觉有点浪费。一想到排序数组很容易想到的是二分法查找，但是这里如果直接使用二分法查找的i不一定是最小的，所以有一种方式就是先把数组劈两半，先在前面一半查，如果找到就直接返回，如果没找到就在后面部分查，并且前面部分和后面部分在分别劈两半，一直这样递归下去……。

# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class Solution:
    def findMagicIndex(self, nums: List[int]) -> int:
        if not nums: return -1
        return self.devide_conquer(nums, 0, len(nums) - 1)

    def devide_conquer(self, nums, lo, hi):
        if lo > hi: return -1
        mid = lo + (hi - lo) // 2
        left = self.devide_conquer(nums, lo, mid - 1)
        if left != -1:
            return left
        elif nums[mid] == mid:
            return mid
        else:
            return self.devide_conquer(nums, mid + 1, hi)  # right

# leetcode submit region end(Prohibit modification and deletion)
