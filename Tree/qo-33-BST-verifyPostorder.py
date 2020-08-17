# 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
#
#
#
#  参考以下这颗二叉搜索树：
#
#       5
#     / \
#    2   6
#   / \
#  1   3
#
#  示例 1：
#
#  输入: [1,6,3,2,5]
# 输出: false
#
#  示例 2：
#
#  输入: [1,3,2,6,5]
# 输出: true
#
#
#
#  提示：
#
#
#  数组长度 <= 1000
#
#  👍 93 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class Solution:
    def verifyPostorder(self, postorder: List[int]) -> bool:
        def dfs(left, right) -> bool:
            if left >= right: return True
            root = postorder[right]  # 1. 后序last一定是根
            i = left  # 2. 从根左侧找其左子树（↓ keep < rootの拐点 -- 有且只有1个！）
            while i < right and postorder[i] < root: i += 1  # 3. i-1即为左子树的root, 后可递归遍历。
            # 4. 防止存在2个拐点，否则直接False（遍历右子树, i为右子树第一个结点）
            for j in range(i, right):
                if postorder[j] < root:
                    return False
            # 5. 不存在多个拐点，则暂时ok，递归下去（左&右子树）
            return dfs(left, i - 1) and dfs(i, right - 1)  # right-1!!!否则错！

        if not postorder: return True
        return dfs(0, len(postorder) - 1)
# leetcode submit region end(Prohibit modification and deletion)
