# 	Q9-610. 两数和 - 差等于目标值
# 给定一个排序后的整数数组，找到两个数的 差 等于目标值。 你需要返回一个包含两个数字的列表[num1, num2], 使得num1与num2的差为target，同时num1必须小于num2。
# 挑战:  O(n^2) 时间, O(1) 额外空间.
# 例1：
# 输入: nums = [2, 7, 15, 24], target = 5
# 输出: [2, 7]
# 解释:  (7 - 2 = 5)
# 例2:
# 输入: nums = [1, 1], target = 0
# 输出: [1, 1]
# 解释:  (1 - 1 = 0)
# 相关题目
# 1879. 两数之和 VII1797. 最佳利用率1187. 数组中的K-diff对689. 两数之和 - BST版本
#  由于数组有序，我们可以用双指针来做 对于双指针i,j,当numj-numi< target时，说明j太小，于是我们将j++，直到numj-numi >= target，若numj-numi > target，我们将i++，若numj-numi = target说明我们找到答案

class Solution:
    def twoSum7(self, nums, target):
        n, j = len(nums), 0
        if target < 0: target = -target
        for i in range(n):
            j = max(i + 1, j)
            while j < n and nums[j] - nums[i] < target: j += 1
            if j > n: break
            if nums[j] - nums[i] == target:
                return [nums[i], nums[j]]
        return [-1, -1]
