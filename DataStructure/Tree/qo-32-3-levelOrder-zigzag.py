# 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
#
#
#
#  例如:
# 给定二叉树: [3,9,20,null,null,15,7],
#
#      3
#    / \
#   9  20
#     /  \
#    15   7
#
#
#  返回其层次遍历结果：
#
#  [
#   [3],
#   [20,9],
#   [15,7]
# ]
#
#
#
#
#  提示：
#
#
#  节点总数 <= 1000
#
#  Related Topics 树 广度优先搜索
#  👍 38 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for a binary tree node.
from collections import deque

from typing import List


class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution:
    # 层次遍历模板[改]： bool zigzag（偶数层置True--从右到左）
    def levelOrder(self, root: TreeNode) -> List[List[int]]:
        if not root: return []
        rst, queue = [], deque([root])
        zigzag = False
        while queue:
            level = []
            for _ in range(len(queue)):
                node = queue.popleft()
                level.append(node.val)
                if node.left: queue.append(node.left)
                if node.right: queue.append(node.right)
            if zigzag:  level = level[::-1]
            zigzag = not zigzag  # 取反
            rst.append(level.copy())
        return rst
# leetcode submit region end(Prohibit modification and deletion)
