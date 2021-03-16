# 给定一个整数数组 nums，求出数组从索引 i 到 j (i ≤ j) 范围内元素的总和，包含 i, j 两点。
#
#  update(i, val) 函数可以通过将下标为 i 的数值更新为 val，从而对数列进行修改。
#
#  示例:
#
#  Given nums = [1, 3, 5]
#
# sumRange(0, 2) -> 9
# update(1, 2)
# sumRange(0, 2) -> 8
#
#
#  说明:
#
#
#  数组仅可以在 update 函数下进行修改。
#  你可以假设 update 函数与 sumRange 函数的调用次数是均匀分布的。
#
#  Related Topics 树状数组 线段树
#  👍 170 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class SegmentTree(object):
    def __init__(self, start, end, val):
        self.start, self.end, self.val = start, end, val
        self.left, self.right = None, None

    @classmethod
    def build(cls, start, end, arr):
        if start > end: return None
        if start == end:
            return SegmentTree(start, end, arr[start])
        node = SegmentTree(start, end, arr[start])
        mid = start + end >> 1
        node.left = cls.build(start, mid, arr)
        node.right = cls.build(mid + 1, end, arr)
        node.val = node.left.val + node.right.val
        return node

    @classmethod
    def modify(cls, root, idx, val):
        if not root: return None
        if root.start == root.end:
            root.val = val
            return
        if idx <= root.left.end:
            cls.modify(root.left, idx, val)
        else:
            cls.modify(root.right, idx, val)
        root.val = root.left.val + root.right.val

    @classmethod
    def query(cls, root, start, end):
        if root.start > end or root.end < start:
            return 0
        if start <= root.start and root.end <= end:
            return root.val
        return cls.query(root.left, start, end) + \
               cls.query(root.right, start, end)  # 分治


class NumArray:

    def __init__(self, nums: List[int]):
        if not len(nums):
            return
        self.root = SegmentTree.build(0, len(nums) - 1, nums)

    def update(self, i: int, val: int) -> None:
        return SegmentTree.modify(self.root, i, val)

    def sumRange(self, i: int, j: int) -> int:
        return SegmentTree.query(self.root, i, j)

# Your NumArray object will be instantiated and called as such:
# obj = NumArray(nums)
# obj.update(i,val)
# param_2 = obj.sumRange(i,j)
# leetcode submit region end(Prohibit modification and deletion)
