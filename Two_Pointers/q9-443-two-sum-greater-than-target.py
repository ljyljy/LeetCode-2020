# 	Q9_443. 两数之和 - Two Sum - Greater than target
# 给一组整数，问能找出多少对整数，他们的和大于一个给定的目标值。
# 进阶：O(1) 额外空间以及 O(nlogn) 时间复杂度？
# 例1：
# 输入: [2, 7, 11, 15], target = 24
# 输出: 1
# 解释: 11 + 15 是唯一的一对

class Solution:
    # 双指针 - O(nlogn) + O(n)?
    def twoSum2_1(self, nums, target):
        nums.sort()
        cnt, lf, rt = 0, 0, len(nums) - 1
        while lf < rt:
            if nums[lf] + nums[rt] > target:
                cnt += rt - lf  # 固定rt, lf=[lf, rt)均满足
                rt -= 1
            else:
                lf += 1
        return cnt

    # 二分 -- O(nlogn)
    # binary search解法，复杂度是O(nlogn)，比双指针O(n^2)要好点。
    # 算法 1. i从左到右遍历； 2.从0到i-1找第一个大于target - nums[i]的数； 3.找得到的话，count += i - first
    def twoSum2(self, nums, target):
        def binSearch_1stLarger(end, target):
            # 起始节点为要找的数
            start = 0
            while start + 1 < end:
                mid = start + end >> 1
                if nums[mid] > target:
                    end = mid
                else:
                    start = mid
            if nums[start] > target: return start
            if nums[end] > target: return end
            return -1

        if not nums or len(nums) <= 1: return 0
        nums.sort()
        cnt = 0
        for i in range(len(nums)):
            first = binSearch_1stLarger(i - 1, target - nums[i])
            if first != -1:
                cnt += i - first
        return cnt
