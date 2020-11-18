# 中位数是有序序列最中间的那个数。如果序列的大小是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。
#
#  例如：
#
#
#  [2,3,4]，中位数是 3
#  [2,3]，中位数是 (2 + 3) / 2 = 2.5
#
#
#  给你一个数组 nums，有一个大小为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，每次窗口向右移动 1 位。你的任务是找出每次窗口移动后得到的新窗
# 口中元素的中位数，并输出由它们组成的数组。
#
#
#
#  示例：
#
#  给出 nums = [1,3,-1,-3,5,3,6,7]，以及 k = 3。
#
#  窗口位置                      中位数
# ---------------               -----
# [1  3  -1] -3  5  3  6  7       1
#  1 [3  -1  -3] 5  3  6  7      -1
#  1  3 [-1  -3  5] 3  6  7      -1
#  1  3  -1 [-3  5  3] 6  7       3
#  1  3  -1  -3 [5  3  6] 7       5
#  1  3  -1  -3  5 [3  6  7]      6
#
#
#  因此，返回该滑动窗口的中位数数组 [1,-1,-1,3,5,6]。
#
#
#
#  提示：
#
#
#  你可以假设 k 始终有效，即：k 始终小于输入的非空数组的元素个数。
#  与真实值误差在 10 ^ -5 以内的答案将被视作正确答案。
#
#  Related Topics Sliding Window
#  👍 120 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from heapq import *
import os
from typing import List


class Heap:
    def __init__(self):
        # q1存储了当前所有元素（包括未删除元素）
        # q2存储了q1中已删除的元素
        self.__q1, self.__q2 = [], []

    def push(self, x):
        heappush(self.__q1, x)

    # q2 中 push 一个元素表示在 q1 中它已经被删除了
    def remove(self, x):
        heappush(self.__q2, x)

    # 若堆顶的元素被 remove 过，那么它此时应该在 q2 中的堆顶
    # 将两个堆一起 pop ，直到堆顶元素不同或 q2 没元素
    def pop(self):
        while len(self.__q2) and self.__q1[0] == self.__q2[0]:
            heappop(self.__q1)
            heappop(self.__q2)
        if len(self.__q1): heappop(self.__q1)

    def top(self):
        while len(self.__q2) and self.__q1[0] == self.__q2[0]:
            heappop(self.__q1)
            heappop(self.__q2)
        if len(self.__q1): return self.__q1[0]

    def size(self):
        return len(self.__q1) - len(self.__q2)

    def sol(self):
        print(self.__q1)


class Solution:
    def medianSlidingWindow(self, nums: List[int], k: int) -> List[float]:
        #  <= 中     > 中
        max_heap, min_heap, rst = Heap(), Heap(), []
        for i in range(len(nums)):
            x = nums[i]
            if i == 0:  # 堆都为空，直接压入大根堆
                max_heap.push(-x)
            else:  # 根据当前值和大根堆堆顶的值判断，该压入哪个堆里
                if x <= -max_heap.top():
                    max_heap.push(-x)
                else:
                    min_heap.push(x)
            if i >= k:  # 控制滑动窗口，删除离开滑动窗口的元素
                # 根据当前要删除的值和大根堆堆顶的值判断，该从哪个堆里删除
                val2rm = nums[i - k]
                if val2rm <= -max_heap.top():
                    max_heap.remove(-val2rm)
                else:
                    min_heap.remove(val2rm)
            # 计算更新中位数 —— 大根堆的堆顶
            if i >= k - 1:  # 故其元素数量是k / 2，向上取整
                max_num = (k + 1) // 2
                while max_heap.size() != max_num:
                    if max_heap.size() > max_num:
                        x = -max_heap.top()
                        max_heap.pop()
                        min_heap.push(x)
                    else:
                        x = min_heap.top()
                        min_heap.pop()
                        max_heap.push(-x)
                # 若滑窗k为偶数，则取max & min堆顶的均值，否则取max堆顶
                if k % 2 == 0:
                    rst.append((-max_heap.top() + min_heap.top()) / 2)
                else:
                    rst.append(-max_heap.top())
        return rst
# leetcode submit region end(Prohibit modification and deletion)
