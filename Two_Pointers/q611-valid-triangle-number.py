# 给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。
#
#  示例 1:
#
#
# 输入: [2,2,3,4]
# 输出: 3
# 解释:
# 有效的组合是:
# 2,3,4 (使用第一个 2)
# 2,3,4 (使用第二个 2)
# 2,2,3
#
#
#  注意:
#
#
#  数组长度不超过1000。
#  数组里整数的范围为 [0, 1000]。
#
#  Related Topics 数组
#  👍 129 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class Solution:
    def triangleNumber(self, nums: List[int]) -> int:
        ans = 0
        if not nums: return ans
        nums = sorted(nums)  # nums.sort() -- 无返回值，原地修改

        for i in range(len(nums)):
            left, right = 0, i - 1  # 数字l < r < i
            while left < right:
                # 2条短边和 > 最长边 <--> 有效△
                if nums[left] + nums[right] > nums[i]:
                    ans += right - left  # 固定right，left=[left, right)均满足
                    right -= 1
                else:
                    left += 1
        return ans
# leetcode submit region end(Prohibit modification and deletion)
