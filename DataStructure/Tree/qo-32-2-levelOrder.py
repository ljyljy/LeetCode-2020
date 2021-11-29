# 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
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
#   [9,20],
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
#
#  注意：本题与主站 102 题相同：https://leetcode-cn.com/problems/binary-tree-level-order-tra
# versal/
#  Related Topics 树 广度优先搜索
#  👍 42 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for a binary tree node.
from typing import List
from collections import deque


class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution:
    # 法1：层序遍历模板: 带level队列的通用模板【分行：用rst.append】
    def levelOrder(self, root: TreeNode) -> List[List[int]]:
        if not root: return []
        queue, rst = deque([root]), []
        while queue:
            level = []  # 每一层清空
            # java中的for每次queue.size会变！应该提前固定记录下来！
            # py中的len(queue)不会根据for内的queue变化而动态改变，故无需提前记录
            for _ in range(len(queue)):
                node = queue.popleft()  # poll root，下面offer左右孩子【全新的queue & level】
                level.append(node.val)
                if node.left: queue.append(node.left)
                if node.right: queue.append(node.right)
            rst.append(level)  # 若不分行，则使用rst.extend
        return rst

    # 法2：层次遍历2：Trick【仅使用一个queue，在每层后加入nullptr】
    def levelOrder2(self, root: TreeNode) -> List[List[int]]:
        if not root: return []
        level, rst, queue = [], [], deque([root, None])
        while queue:
            node = queue.popleft()
            # if isinstance(node, TreeNode): print(node.val)
            if not node:  # 一层遍历结束
                if not level: break  # “断子绝孙”：没有下一层了
                rst.append(level.copy())
                level = []  # 勿漏！！！因为是引用，不可clear--除非rst.append(level.copy())
                queue.append(None)
                continue
            level.append(node.val)
            if node.left: queue.append(node.left)
            if node.right: queue.append(node.right)
        return rst

    # 层次遍历法3：【常规】
    def levelOrder3(self, root: TreeNode) -> List[List[int]]:
        def getLevel(level: List[TreeNode]):
            sub_res = []
            for node in level:
                sub_res.append(node.val)
            return sub_res

        res, level = [], [root]
        if not root: return res
        res.append([root.val])  # 或：res.append(getLevel( level或[root] ))
        while 1:
            new_level = []
            for p in level:
                if p.left: new_level.append(p.left)
                if p.right: new_level.append(p.right)
            if new_level:
                res.append(getLevel(new_level))
                level = new_level
            else:
                break
        return res

    # 法4：Follow Up：能否用dfs?
    def levelOrder4(self, root: TreeNode) -> List[List[int]]:
        if not root: return []
        level, rst = [], []
        cur_level, n_levels = 0, 0
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

# leetcode submit region end(Prohibit modification and deletion)
