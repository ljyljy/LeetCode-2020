# [Link] acw_14. 不修改数组找出重复的数字
# 	287. 寻找重复的数
# 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
# 假设 nums 只有 一个重复的整数 ，找出 这个重复的数 。
# 示例 1：
# 输入：nums = [1,3,4,2,2]
# 输出：2
# 示例 2：
# 输入：nums = [3,1,3,4,2]
# 输出：3
# 示例 3：
# 输入：nums = [1,1]
# 输出：1
# 示例 4：
# 输入：nums = [1,1,2]
# 输出：1
# 提示：
# 	2 <= n <= 3 * 104
# 	nums.length == n + 1
# 	1 <= nums[i] <= n

class Solution:
    def findDuplicate(self, nums):
        n = len(nums)
        if n == 0: return -1
        left, right = 1, n  # 二分对象是区间[1,n], 而非num[0~n]!!!
        while left + 1 < right:
            mid = left + right >> 1
            if self.count(nums, mid) <= mid:  # 斜率 <= 1(前半段，尚未出现重复)
                left = mid  # 重复数字在后半段
            else:
                right = mid  # 斜率 > 1: 找到重复数字，第一次出现位置-在该折线之前
        if self.count(nums, left) <= left:
            return right
        return left

    def count(self, nums, mid):
        cnt = 0
        for num in nums:
            cnt += (num <= mid)
        return cnt
