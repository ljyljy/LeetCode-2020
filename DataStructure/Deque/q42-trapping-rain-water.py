# 	Q363. 接雨水
# 给出 n 个非负整数，代表一张X轴上每个区域宽度为 1 的海拔图, 计算这个海拔图最多能接住多少（面积）雨水。
# 说明： 如果两个单词有相同的使用频率, 按字典序排名.。
# 示例 1：
# 输入: [0,1,0]
# 输出: 0
# 样例 2:
# 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
# 输出: 6
# 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
# 挑战
# O(n) 时间, O(1) 空间/ O(n) 空间也可以接受
# 相关题目  1310. 数组除了自身的乘积364. 接雨水 II383. 装最多水的容器
# 思路：每个位置上的盛水数目 = min(左侧最高，右侧最高) - 当前高度
# 从左到右扫描一边数组，获得每个位置往左这一段的最大值，再从右到左扫描一次获得每个位置向右的最大值。 然后最后再扫描一次数组，计算每个位置上的盛水数目。
# 时间复杂度 O(n)，空间复杂度 O(n)

import sys
from typing import List


class Solution:
    # 法1：列遍历，双指针 -- 时间/空间：O(n)
    def trap1(self, heights: List[int]) -> int:
        if not heights: return 0
        water, n = 0, len(heights)
        left_max_list, cur_max = [], -sys.maxsize
        for height in heights:
            cur_max = max(cur_max, height)
            left_max_list.append(cur_max)

        right_max_list, cur_max = [], -sys.maxsize
        for height in reversed(heights):
            cur_max = max(cur_max, height)
            right_max_list.append(cur_max)
        right_max_list = right_max_list[::-1]

        for i in range(n):
            water += (min(left_max_list[i], right_max_list[i]) - heights[i])
        return water

    # 法2：双向指针 -- 时间O(n), 空间O(1)
    def trap2(self, heights: List[int]) -> int:
        if not heights: return 0
        left, right = 0, len(heights) - 1
        left_max, right_max = heights[left], heights[right]
        water = 0
        while left <= right:
            if left_max < right_max:
                left_max = max(left_max, heights[left])
                water += left_max - heights[left]  # >= 0 (等于0时，左侧比当前左指针矮，无法盛水)
                left += 1  # 当前左指针处，可盛水的max已确定，继续左移计算next
            else:
                right_max = max(right_max, heights[right])
                water += right_max - heights[right]  # 当前右指针列 计算完毕
                right -= 1
        return water

    # 法3 – 单调栈：时间复杂度：O(n)，空间复杂度 O(n)
    # ❤ 和双指针法不同，单调栈不需要全局信息，输入是数据流的情况也可以处理。缺点是最坏O(N)空间。但你要是只会双指针，面试官测你数据流，你就抓瞎了。
    def trap(self, heights):
        stack, water = [], 0
        for i, cur_h in enumerate(heights):
            while stack and cur_h > heights[stack[-1]]:  # cur_h > 栈顶h，可以接水！
                left_min1_h = heights[stack.pop()]  # 左侧/栈顶h比cur_h矮。将被'次栈顶' & cur_h淹没的'当前栈顶'pop，后面用不到了（此后可能会用到次栈顶）
                if not stack: continue  # ❤ 不可少！
                left_min2_idx = stack[-1]
                min_h = min(heights[left_min2_idx], cur_h)  # ‘(原)次栈顶’ vs 当前高度
                width, h = i - left_min2_idx - 1, min_h - left_min1_h
                water += h * width
            stack.append(i)  # stack为空 || cur_h < 栈顶h，无法接水，暂时进栈
        return water

