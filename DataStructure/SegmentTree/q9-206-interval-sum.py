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
    def build(cls, start, end, arr):
        if start > end: return None
        node = SegmentTree(start, end, arr[start])
        if start == end: return node

        mid = start + end >> 1
        node.left = cls.build(start, mid, arr)
        node.right = cls.build(mid + 1, end, arr)
        node.sum = node.left.sum + node.right.sum
        return node

    @classmethod
    def query(cls, root, start, end):
        if root.start > end or root.end < start:
            return 0
        # WHY 【包含∈】而非【=≠】:
        # 1) query查询区间范围∈±∞, 当查询区间超过arr区间时，返回0即可（合法）
        # 2) cnt = 左子树sum + 右子树sum
        if start <= root.start and root.end <= end:
            return root.sum
        return cls.query(root.left, start, end) + \
               cls.query(root.right, start, end)


class Solution:
    # 法1：线段树【不推荐】(查询区间∈[-∞, +∞])
    def intervalSum1(self, A, queries: List[Interval]):
        root = SegmentTree.build(0, len(A) - 1, A)
        rst = []
        for query in queries:
            rst.append(SegmentTree.query(root, query.start, query.end))
        return rst

    # 法2：【前缀树 推荐√】(查询区间∈[0, n-1])
    def intervalSum(self, A, queries: List[Interval]):
        if not A: return 0
        n, res = len(A), []
        prefix_sum = [0] * (n+1)
        for i in range(1, n+1):
            prefix_sum[i] = prefix_sum[i-1] + A[i-1]
        for query in queries:  # 前缀和下标 = 查询下标+1
            # 下标问题：举个例子，打个草稿
            ans = prefix_sum[query.end + 1] - prefix_sum[query.start]
            res.append(ans)
        return res






# def print_root(root: SegmentTree):
#     if root:
#         print(f'{root.start}, {root.end}, cnt={root.cnt}')

# if __name__ == "__main__":
#     arr = [1, 2, 7, 8, 5]
#     root = SegmentTree.build(0, len(arr)-1, arr)
#     while root:
#         print_root(root)
#         print_root(root.left)
#         print_root(root.right)




