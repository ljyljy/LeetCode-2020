# 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
#
#  B是A的子结构， 即 A中有出现和B相同的结构和节点值。
#
#  例如:
# 给定的树 A:
#
#  3
#  / \
#  4 5
#  / \
#  1 2
# 给定的树 B：
#
#  4
#  /
#  1
# 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
#
#  示例 1：
#
#  输入：A = [1,2,3], B = [3,1]
# 输出：false
#
#
#  示例 2：
#
#  输入：A = [3,4,5,1,2], B = [4,1]
# 输出：true
#
#  限制：
#
#  0 <= 节点个数 <= 10000
#  Related Topics 树
#  👍 93 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution:
    def isSubStructure(self, A: TreeNode, B: TreeNode) -> bool:
        if not A or not B: return False
        if self._divideconq(A, B): return True
        # 递归查找是否有子结构：B指针不动【or】
        return self.isSubStructure(A.left, B) or \
               self.isSubStructure(A.right, B)

    def _divideconq(self, A: TreeNode, B: TreeNode) -> bool:
        if not B: return True
        if not A or A.val != B.val: return False
        # 递归查找子结构内部每个节点是否相同 -- A、B同步移动【and】
        return self._divideconq(A.left, B.left) and \
               self._divideconq(A.right, B.right)
# leetcode submit region end(Prohibit modification and deletion)
