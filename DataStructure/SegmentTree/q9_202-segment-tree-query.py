# 	Q202. 线段树的查询 - segment-tree-query
# 对于一个有n个数的整数数组，在对应的线段树中, 根节点所代表的区间为0-n-1, 每个节点有一个额外的属性max，值为该节点所代表的数组区间start到end内的最大值。
# 为SegmentTree设计一个 query 的方法，接受3个参数root, start和end，线段树root所代表的数组中子区间[start, end]内的最大值。
# 说明: 线段树(又称区间树), 是一种高级数据结构，他可以支持这样的一些操作:
# 查找给定的点包含在了哪些区间内
# 查找给定的区间包含了哪些点
# 见百科：线段树、区间树
# 样例 1:
# 输入："[0,3,max=4][0,1,max=4][2,3,max=3][0,0,max=1][1,1,max=4]
# [2,2,max=2][3,3,max=3]",1,2
# 输出：4
# 解释：对于数组 [1, 4, 2, 3], 对应的线段树为 :
# 	                  [0, 3, max=4]
# 	                 /             \
# 	          [0,1,max=4]        [2,3,max=3]
# 	          /         \        /         \
# 	   [0,0,max=1] [1,1,max=4] [2,2,max=2], [3,3,max=3]
# [1,2]区间最大值为4
# 样例 2:
# 输入："[0,3,max=4][0,1,max=4][2,3,max=3][0,0,max=1][1,1,max=4][2,2,max=2][3,3,max=3]",2,3
# 输出：3
# 解释： 对于数组 [1, 4, 2, 3], 对应的线段树为 :
# 	                  [0, 3, max=4]
# 	                 /             \
# 	          [0,1,max=4]        [2,3,max=3]
# 	          /         \        /         \
# 	   [0,0,max=1] [1,1,max=4] [2,2,max=2], [3,3,max=3]
# [2,3]区间最大值为3
# 相关题目:  833. 进程序列751. 约翰的生意247. 线段树查询 II203. 线段树的修改

import sys


class SegmentTreeNode:
    def __init__(self, start, end, max):
        self.start, self.end = start, end
        self.max = max
        self.left, self.right = None, None


class Solution:
    def query2(cls, root, start, end):
        if root.start > end or root.end < start:
            return 0
        if start <= root.start and root.end <= end:
            return root.max
        return cls.query(root.left, start, end) + \
               cls.query(root.right, start, end)

    def query(self, root, start, end):
        if start <= root.start and root.end <= end:
            return root.max
        overlap = lambda node: max(start, node.start) <= min(end, node.end)
        return max(self.query(node, start, end) for node in (root.left, root.right)
                   if node and overlap(node))

    # def query1(self, root, start, end):
    #     # // 如果查询区间在当前节点的区间之内, 直接输出结果
    #     if start <= root.start and root.end <= end:
    #         return root.max
    #     mid = root.start + root.end >> 1
    #     ans = -sys.maxsize
    #     if start <= mid:
    #         ans = max(ans, self.query1(root.left, start, end))
    #     if mid + 1 <= end:
    #         ans = max(ans, self.query1(root.right, start, end))
    #     return ans

    def query2(self, root, start, end):
        if start == root.start and end == root.end:
            return root.max
        mid = root.start + root.end >> 1
        max_left = -sys.maxsize
        max_right = -sys.maxsize
        if start <= mid:
            if mid < end:
                max_left = self.query(root.left, start, mid)
            else:
                max_left = self.query(root.left, start, end)
        if mid < end:
            if start <= mid:
                max_right = self.query(root.right, mid + 1, end)
            else:
                max_right = self.query(root.right, start, end)
        return max(max_right, max_left)
