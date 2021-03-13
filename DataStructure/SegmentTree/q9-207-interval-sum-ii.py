# 	Q207. 区间求和
# 在类的构造函数中给一个整数层叠，实现两个方法query(start, end)和modify(index, value)：
# 对于query（start，end），返回数组中下标start到end的和。
# 对于Modify（index，value），修改列表中下标为index上的数为value。
# 在做此题前，建议先完成以下三题：线段树的构造，线段树的查询，以及线段树的修改。
# 挑战： query和modify的时间复杂度需要为O（logN）。
# 样例1：
# 输入:
# [1,2,7,8,5]
# [query(0,2),modify(0,4),query(0,1),modify(2,1),query(2,4)]
# 输出: [10,6,14]
# 说明:
# 给定数组 A = [1,2,7,8,5].
# 在query(0, 2)后, 1 + 2 + 7 = 10,
# 在modify(0, 4)后, 将 A[0] 修改为 4， A = [4,2,7,8,5].
# 在query(0, 1)后, 4 + 2 = 6.
# 在modify(2, 1)后, 将 A[2] 修改为 1，A = [4,2,1,8,5].
# After query(2, 4), 1 + 8 + 5 = 14.
# 样例2
# 输入:
# [1,2,3,4,5]
# [query(0,0),query(1,2),query(3,4)]
# 输出: [1,5,9]
# 相关题目：206 ，区间求和-I 205 。区间最小数

class SegmentTree(object):
    def __init__(self, start, end, sum=0):
        self.start, self.end, self.sum = start, end, sum
        self.left, self.right = None, None


    # 助记: why 传参 arr 而非root？
    # - （1）root是返回值，需要递归建立
    # - （2）arr是为了赋值sum，叶子结点的sum即为arr[start/end]
    @classmethod
    def build(cls, start, end, a=None):
        if a is None: a = [0]
        if start > end: return None
        if start == end:
            return SegmentTree(start, end, a[start])
        node = SegmentTree(start, end, a[start])

        mid = start + end >> 1
        node.left = cls.build(start, mid, a)
        node.right = cls.build(mid + 1, end, a)
        node.sum = node.left.sum + node.right.sum
        return node

    # 助记: why 传参 root？ - 因为需要递归修改区间和sum
    @classmethod
    def modify(cls, root, idx, val):
        if not root: return None
        if root.start == root.end:
            root.sum = val
            return
        if idx <= root.left.end:
            cls.modify(root.left, idx, val)
        else:
            cls.modify(root.right, idx, val)
        root.sum = root.left.sum + root.right.sum

    # 助记: why 传参 root？ - 因为需要递归查询区间和（左右子树的sum）
    @classmethod
    def query(cls, root, start, end):
        if root.start > end or root.end < start:
            return 0
        if start <= root.start and root.end <= end:
            return root.sum
        return cls.query(root.left, start, end) + \
               cls.query(root.right, start, end)



class Solution:
    def __init__(self, A):
        self.root = SegmentTree.build(0, len(A) - 1, A)

    def query(self, start, end):
        return SegmentTree.query(self.root, start, end)

    def modify(self, index, value):
        SegmentTree.modify(self.root, index, value)
