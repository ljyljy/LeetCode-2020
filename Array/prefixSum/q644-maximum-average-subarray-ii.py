# 给你一个包含 n 个整数的数组，请你找出 长度大于等于 k 且含最大平均值的连续子数组。并输出这个最大平均值。
#
#
#
#  示例：
#
#
# 输入：[1,12,-5,-6,50,3], k = 4
# 输出：12.75
# 解释：
# 当长度为 5 的时候，最大平均值是 10.8，
# 当长度为 6 的时候，最大平均值是 9.16667。
# 所以返回值是 12.75。
#
#
#
#
#  提示：
#
#
#  1 <= k <= n <= 10,000。
#  数组中的元素范围是 [-10,000, 10,000]。
#  答案的计算误差小于 10-5 。
#
#  Related Topics 数组 二分查找
#  👍 53 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class Solution:
    # 二分答案  O(nlogn)
    def findMaxAverage(self, nums: List[int], k: int) -> float:
        if not nums or len(nums) == 0 or k <= 0: return -1
        n, eps = len(nums), 1e-5
        start, end = min(nums), max(nums)
        while start + eps < end:
            mid = start + end >> 1
            if self.check_subarray(nums, k, mid):
                start = mid
            else:
                end = mid
        return start

    def check_subarray(self, nums, k, avg):
        n = len(nums)
        # 前缀数组构造1
        prefix_sum = [0 for i in range(n + 1)]
        for i in range(1, n + 1):
            prefix_sum[i] = prefix_sum[i - 1] + nums[i]
        # # 前缀数组构造2
        # prefix_sum = [0]
        # for num in nums:
        #     prefix_sum.append(prefix_sum[-1] + num)
        prefix_sum -= avg  # s.t. ∑ (ai - mid) >= 0, mid即当前搜索的avg

        min_prefix_sum = 0
        for k_ in range(k, n + 1):
            if prefix_sum[k_] - min_prefix_sum >= 0:
                return True  # ↑↓见草稿纸递推过程❤❤❤
            min_prefix_sum = min(min_prefix_sum, prefix_sum[k_ - k + 1])
        return False

    # TLE!!!!  O(n^2)
    def findMaxAverage_TLE(self, nums: List[int], k: int) -> float:
        if not nums or len(nums) == 0 or k <= 0: return -1
        n = len(nums)
        prefix_sum = [0 for i in range(n + 1)]
        for i in range(1, n + 1):
            prefix_sum[i] = prefix_sum[i - 1] + nums[i - 1]
        max_k = k  # 注意该题需要直接判断avg（因为本题的k会变化，与i题不同！）
        max_avg = prefix_sum[k] / max_k
        for k_ in range(k, n):  # k_ ∈ [k, n]
            for i in range(n - k_ + 1):  # i + k_ < n+1
                tmp_avg = (prefix_sum[i + k_] - prefix_sum[i]) / k_
                if tmp_avg > max_avg:
                    max_avg = tmp_avg
                    max_k = k_
        return max_avg

# leetcode submit region end(Prohibit modification and deletion)
