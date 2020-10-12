# 	Q9_248. 统计比给定整数小的数的个数
# 给定一个整数数组（下标由 0 到 n-1）以及一个 查询列表。对于每一个查询，将会给你一个整数，请你返回该数组中小于给定整数的元素的数量。
# 说明：可否用三种方法完成以上题目。
# ①仅用循环方法   ②分类搜索 和 二进制搜索   ③构建 线段树 和 搜索
# 样例1：  输入: array =[1,2,7,8,5] queries =[1,8,5]     输出:[0,4,2]
# 样例 2:  输入: array =[3,4,5,8]    queries =[2,4]       输出:[0,1]
# 相关题目：
# 548. 两数组的交集 II547. 两数组的交集249. 统计前面比自己小的数的个数

class Solution:
    # 法0：线段树 -- 转化为‘区间求和’
    class SegmentTree(object):
        def __init__(self, start, end, count):
            self.start, self.end, self.count = start, end, count
            self.left, self.right = None, None

        @classmethod
        def build(cls, start, end):
            if start > end: return None
            if start == end: return Solution.SegmentTree(start, end, 0)
            root = Solution.SegmentTree(start, end, 0)
            mid = start + end >> 1
            root.left = cls.build(start, mid)
            root.right = cls.build(mid + 1, end)
            return root

        @classmethod
        def modify(cls, root, idx, val=1):
            if root.start == idx and root.end == idx:
                root.count += val
                return
            # query 【或idx <= root.left.end】
            mid = root.start + root.end >> 1
            if idx <= mid: cls.modify(root.left, idx, val)
            if mid < idx: cls.modify(root.right, idx, val)
            root.count = root.left.count + root.right.count

        @classmethod
        def query(cls, root, start, end):
            if start > end or end <= 0: return 0
            if start == root.start and end == root.end:
                return root.count
            mid = root.start + root.end >> 1
            if end <= mid: return cls.query(root.left, start, end)
            if mid < start: return cls.query(root.right, start, end)
            return cls.query(root.left, start, mid) + \
                   cls.query(root.right, mid + 1, end)

    def countOfSmallerNumber(self, A, queries):
        # 1. 构造segmentTree
        segTree = self.SegmentTree
        root = segTree.build(0, 10000)
        rst = []
        # 2. 转化为“区间求和” -- 将A中各数的cnt修改为1，每个搜索区间即为[0, queries[i]-1]
        for idx in A:
            segTree.modify(root, idx, 1)
        for q in queries:
            rst.append(segTree.query(root, 0, q - 1))
        return rst

    # 法1：二分搜索
    def countOfSmallerNumber1(self, A, queries):
        A = sorted(A)
        rst = []
        for q in queries:
            rst.append(self.countSmaller1(A, q))
        return rst

    def countSmaller1(self, A, q):
        if not len(A) or A[-1] < q:
            return len(A)
        start, end = 0, len(A) - 1
        while start + 1 < end:
            mid = start + end >> 1
            if A[mid] < q:
                start = mid
            else:
                end = mid  # A[mid] >= q时
        # 若边界处不满足比q小，则个数==边界下标
        if A[start] >= q: return start
        if A[end] >= q: return end
        # 否则，边界处也比q小，则个数==end+1
        return end + 1
