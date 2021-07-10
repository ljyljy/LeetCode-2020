class TreeNode:
    def __init__(self, val):
        self.val = val
        self.left, self.right = None, None


class Solution:
    """
    @param root: the given BST
    @param target: the given target
    @return: the value in the BST that is closest to the target
    """

    def closestValue(self, root: TreeNode, target: int) -> int:
        lower = root  # 找到<= target
        upper = root  # 找到> target
        while root:
            if root.val < target:
                lower = root
                root = root.right
            elif root.val > target:
                upper = root
                root = root.left
            else:
                return root.val

        # 遍历结束，未找到相等的结点(否则直接返回)
        # 此时比较lower, target, upper
        if abs(target - lower.val) <= abs(upper.val - target):
            return lower.val
        return upper.val
