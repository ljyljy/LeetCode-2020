# Follow Up IV. k 路归并算法（外排序算法 External Sorting）:
# 	Q9_577. 合并K个排序间隔列表 · Merge K Sorted Interval Lists
# 将K个排序的间隔列表合并到一个排序的间隔列表中，你需要合并重叠的间隔。
# 挑战：O(n log k) 的时间完成：N 是所有数组包含的整数总数量；k 是数组的个数。
# 例1：输入: [
#   [(1,3),(4,7),(6,8)],
#   [(1,2),(9,10)]
# ]
# 输出: [(1,3),(4,8),(9,10)]
#
# 类似的题解：
# （1）Follow Up III. k 路归并算法（外排序算法 External Sorting）:
# 	Q9_486. 合并k个排序数组 · Merge K Sorted Arrays
# （2）Follow Up II. 归并 2 个有序的区间序列:
# 	Q9_839. 合并两个排序的间隔列表

from typing import List


class Interval(object):
    def __init__(self, start, end):
        self.start = start
        self.end = end


class Solution:
    # 法1：优先堆 Heap
    def mergeKSortedIntervalLists(self, intervals: List[List[Interval]]) -> List[Interval]:
        import heapq
        # 初始化 优先堆，包括四个值：数字大小(start & end)，在哪个数组里x，数字在数组的哪个位置y
        rst, heap = [], []
        for x, iv_list in enumerate(intervals):
            if not iv_list: continue
            heapq.heappush(heap, (iv_list[0].start, iv_list[0].end, x, 0))  # y: 0
        while heap:
            start, end, x, y = heapq.heappop(heap)  # x:第几个list，y:该list内索引
            self._push_and_merge(rst, Interval(start, end))
            if y < len(intervals[x]) - 1:  # 不是最后一个
                next_iv = intervals[x][y + 1]
                heapq.heappush(heap, (next_iv.start, next_iv.end, x, y + 1))
        return rst

    def _push_and_merge(self, rst: List[Interval], iv: Interval):
        if not rst:
            rst.append(iv)
            return
        last_iv = rst[-1]
        if last_iv.end < iv.start:  # 没有交集
            rst.append(iv)
            return
        last_iv.end = max(last_iv.end, iv.end)  # 直接对rst内部做修改

    # 法2：二分归并
    def mergeKSortedIntervalLists2(self, intervals):
        return self.mergeKSortedIntervalListsHelper(0, len(intervals) - 1, intervals)

    def mergeKSortedIntervalListsHelper(self, start, end, intervals):
        if start >= end: return intervals[start]

        mid = start + end >> 1
        left = self.mergeKSortedIntervalListsHelper(start, mid, intervals)
        right = self.mergeKSortedIntervalListsHelper(mid + 1, end, intervals)
        return self.mergeTwoIntervals(left, right)

    def mergeTwoIntervals(self, list1, list2):
        i, j, results = 0, 0, []
        curt, last = None, None

        if list1 is None or list2 is None: return results

        while i < len(list1) and j < len(list2):
            if list1[i].start < list2[j].start:
                curt = list1[i]
                i += 1
            else:
                curt = list2[j]
                j += 1
            last = self.merge(curt, last, results)

        while i < len(list1):
            curt = list1[i]
            i += 1
            last = self.merge(curt, last, results)

        while j < len(list2):
            curt = list2[j]
            j += 1
            last = self.merge(curt, last, results)
        if last is not None:
            results.append(last)
        return results

    def merge(self, curt, last, results):
        if last == None: return curt
        if last.end < curt.start:
            results.append(last)
            return curt

        last.end = max(last.end, curt.end)
        return last
