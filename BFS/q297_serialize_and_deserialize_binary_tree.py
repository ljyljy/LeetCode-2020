# 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方
# 式重构得到原数据。
#
#  请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串
# 反序列化为原始的树结构。
#
#  示例:
#
#  你可以将以下二叉树：
#
#     1
#    / \
#   2   3
#      / \
#     4   5
#
# 序列化为 "[1,2,3,null,null,4,5]"
#
#  提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这
# 个问题。
#
#  说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
#  Related Topics 树 设计


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None
from collections import deque

from Divide_Conquer.q1120_subtree_with_maximum_average import TreeNode


class Codec:

    def serialize(self, root):
        if not root: return ""
        queue, rst = deque([root]), []
        while queue:
            # level = []
            # for _ in range(len(queue)):
            #     node = queue.popleft()
            #     level.append(str(node.val) if node else '#')
            #     if node:
            #         queue.append(node.left)
            #         queue.append(node.right)
            # rst.append(level)
            node = queue.popleft()
            rst.append(str(node.val) if node else '#')
            if node:
                queue.append(node.left)
                queue.append(node.right)
        return ' '.join(rst)  # 将rst转为str返回, 必须是' ',不可以是''(这属于None！)

    def deserialize(self, data):
        if not data: return None
        bfs_order = [TreeNode(int(node_val)) if node_val != '#' else None
                     for node_val in data.split()]  # List[TreeNode]。 val为'#'时用None占位
        root = bfs_order[0]
        nodes, slow, fast = [root], 0, 1
        while slow < len(nodes):
            node = nodes[slow]
            slow += 1
            node.left = bfs_order[fast]
            node.right = bfs_order[fast + 1]
            fast += 2
            if node.left:
                nodes.append(node.left)
            if node.right:
                nodes.append(node.right)
        return root

# Your Codec object will be instantiated and called as such:
# codec = Codec()
# codec.deserialize(codec.serialize(root))
# leetcode submit region end(Prohibit modification and deletion)
