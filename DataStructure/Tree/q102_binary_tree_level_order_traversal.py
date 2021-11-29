from collections import deque
from typing import List

from Divide_Conquer.q1120_subtree_with_maximum_average import TreeNode


# 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
#
#
#
#  示例：
# 二叉树：[3,9,20,null,null,15,7],
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
#   [9,20],
#   [15,7]
# ]
#
#  Related Topics 树 广度优先搜索


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:

    def levelOrder(self, root: TreeNode) -> List[List[int]]:
        if not root: return []
        queue, rst = deque([root]), []
        while queue:
            level = []
            # java中的for每次queue.size会变！应该提前固定记录下来！
            # py中的len(queue)不会根据for内的queue变化而动态改变，故无需提前记录
            for _ in range(len(queue)):
                node = queue.popleft()
                level.append(node.val)
                if node.left:
                    queue.append(node.left)
                if node.right:
                    queue.append(node.right)
            rst.append(level)
        return rst

    # Follow Up：能否用dfs?
    def levelOrder1(self, root: TreeNode) -> List[List[int]]:
        if not root: return []
        rst, n_levels = [], 0
        while True:
            level = []
            self._dfs(root, level, 0, n_levels)
            if not level: break
            rst.append(level)
            n_levels += 1
        return rst

    def _dfs(self, root, level, cur_level, n_levels):
        if not root or cur_level > n_levels:
            return
        if cur_level == n_levels:
            level.append(root.val)
        self._dfs(root.left, level, cur_level + 1, n_levels)
        self._dfs(root.right, level, cur_level + 1, n_levels)

    # 法3：用两个队列实现层次遍历
    def levelOrder3(self, root):
        if not root: return []
        queue = [root]
        results = []
        while queue:
            next_queue = []
            results.append([node.val for node in queue])
            for node in queue:
                if node.left:
                    next_queue.append(node.left)
                if node.right:
                    next_queue.append(node.right)
            queue = next_queue
        return results
# leetcode submit region end(Prohibit modification and deletion)
