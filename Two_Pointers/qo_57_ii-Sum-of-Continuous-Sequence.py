# 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
#
#  序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
#
#
#
#  示例 1：
#
#  输入：target = 9
# 输出：[[2,3,4],[4,5]]
#
#
#  示例 2：
#
#  输入：target = 15
# 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
#
#
#
#
#  限制：
#
#
#  1 <= target <= 10^5
#
#
#
#  👍 148 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class Solution:
    def findContinuousSequence(self, target: int) -> List[List[int]]:
        i, j = 1, 1  # ∵正整数序列，∴滑动窗口的左右边界均从1开始；
        sum, rst = 0, []  # sum：滑动窗口内数字和
        while i <= target // 2:  # [i, j]: 递增序列，故
            if sum < target:  # 右边界右移
                sum += j
                j += 1
            elif sum > target:  # 左边界右移
                sum -= i
                i += 1
            else:  # sum == target
                sub_rst = list(range(i, j))
                rst.append(sub_rst)
                sum -= i  # 左边界右移
                i += 1
        return rst
# leetcode submit region end(Prohibit modification and deletion)
