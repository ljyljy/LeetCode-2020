# 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
#
#  如果数组元素个数小于 2，则返回 0。
#
#  示例 1:
#
#  输入: [3,6,9,1]
# 输出: 3
# 解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
#
#  示例 2:
#
#  输入: [10]
# 输出: 0
# 解释: 数组元素个数小于 2，因此返回 0。
#
#  说明:
#
#
#  你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
#  请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
#
#  Related Topics 排序
#  👍 257 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    # 法2：桶排序 O(n)
    def maximumGap(self, nums):
        n = len(nums)
        if n < 2: return 0
        max_, min_ = max(nums), min(nums)
        each_bucket_len = (max_ - min_) // (n - 1)
        buckets = [[] for _ in range((max_ - min_ + 1) // each_bucket_len)]  # 向上取整
        for i in range(n):
            loc = (nums[i] - min_) // each_bucket_len  # 向下取整
            buckets[loc].append(nums[i])

        prev_max, maxDiff = float('inf'), -1
        for i in range(len(buckets)):
            if buckets[i] and prev_max != float('inf'):
                maxDiff = max(maxDiff, min(buckets[i]) - prev_max)
            if buckets[i]:
                prev_max = max(buckets[i])
        return maxDiff

    # 法1： 暴力 O(nlogn)
    def maximumGap0(self, nums):
        if len(nums) < 2: return 0
        nums.sort()
        maxDiff = -1
        for i in range(len(nums) - 1):
            maxDiff = max(maxDiff, nums[i + 1] - nums[i])
        return maxDiff

# leetcode submit region end(Prohibit modification and deletion)
