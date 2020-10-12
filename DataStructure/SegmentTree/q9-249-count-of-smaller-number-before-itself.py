# 	Q9_249. 统计前面比自己小的数的个数
# 给定一个整数数组（下标由 0 到 n-1）。对于数组中的每个 ai 元素，请计算 ai 前的数中比它小的元素的数量。
# 说明：n 表示数组的规模，取值范围由 0 到10000
# 样例1：
# 输入: [1,2,7,8,5]
# 输出: [0,1,2,3,2]
# 样例 2:
# 输入: [7,8,2,1,3]
# 输出: [0,1,0,0,2]

from typing import List

"""
思路如下：
1.	如何找到问题的切入点，对于每一个元素A[i]我们查询比它小数，转换成区间的查询就是查询在它前面的数当中有多少在区间[0, A[i] - 1]当中。
2.	因此我们可以为0-10000区间建树，并将所有区间count设为0。每一个最小区间（即叶节点）的count代表到目前为止该数的数量。
然后开始遍历数组，遇到A[i]时，去查0 ～ A[i]-1区间的count即这个区间中有多少数存在，这就是比A[i]小的数的数量。【与248的不同->】查完后将A[i]区间的count加1（∵遇到了A[i]，cnt++），即把A[i]插入到线段树i的位置上。
3.	具体举例：A = [1,2,7,8,5], 我们得到一棵空的SegmentTree，对应的前10个位置的区间是[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
A[0] = 1, 查询区间[0, 0]的和, 得到0，然后在1的地方加1，得到[0, 1, 0, 0, 0, 0, 0, 0, 0, 0]
A[1] = 2, 查询区间[0, 1]的和, 得到1，然后在2的地方加1，得到[0, 1, 1, 0, 0, 0, 0, 0, 0, 0]
A[2] = 7, 查询区间[0, 6]的和, 得到2，然后在7的地方加1，得到[0, 1, 1, 0, 0, 0, 0, 1, 0, 0]
A[3] = 8, 查询区间[0, 7]的和, 得到3，然后在8的地方加1，得到[0, 1, 1, 0, 0, 0, 0, 1, 1, 0]
A[4] = 5, 查询区间[0, 4]的和, 得到2，然后在5的地方加1，得到[0, 1, 1, 0, 0, 1, 0, 1, 1, 0]
所以我们得到结果序列为：[0, 1, 2, 3, 2]
"""


# 属于法一(通过64%)
class SegmentTree:
    def __init__(self, start, end, count):
        self.start, self.end, self.count = start, end, count
        self.left, self.right = None, None

    @classmethod
    def build(cls, start, end):
        if start > end: return None
        if start == end: return SegmentTree(start, end, 0)
        root = SegmentTree(start, end, 0)
        mid = start + end >> 1
        root.left = cls.build(start, mid)
        root.right = cls.build(mid + 1, end)
        return root

    @classmethod
    def modify(cls, root, idx, val=1):
        if root.start == idx and root.end == idx:
            root.count += val
            return
        mid = root.start + root.end >> 1
        if idx <= mid: cls.modify(root.left, idx, val)
        if mid < idx: cls.modify(root.right, idx, val)
        root.count = root.left.count + root.right.count

    @classmethod
    def query(cls, root, start, end):
        if start > end or end <= 0: return 0
        if root.start == start and root.end == end:
            return root.count
        mid = root.start + root.end >> 1
        if end <= mid: return cls.query(root.left, start, end)
        if mid < start: return cls.query(root.right, start, end)
        return cls.query(root.left, start, mid) + \
               cls.query(root.right, mid + 1, end)


# 属于法二(通过100%)
import math


# N = 10000
# sqrtN = int(math.sqrt(N))
class Block:
    def __init__(self):
        self.total = 0
        self.counter = dict()


class BlockArray:
    def __init__(self, max_val, sqrtN):
        self.blocks = [Block() for _ in range(max_val // sqrtN + 1)]

    def cnt_smaller(self, val, sqrtN):
        cnt = 0
        block_idx = val // sqrtN
        for i in range(block_idx):
            cnt += self.blocks[i].total
        counter = self.blocks[block_idx].counter
        for val_cnter in counter:
            if val_cnter < val: cnt += counter[val_cnter]
        return cnt

    def insert(self, val, sqrtN):
        block_idx = val // sqrtN
        block = self.blocks[block_idx]
        block.total += 1
        block.counter[val] = block.counter.get(val, 0) + 1


class Solution:
    # 法一：线段树
    def countOfSmallerNumberII(self, A):
        if not A: return []
        root = SegmentTree.build(0, max(A))
        rst = []
        for idx in A:
            rst.append(SegmentTree.query(root, 0, idx - 1))
            SegmentTree.modify(root, idx, 1)
        return rst

    # 法二：使用分块检索算法，时间复杂度 O(n∗sqrt(size))。
    # 如果数值范围是 size，那么创建 sqrt(size) 个 sqrt(size)大小的区间。
    # 每个区间记录总共有多少数，和不同的数出现几次。
    def countOfSmallerNumberII_block(self, A):
        if not A: return []
        N = max(A)
        sqrtN = int(math.sqrt(N))
        rst, block_arr = [], BlockArray(N, sqrtN)
        for a in A:
            rst.append(block_arr.cnt_smaller(a, sqrtN))
            block_arr.insert(a, sqrtN)
        return rst

# class Solution:
#     # 法0：线段树 -- 转化为‘区间求和’
#     class SegmentTree(object):
#         def __init__(self, start, end, count):
#             self.start, self.end, self.count = start, end, count
#             self.left, self.right = None, None

#         @classmethod
#         def build(cls, start, end):
#             if start > end: return None
#             if start == end: return Solution.SegmentTree(start, end, 0)
#             root = Solution.SegmentTree(start, end, 0)
#             mid = start + end >> 1
#             root.left = cls.build(start, mid)
#             root.right = cls.build(mid + 1, end)
#             return root

#         @classmethod
#         def modify(cls, root, idx, val=1):
#             if root.start == idx and root.end == idx:
#                 root.count += val
#                 return
#             # query 【或idx <= root.left.end】
#             mid = root.start + root.end >> 1
#             if idx <= mid: cls.modify(root.left, idx, val)
#             if mid < idx: cls.modify(root.right, idx, val)
#             root.count = root.left.count + root.right.count

#         @classmethod
#         def query(cls, root, start, end):
#             if start > end or end <= 0: return 0
#             if start == root.start and end == root.end:
#                 return root.count
#             mid = root.start + root.end >> 1
#             if end <= mid: return cls.query(root.left, start, end)
#             if mid < start: return cls.query(root.right, start, end)
#             return cls.query(root.left, start, mid) + \
#                   cls.query(root.right, mid + 1, end)

#     def countOfSmallerNumberII(self, A):
#         if not A: return []
#         # 1. 构造segmentTree
#         segTree = self.SegmentTree
#         root = segTree.build(0, max(A))
#         rst = []
#         # 2. 转化为“区间求和” -- 将A中各数的cnt修改为1，每个搜索区间即为[0, queries[i]-1]
#         for idx in A:
#             rst.append(segTree.query(root, 0, idx - 1))
#             segTree.modify(root, idx, 1)

#         return rst
