# 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
#
#
#
#  示例:
# 给定如下二叉树，以及目标和 cnt = 22，
#
#                5
#              / \
#             4   8
#            /   / \
#           11  13  4
#          /  \    / \
#         7    2  5   1
#
#
#  返回:
#
#  [
#    [5,4,11,2],
#    [5,8,4,5]
# ]
#
#
#
#
#  提示：
#
#
#  节点总数 <= 10000
#
#
#  注意：本题与主站 113 题相同：https://leetcode-cn.com/problems/path-sum-ii/
#  Related Topics 树 深度优先搜索
#  👍 70 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution:
    # 法1：加法：先处理自己，下探的同时处理下一层
    def pathSum1(self, root: TreeNode, sum: int) -> List[List[int]]:
        if not root: return []
        rst = []
        self.dfs1(root, 0 + root.val, sum, [root.val], rst)
        return rst

    def dfs1(self, root: TreeNode, cur_sum, sum, path, rst):
        if not root: return
        if not root.left and not root.right and cur_sum == sum:
            rst.append(list(path))
            return
        if root.left:
            self.dfs1(root.left, cur_sum + root.left.val, sum, path + [root.left.val], rst)
        if root.right:
            self.dfs1(root.right, cur_sum + root.right.val, sum, path + [root.right.val], rst)

    # 法2：减法：先处理自己，下探的同时处理下一层
    def pathSum(self, root: TreeNode, sum: int) -> List[List[int]]:
        if not root: return []
        rst = []
        self.dfs2(root, sum - root.val, [root.val], rst)
        return rst

    def dfs2(self, root: TreeNode, sum, path, rst):
        if not root: return
        if not root.left and not root.right and sum == 0:
            rst.append(list(path))  # ❤下探的同时已经处理过下探层
            return  # ↑ path是本层路径，包括自己 ↓
        if root.left:
            self.dfs2(root.left, sum - root.left.val, path + [root.left.val], rst)
        if root.right:
            self.dfs2(root.right, sum - root.right.val, path + [root.right.val], rst)

    # 法3：减法：下探的同时，处理的是本层的sum与path，故:
    # 叶子节点处得到的path是其祖宗path(不包括自己)，所以还需要加上自己
    def pathSum3(self, root: TreeNode, sum: int) -> List[List[int]]:
        if not root: return []
        rst = []
        self.dfs3(root, sum, [], rst)
        return rst

    def dfs3(self, root: TreeNode, sum, path, rst):
        if not root: return
        if not root.left and not root.right and sum == root.val:
            rst.append(list(path + [root.val]))  # ❤还需加上叶子自己
            return  # ↑ path是上层路径，不包括自己 ↓
        self.dfs3(root.left, sum - root.val, path + [root.val], rst)
        self.dfs3(root.right, sum - root.val, path + [root.val], rst)

# leetcode submit region end(Prohibit modification and deletion)
