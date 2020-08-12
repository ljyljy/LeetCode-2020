# 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
#
#  例如输入：
#
#  4
#  / \
#  2 7
#  / \ / \
# 1 3 6 9
# 镜像输出：
#
#  4
#  / \
#  7 2
#  / \ / \
# 9 6 3 1
#
#
#
#  示例 1：
#
#  输入：root = [4,2,7,1,3,6,9]
# 输出：[4,7,2,9,6,3,1]
#
#
#
#
#  限制：
#
#  0 <= 节点个数 <= 1000
#
#  注意：本题与主站 226 题相同：https://leetcode-cn.com/problems/invert-binary-tree/
#  Related Topics 树
#  👍 49 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution:
    def mirrorTree(self, root: TreeNode) -> TreeNode:
        if not root: return None
        right = root.right
        # 若用root.right直接接收，需要暂存改变前的root.right
        root.right = self.mirrorTree(root.left)
        root.left = self.mirrorTree(right)
        return root

    def mirrorTree2(self, root: TreeNode) -> TreeNode:
        if not root: return None
        self.mirrorTree(root.left)  # 不可用root.right直接接收(因为root.左/右被修改了)
        self.mirrorTree(root.right)
        root.left, root.right = root.right, root.left
        return root

# leetcode submit region end(Prohibit modification and deletion)
