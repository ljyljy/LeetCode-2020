# 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
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
#  返回：
#
#  [3,9,20,15,7]
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
#  👍 33 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for a binary tree node.
from typing import List


class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution:
    # 法1：简洁版(简化，由于不分行，故无需使用level队列)
    def levelOrder0(self, root: TreeNode) -> List[int]:
        from collections import deque
        if not root: return []
        queue, rst = deque([root]), []
        while queue:
            cur = queue.popleft()
            rst.append(cur.val)
            if cur.left: queue.append(cur.left)
            if cur.right: queue.append(cur.right)
        return rst

    # 法2：层序遍历模板[修改版]: 带level队列的通用模板[改]
    # 由于无需分行，故模板中使用res.extend, 否则使用res.append
    def levelOrder(self, root: TreeNode) -> List[int]:
        from collections import deque
        if not root: return []
        queue, rst = deque([root]), []
        while queue:
            level = []
            for _ in range(len(queue)):
                node = queue.popleft()
                level.append(node.val)
                if node.left: queue.append(node.left)
                if node.right: queue.append(node.right)
            rst.extend(level)
        return rst

# leetcode submit region end(Prohibit modification and deletion)
