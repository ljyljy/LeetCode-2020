# 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
#
#
#
#  为了让您更好地理解问题，以下面的二叉搜索树为例：
#
#
#
#
#
#
#
#  我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是
# 第一个节点。
#
#  下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。
#
#
#
#
#
#
#
#  特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。
#
#
#
#  注意：本题与主站 426 题相同：https://leetcode-cn.com/problems/convert-binary-search-tree-
# to-sorted-doubly-linked-list/
#
#  注意：此题对比原题有改动。
#  Related Topics 分治算法
#  👍 92 👎 0


# leetcode submit region begin(Prohibit modification and deletion)

# Definition for a Node.
class Node:
    def __init__(self, val, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def treeToDoublyList(self, root: 'Node') -> 'Node':
        if not root: return None
        # 返回root的最左与最右结点
        head, tail = self._dfs(root)
        head.left = tail  # 要求首尾相连
        tail.right = head
        return head

    def _dfs(self, root):
        if not root.left and not root.right:
            return root, root  # 若为叶子，则最左最右都是自己
        if root.left and root.right:
            # 左/右子树的最左[0] & 最右[1]结点
            pair_L = self._dfs(root.left)  # 左子树L
            pair_R = self._dfs(root.right)  # 右子树R
            # 将L.最右 <-> root <-> R.最左 串接为链表
            pair_L[1].right = root
            root.left = pair_L[1]
            root.right = pair_R[0]
            pair_R[0].left = root
            # 返回L.最左 & R.最右
            return pair_L[0], pair_R[1]
        if root.left:  # 只有左子树
            pair_L = self._dfs(root.left)  # 左子树L
            pair_L[1].right = root
            root.left = pair_L[1]
            # 返回L.最左 & 最右结点即root(∵没有右子树)
            return pair_L[0], root
        if root.right:
            pair_R = self._dfs(root.right)  # 右子树R
            root.right = pair_R[0]
            pair_R[0].left = root
            # 返回 最左结点即root & R.最右
            return root, pair_R[1]

# leetcode submit region end(Prohibit modification and deletion)
