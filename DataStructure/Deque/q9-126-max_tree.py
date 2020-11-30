# 	Q9_126. 最大树
# 给出一个没有重复的整数数组，在此数组上建立最大树的定义如下：
# 根是数组中最大的数,  左子树和右子树元素分别是被父节点元素切分开的子数组中的最大值
# 利用给定的数组构造最大树。
# 挑战: 要求时间复杂度和空间复杂度均为O(n)
# 示例 1:
# 输入：[2, 5, 6, 0, 3, 1]			输出：{6,5,3,2,#,0,1}
# 解释：  此数组构造的最大树是：
#     6
#    / \
#   5   3
#  /   / \
# 2   0   1
# 示例 2
# 输入：[6,4,20]
# 输出：{20,6,#,#,4}
# 解释：
#      20
#      /
#     6
#      \
#       4
# 相关题目： 510. 最大矩形 122. 直方图最大矩形覆盖12. 带最小值操作的栈

import sys


class TreeNode:
    def __init__(self, val):
        self.val = val
        self.left, self.right = None, None


class Solution:
    def maxTree(self, A):
        if not A: return None
        dummy = sys.maxsize
        nodes = [TreeNode(num) for num in A + [dummy]]
        stack = []  # 递减栈, 栈内元素递减
        for idx, num in enumerate(A + [dummy]):
            while stack and A[stack[-1]] < num:  # 当前num比栈顶大;    num如 6/8
                min1_idx = stack.pop()  # 栈顶找到了其右边界num，可以pop了; 栈顶min1如 (5)
                # 下面需要为min1结点(5) 分配左&右'父亲'(6/8)
                min2_idx = stack[-1] if stack else None
                min2 = A[min2_idx] if stack else dummy  # min1的父亲之一，如【7】
                if min2 < num:  # 如 【7】 < 8 -- 【7】  (5)  8
                    nodes[min2_idx].right = nodes[min1_idx]  # 【7】.right = (5),  后续8.left = 【7】
                else:  # 如: 【7】 > 6 -- 【7】 (5)  6
                    nodes[idx].left = nodes[min1_idx]  # 6.left = (5), 后续【7】.right = 6
            stack.append(idx)  # 递减栈, 新元素小于原栈顶(左边界)，还需要找右边界，故压栈
        return nodes[-1].left  # dummy结点的左子树，即为所求
