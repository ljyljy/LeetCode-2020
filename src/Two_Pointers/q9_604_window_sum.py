# 滑动窗口内数的和 · Window Sum
# 亚马逊
# 描述
# 给你一个大小为n的整型数组和一个大小为k的滑动窗口，将滑动窗口从头移到尾，输出从开始到结束每一个时刻滑动窗口内的数的和。
#
# 样例
# 样例 1
#
# 输入：array = [1,2,7,8,5], k = 3
# 输出：[10,17,20]
# 解析：
# 1 + 2 + 7 = 10
# 2 + 7 + 8 = 17
# 7 + 8 + 5 = 20

class Solution:
    # @param nums {int[]} a list of integers
    # @param k {int} 滑动窗口大小
    # @return {int[]} the sum of element inside the window at each moving
    def winSum(self, nums, k):
        n = len(nums)
        if k <= 0 or n < k: return []
        sum = [0] * (n - k + 1)
        for i in range(k):
            sum[0] += nums[i]
        for i in range(1, n - k + 1):
            sum[i] = sum[i - 1] - nums[i - 1] + nums[i + k - 1]
        return sum
