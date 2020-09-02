from Tree.q246_inorder_successor_in_bst import TreeNode


class Solution:
    # 【迭代版】中序遍历模板+卖萌处理
    def kthSmallest(self, root: TreeNode, k: int) -> int:
        stack, rst = [root], []
        cnt = 0
        while stack:
            i = stack.pop()
            if isinstance(i, TreeNode):
                stack.extend([i.right, i.val, i.left])
            elif isinstance(i, int):
                rst.append(i)  # 放一个计cnt++
                cnt += 1
                if cnt == k: return i
        return rst[k - 1]

    # 法1(递归版):中序遍历，取inorder[k-1]
    def kthSmallest1(self, root: TreeNode, k: int) -> int:
        def inorder(root):
            return inorder(root.left) + [root.val] + inorder(root.right) if root else []

        return inorder(root)[k - 1]

    # 法2(迭代版):中序遍历，取inorder[k-1]
    def kthSmallest2(self, root: TreeNode, k: int) -> int:
        stack, rst = [root], []
        while stack:
            i = stack.pop()  # 弹栈：左-中-右（保存至res）
            if isinstance(i, TreeNode):
                stack.extend([i.right, i.val, i.left])  # 压栈：右-中-左
            elif isinstance(i, int):
                rst.append(i)
        return rst[k - 1]
