# 	Q9_533. 两数和的最接近值
# 给定整数数组num，从中找到两个数字使得他们和最接近target，返回两数和与 target 的差的 绝对值。
# 挑战:  Do it in O(nlogn) time complexity.
# 例1：
# 输入: nums = [-1, 2, 1, -4] 并且 target = 4
# 输出: 1
# 解释:  最小的差距是1，(4 - (2 + 1) = 1).
# 例2:
# 输入: nums = [-1, -1, -1, -4] 并且 target = 4
# 输出: 6
# 解释: 最小的差距是6，(4 - (- 1 - 1) = 6).
# 相关题目
# 1879. 两数之和 VII1797. 最佳利用率443. 两数之和 II59. 最接近的三数之和56. 两数之和

import sys


class Solution:
    def twoSumClosest(self, nums, target):
        nums.sort()
        i, j = 0, len(nums) - 1
        diff = sys.maxsize
        while i < j:
            tmp_sum = nums[i] + nums[j]
            if tmp_sum < target:
                diff = min(diff, target - tmp_sum)
                i += 1
            else:
                diff = min(diff, tmp_sum - target)
                j -= 1
        return diff
