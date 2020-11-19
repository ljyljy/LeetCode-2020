# 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
#
#  求在该柱状图中，能够勾勒出来的矩形的最大面积。
#
#
#
#
#
#  以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
#
#
#
#
#
#  图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
#
#
#
#  示例:
#
#  输入: [2,1,5,6,2,3]
# 输出: 10
#  Related Topics 栈 数组
#  👍 1018 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class Solution:
    def largestRectangleArea(self, heights: List[int]) -> int:
        idx_stack, area = [], 0
        for i, cur_h in enumerate(heights + [0]):  # ❤ 0为右哨兵！
            #  对栈中柱体来说，栈中的下一个柱体就是其「右边第一个小于自身的柱体」；
            #  若当前柱体 i 的高度【小于】栈顶柱体的高度，说明 i 是栈顶柱体的「右边第一小的边界」。
            #  因此以栈顶柱体为高的矩形的【左右宽度边界】就确定了，可以计算面积🌶️ ～
            while idx_stack and cur_h <= heights[idx_stack[-1]]:  # 找到右边界cur_h: 与42.接雨水恰好相反
                left_high_idx = idx_stack.pop()  # 最高点（∵左右边界均已找到，故pop）
                left_low_idx = idx_stack[-1] if idx_stack else -1  # 最高点的左边界，(❤不pop ∵尚未计算其左右边界)
                width = i - left_low_idx - 1  # i是最高点的右边界
                area = max(area, width * heights[left_high_idx])

            idx_stack.append(i)  # 说明'前栈顶'是i的左边界，将i压栈，继续搜索i的右边界
        return area

# leetcode submit region end(Prohibit modification and deletion)
