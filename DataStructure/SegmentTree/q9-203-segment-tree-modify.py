class SegmentTreeNode:
    def __init__(self, start, end, max):
        self.start, self.end = start, end
        self.max = max
        self.left, self.right = None, None


class Solution:
    def modify1(self, root: SegmentTreeNode, idx, val):
        if not root: return
        if root.start == root.end:
            root.max = val
            return
        if root.left.end >= idx:
            self.modify1(root.left, idx, val)
        else:
            self.modify1(root.right, idx, val)
        root.max = max(root.left.max, root.right.max)

    def modify(self, root, index, value):
        if not root: return
        if root.left == root.right:
            root.max = value
            return
        mid = (root.start + root.end) // 2
        if index <= mid:
            self.modify(root.left, index, value)
        else:
            self.modify(root.right, index, value)
        root.max = max(root.left.max, root.right.max)
