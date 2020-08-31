# 	Q9_839. 合并两个排序的间隔列表
# 合并两个已排序的区间列表，并将其作为一个新的有序区间列表返回。新的区间列表应该通过拼接两个列表的区间并按升序排序。
# 同一个列表中的区间一定不会重叠。 不同列表中的区间可能会重叠。
# 挑战：O(n log n) 的时间和 O(1) 的额外空间。
# 例1：
# 输入: [(1,2),(3,4)] and list2 = [(2,3),(5,6)]
# 输出: [(1,4),(5,6)]
# 解释:
# (1,2),(2,3),(3,4) --> (1,4)
# (5,6) --> (5,6)
# 例2:
# 输入: [(1,2),(3,4)] 和 list2 = [(4,5),(6,7)]
# 输出: [(1,2),(3,5),(6,7)]
# 解释:
# (1,2) --> (1,2)
# (3,4),(4,5) --> (3,5)
# (6,7) --> (6,7)。
from typing import List


class Interval(object):
    def __init__(self, start, end):
        self.start = start
        self.end = end


class Solution:
    def mergeTwoInterval(self, list1: List[Interval], list2: List[Interval]):
        i, j = 0, 0
        rst = []
        while i < len(list1) and j < len(list2):
            if list1[i].start < list2[j].start:
                self._push_merge(rst, list1[i])
                i += 1
            else:
                self._push_merge(rst, list2[j])
                j += 1
        while i < len(list1):
            self._push_merge(rst, list1[i])
            i += 1
        while j < len(list2):
            self._push_merge(rst, list2[j])
            j += 1
        return rst

    def _push_merge(self, rst, interval):
        if not rst:
            rst.append(interval)
            return
        last = rst[-1]
        if last.end < interval.start:  # 无交集
            rst.append(interval)
            return
        rst[-1].end = max(rst[-1].end, interval.end)
