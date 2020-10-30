# 给定一个长度为 n 的整数数组和一个目标值 target，寻找能够使条件 nums[i] + nums[j] + nums[k] < target 成立的三
# 元组 i, j, k 个数（0 <= i < j < k < n）。
#
#  示例：
#
#  输入: nums = [-2,0,1,3], target = 2
# 输出: 2
# 解释: 因为一共有两个三元组满足累加和小于 2:
#      [-2,0,1]
#      [-2,0,3]
#
#
#  进阶：是否能在 O(n2) 的时间复杂度内解决？
#  Related Topics 数组 双指针
#  👍 52 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def threeSumSmaller(self, nums: List[int], target: int) -> int:
        if not nums or target is None: return 0
        nums.sort()
        cnt, n = 0, len(nums)
        for i in range(n - 2):  # ∵ rt_min = i+2 < n
            left, right = i + 1, n - 1
            while left < right:
                tmp_sum = nums[i] + nums[left] + nums[right]
                if tmp_sum >= target:
                    right -= 1
                else:
                    cnt += right - left
                    left += 1
        return cnt

# leetcode submit region end(Prohibit modification and deletion)
