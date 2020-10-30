# 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复
# 的三元组。
#
#  注意：答案中不可以包含重复的三元组。
#
#
#
#  示例：
#
#  给定数组 nums = [-1, 0, 1, 2, -1, -4]，
#
# 满足要求的三元组集合为：
# [
#   [-1, 0, 1],
#   [-1, -1, 2]
# ]
#
#  Related Topics 数组 双指针
#  👍 2704 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


# ❤ 对于求 2 个变量如何组合的问题
# 可以循环其中一个变量，然后研究另外一个变量如何变化
class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        nums = sorted(nums)
        rst = []
        # 假设 a <= b <= c：for 循环 a ，找 b + c = -a 即可调用 two sum 的算法来解决。
        for i in range(len(nums)):
            if i > 0 and nums[i] == nums[i - 1]: continue
            self.find_two_sum(nums, i + 1, len(nums) - 1, -nums[i], rst)
        return rst

    # 双指针 b + c = -a (target)
    def find_two_sum(self, nums, left, right, target, rst):
        last_pair = None
        while left < right:
            if nums[left] + nums[right] == target:
                if (nums[left], nums[right]) != last_pair:
                    rst.append([-target, nums[left], nums[right]])
                last_pair = (nums[left], nums[right])
                left += 1
                right -= 1
            elif nums[left] + nums[right] > target:
                right -= 1
            else:
                left += 1
# leetcode submit region end(Prohibit modification and deletion)
