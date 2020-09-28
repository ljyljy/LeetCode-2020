# 对于给定数组实现build方法, 线段树的每个节点储存区间最大值, 返回根节点
# 说明: 线段树(又称区间树), 是一种高级数据结构，他可以支持这样的一些操作:
# 查找给定的点包含在了哪些区间内
# 查找给定的区间包含了哪些点
# 见百科：线段树、区间树
# 样例 1:
# 输入: [3,2,1,4]
# 解释:
# 这颗线段树将会是
#           [0,3](max=4)
#           /          \
#        [0,1]         [2,3]
#       (max=3)       (max=4)
#       /   \          /    \
#    [0,0]  [1,1]    [2,2]  [3,3]
#   (max=3)(max=2)  (max=1)(max=4)

class SegmentTreeNode:
    def __init__(self, start, end, max):
        self.start, self.end = start, end
        self.max = max
        self.left, self.right = None, None


class Solution:
    def build(self, A):
        return self.buildHelper(0, len(A) - 1, A)

    def buildHelper(self, start, end, A):
        if start > end: return None

        node = SegmentTreeNode(start, end, A[start])
        if start == end: return node

        mid = start + end >> 1
        node.left = self.buildHelper(start, mid, A)
        node.right = self.buildHelper(mid + 1, end, A)
        if node.left and node.left.max > node.max:
            node.max = node.left.max
        if node.right and node.right.max > node.max:
            node.max = node.right.max
        return node
