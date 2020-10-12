# 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组，返回
#  0。
#
#
#
#  示例：
#
#  输入：s = 7, nums = [2,3,1,2,4,3]
# 输出：2
# 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
#
#
#
#
#  进阶：
#
#
#  如果你已经完成了 O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
#
#  Related Topics 数组 双指针 二分查找
#  👍 469 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class Solution:
    # 前缀和 + 二分搜索
    def minSubArrayLen(self, sum: int, nums: List[int]) -> int:
        import bisect
        if not nums or sum is None: return 0
        n = len(nums)
        min_len = n + 1
        prefix_sum = [0]  # ↓ 构造前缀和数组
        for i in range(n):
            prefix_sum.append(prefix_sum[-1] + nums[i])
        # 二分 v1 -- bisect.bisect_left
        # for j in range(n):
        #     target = sum + prefix_sum[j]
        #     pos_rt = bisect.bisect_left(prefix_sum, target)
        #     if pos_rt != len(prefix_sum):
        #         min_len = min(min_len, pos_rt - j)
        # return 0 if min_len == n+1 else min_len

        # 二分 v2 -- 手撕
        for i in range(len(prefix_sum) - 1):
            start, end = i + 1, len(prefix_sum) - 1
            while start + 1 < end:
                mid = start + end >> 1
                if prefix_sum[mid] - prefix_sum[i] >= sum:
                    end = mid
                else:
                    start = mid
            if prefix_sum[start] - prefix_sum[i] >= sum:
                min_len = min(min_len, start - i)
            elif prefix_sum[end] - prefix_sum[i] >= sum:
                min_len = min(min_len, end - i)
        return 0 if min_len == n + 1 else min_len

        # 同向双指针 - O(n)

    def minSubArrayLen_1(self, sum: int, nums: List[int]) -> int:
        n = len(nums)
        if not n or sum is None: return 0
        lf, rt, tmp_sum, min_len = 0, 0, 0, n + 1
        for lf in range(n):
            while rt < n and tmp_sum < sum:
                tmp_sum += nums[rt]
                rt += 1  # 故min_len无需加1，此处以加过1
            if tmp_sum >= sum: min_len = min(min_len, rt - lf)
            tmp_sum -= nums[lf]  # 接着lf += 1
        if min_len == n + 1:
            return 0
        else:
            return min_len

# leetcode submit region end(Prohibit modification and deletion)
