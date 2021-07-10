# 给定一个二叉树，判断它是否是高度平衡的二叉树。
#
#  本题中，一棵高度平衡二叉树定义为：
#
#
#  一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
#
#
#  示例 1:
#
#  给定二叉树 [3,9,20,null,null,15,7]
#
#      3
#    / \
#   9  20
#     /  \
#    15   7
#
#  返回 true 。
#
# 示例 2:
#
#  给定二叉树 [1,2,2,3,3,null,null,4,4]
#
#         1
#       / \
#      2   2
#     / \
#    3   3
#   / \
#  4   4
#
#
#  返回 false 。
#
#
#  Related Topics 树 深度优先搜索
#  👍 462 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution:
    ans = True

    def isBalanced(self, root: TreeNode) -> bool:
        def dfs(root):
            if not root: return 0
            l, r = dfs(root.left), dfs(root.right)
            if abs(l - r) > 1:  # 子树深度差
                self.ans = False  # 非平衡二叉树
            return max(l, r) + 1  # 求树深

        if not root: return True
        dfs(root)
        return self.ans
# leetcode submit region end(Prohibit modification and deletion)
