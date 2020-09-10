# 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
#
#  示例:
#
#  输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
# 输出: [3,3,5,5,6,7]
# 解释:
#
#   滑动窗口的位置                最大值
# ---------------               -----
# [1  3  -1] -3  5  3  6  7       3
#  1 [3  -1  -3] 5  3  6  7       3
#  1  3 [-1  -3  5] 3  6  7       5
#  1  3  -1 [-3  5  3] 6  7       5
#  1  3  -1  -3 [5  3  6] 7       6
#  1  3  -1  -3  5 [3  6  7]      7
#
#
#
#  提示：
#
#  你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
#
#  注意：本题与主站 239 题相同：https://leetcode-cn.com/problems/sliding-window-maximum/
#  Related Topics 队列 Sliding Window
#  👍 109 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class Solution:
    def maxSlidingWindow(self, nums: List[int], k: int) -> List[int]:
        from collections import deque
        if not nums or k <= 0: return []
        if k == 1: return nums
        rst, deq = [], deque([])
        for i in range(len(nums)):
            # while!【维持窗口长度】移除k范围外的数 【deq储存idx】
            while (deq and deq[0] <= i - k): deq.popleft()
            # 只保留大数位于队头（小的数全都poll出去啦！）- 从-1处向前pop，避免大数遮挡
            while (deq and nums[deq[-1]] < nums[i]): deq.pop()
            deq.append(i)
            if i >= k - 1: rst.append(nums[deq[0]])
        return rst

# leetcode submit region end(Prohibit modification and deletion)
