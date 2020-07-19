class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class BSTIterator:
    def __init__(self, root: TreeNode):
        self.stack = []
        self._leftmost_inorder(root)

    def _leftmost_inorder(self, root):
        while root:
            self.stack.append(root)
            root = root.left

    def next(self) -> int:
        topNode = self.stack.pop()  # 下一个最小结点
        if topNode.right:
            self._leftmost_inorder(topNode.right)
        return topNode.val

    def hasNext(self) -> bool:
        return bool(len(self.stack))


# (2)写法2：与法1相同。
class BSTIterator2:
    def __init__(self, root):
        self.stack = []
        while root:
            self.stack.append(root)
            root = root.left

    def next(self) -> int:
        topNode = self.stack.pop()
        node = topNode.right
        while node:
            self.stack.append(node)
            node = node.left
        return topNode.val

    def hasNext(self):
        return len(self.stack) > 0
