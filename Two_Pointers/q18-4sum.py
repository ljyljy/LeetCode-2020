# 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c +
#  d 的值与 target 相等？找出所有满足条件且不重复的四元组。
#
#  注意：
#
#  答案中不可以包含重复的四元组。
#
#  示例：
#
#  给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
#
# 满足要求的四元组集合为：
# [
#   [-1,  0, 0, 1],
#   [-2, -1, 1, 2],
#   [-2,  0, 0, 2]
# ]
#
#  Related Topics 数组 哈希表 双指针
#  👍 664 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class Solution:
    def fourSum(self, nums: List[int], target: int) -> List[List[int]]:
        if not nums: return []
        nums.sort()
        n, rst = len(nums), []
        for i in range(n):  # 去重1 ↓
            if i - 1 >= 0 and nums[i] == nums[i - 1]: continue
            for j in range(i + 1, n):  # 去重2↓  j-1 >= i+1 ↓
                if j - 1 > i and nums[j] == nums[j - 1]: continue
                pairs = self.find_2_sum_pairs(nums, j + 1, n - 1,
                                              target - nums[i] - nums[j])
                for (k, m) in pairs:
                    rst.append([nums[i], nums[j], k, m])
        return rst

    def find_2_sum_pairs(self, nums, left, right, target):
        pairs = []
        while left < right:
            if nums[left] + nums[right] < target:
                left += 1
            elif nums[left] + nums[right] > target:
                right -= 1
            else:  # ↓无需判断'in'，∵有序 ∴只需判断pairs[-1]
                if not pairs or (nums[left], nums[right]) != pairs[-1]:
                    pairs.append((nums[left], nums[right]))
                left += 1
                right -= 1
        return pairs
# leetcode submit region end(Prohibit modification and deletion)
