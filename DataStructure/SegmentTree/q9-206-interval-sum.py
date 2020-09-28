# 	Q9-206. 区间求和-I
# 给定一个整数数组（下标由 0 到 n-1，其中 n 表示数组的规模），以及一个查询列表。每一个查询列表有两个整数 [start, end] 。 对于每个查询，计算出数组中从下标 start 到 end 之间的数的总和，并返回在结果列表中。
# 挑战： query时间复杂度为O（logN）。
# 样例1：
# 输入: 数组 = [1,2,7,8,5], 查询 = [(0,4),(1,2),(2,4)]
# 输出: [23,9,20]
from typing import List


class Interval(object):
    def __init__(self, start, end):
        self.start = start
        self.end = end


class SegmentTree(object):
    def __init__(self, start, end, sum=0):
        self.start, self.end, self.sum = start, end, sum
        self.left, self.right = None, None

    @classmethod
    def build(cls, start, end, a):
        if start > end: return None
        if start == end:  #
            return SegmentTree(start, end, a[start])
        node = SegmentTree(start, end, a[start])
        mid = start + end >> 1

        node.left = cls.build(start, mid, a)
        node.right = cls.build(mid + 1, end, a)
        node.sum = node.left.sum + node.right.sum
        return node

    @classmethod
    def query(cls, root, start, end):
        if root.start > end or root.end < start:
            return 0
        if start <= root.start and root.end <= end:
            return root.sum
        return cls.query(root.left, start, end) + \
               cls.query(root.right, start, end)


class Solution:
    def intervalSum(self, A, queries: List[Interval]):
        root = SegmentTree.build(0, len(A) - 1, A)
        rst = []
        for query in queries:
            rst.append(SegmentTree.query(root, query.start, query.end))
        return rst
