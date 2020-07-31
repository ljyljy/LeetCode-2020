class TreeNode:
    def __init__(self, val):
        self.val = val
        self.left, self.right = None, None


class TreeNode2(object):
    def __init__(self, x):
        self.val = x
        self.left, self.right = None, None
        self.father = None


class Solution:
    def inorderSuccessor(self, root: TreeNode, p: TreeNode) -> TreeNode:
        successor = None
        while root and root.val != p.val:
            if root.val > p.val:
                successor = root
                root = root.left
            else:
                root = root.right

        if not root: return None
        if not root.right: return successor

        # 有右子树，则找右子树的最左孩子
        root = root.right
        while root.left:
            root = root.left
        return root

    # 变题：若题目不给root，只给出结点，增加结点的'父节点'属性：
    def inorderSuccessor2(self, q: TreeNode2) -> TreeNode2:
        if q.right:
            q = q.right
            while q.left:
                q = q.left
            return q
        # q无右子树，则返回某祖宗结点res，其中res的左子树中包含q（最近祖先）
        # 找出q祖宗的第一个拐点(整体作为祖宗的左子树)
        while q.father:
            if q.father.left == q:
                return q.father
            else:
                q = q.father
