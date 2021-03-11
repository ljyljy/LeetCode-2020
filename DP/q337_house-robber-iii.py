# 	Q337.打家劫舍III
# 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
# 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
# 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
# 示例 1:
# 输入: [3,2,3,null,3,null,1]
#      3
#     / \
#    2   3
#     \   \
#      3   1
# 输出: 7
# 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
# 示例 2:
# 输入: [3,4,5,1,3,null,1]
#      3
#     / \
#    4   5
#   / \   \
#  1   3   1
# 输出: 9
# 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.

###
# https://mp.weixin.qq.com/s/z44hk0MW14_mAQd7988mfw

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

from collections import defaultdict
class Solution:
    memo = defaultdict(int)
    # 法一[常规，推荐]：时间复杂度 O(N)，N为数的节点数。
    def rob(self, root: TreeNode) -> int:
        if not root: return 0
        # 备忘录：消除重叠子问题
        if self.memo.get(root, -1) != -1:
            return self.memo.get(root, -1)
        # 抢，然后去下下家（孙子：左右子树的子树）
        do_it = root.val + \
                (0 if not root.left else self.rob(root.left.left) + self.rob(root.left.right) ) + \
                (0 if not root.right else self.rob(root.right.left) + self.rob(root.right.right))
        # 不抢，直接去下家（儿子：左右子树）
        not_do = 0 + self.rob(root.left) + self.rob(root.right)
        res = max(do_it, not_do)
        self.memo[root] = res
        return res

    # 法二
    def rob2(self, root: TreeNode) -> int:
        if not root: return 0

        def dp(root):
            # /* 返回一个大小为 2 的数组 arr
            # arr[0] 表示不抢 root 的话，得到的最大钱数
            # arr[1] 表示抢 root 的话，得到的最大钱数 */
            if not root: return [0, 0]  # [rob, not_rob]
            lf, rt = dp(root.left), dp(root.right)
            # 抢，下家就不能抢了
            rob = root.val + lf[1] + rt[1]
            # 不抢，下家可抢可不抢，取决于收益大小
            # 【对应法一max(do, not_do)】
            not_rob = 0 + max(lf[0], lf[1]) + max(rt[0], rt[1])
            return [rob, not_rob]

        res = dp(root)
        return max(res[0], res[1])


