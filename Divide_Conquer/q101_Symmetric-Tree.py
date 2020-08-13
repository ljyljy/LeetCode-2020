# 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
#
#  例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
#
#  1
#  / \
#  2 2
#  / \ / \
# 3 4 4 3
# 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
#
#  1
#  / \
#  2 2
#  \ \
#  3 3
#
#
#
#  示例 1：
#
#  输入：root = [1,2,2,3,4,4,3]
# 输出：true
#
#
#  示例 2：
#
#  输入：root = [1,2,2,null,3,null,3]
# 输出：false
#
#
#
#  限制：
#
#  0 <= 节点个数 <= 1000
#
#  注意：本题与主站 101 题相同：https://leetcode-cn.com/problems/symmetric-tree/
#  Related Topics 树
#  👍 65 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution:
    # 1.递归
    def isSymmetric1(self, root: TreeNode) -> bool:
        if not root: return True
        return self.dfs(root.left, root.right)

    def dfs(self, p: TreeNode, q: TreeNode):
        if not p or not q: return not p and not q
        if p.val != q.val: return False
        return self.dfs(p.left, q.right) and self.dfs(p.right, q.left)

    # 2.迭代
    def isSymmetric(self, root: TreeNode) -> bool:
        from collections import deque
        if not root: return True
        queue = deque([root.left, root.right])
        while queue:
            lchild = queue.popleft()
            rchild = queue.popleft()
            # 俩节点都空【continue】，两者有一个为空or不相等则false
            if not lchild and not rchild:
                # ↓ 只有queue空才能返回True，中途只能返回False！
                continue  # return True错！
            elif not (lchild and rchild):  # only一边空
                return False
            if lchild.val != rchild.val:
                return False
            # 入列：lchild左孩子，rchild右孩子
            queue.append(lchild.left)
            queue.append(rchild.right)
            # 入列：lchild右孩子，rchild左孩子
            queue.append(lchild.right)
            queue.append(rchild.left)
        return True

# leetcode submit region end(Prohibit modification and deletion)
