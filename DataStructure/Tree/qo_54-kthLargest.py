# 给定一棵二叉搜索树，请找出其中第k大的节点。
#
#
#
#  示例 1:
#
#  输入: root = [3,1,4,null,2], k = 1
#    3
#   / \
#  1   4
#   \
#    2
# 输出: 4
#
#  示例 2:
#
#  输入: root = [5,3,6,2,4,null,null,1], k = 3
#        5
#       / \
#      3   6
#     / \
#    2   4
#   /
#  1
# 输出: 4
#
#
#
#  限制：
#
#  1 ≤ k ≤ 二叉搜索树元素个数
#  Related Topics 树
#  👍 59 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution:
    def kthLargest(self, root: TreeNode, k: int) -> int:
        if not root: return
        rst, cnt, stack = [], 1, [root]
        while stack:
            i = stack.pop()
            if isinstance(i, TreeNode):
                stack.extend([i.right, i.val, i.left])
            elif isinstance(i, int):
                rst.append(i)
                cnt += 1
                # if cnt == k: return i
        return rst[-k]  # BST的中序遍历：升序
# leetcode submit region end(Prohibit modification and deletion)
