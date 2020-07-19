class q9_901_closest_binary_search_tree_value_ii:
    """
    @param root: the given BST
    @param target: the given target
    @param k: the given k
    @return: k values in the BST that are closest to the target
    """

    # 法2：O(n)
    def closestKValues(self, root, target, k):
        inorder = self._inorder2(root)
        # 双指针
        rst = self._getKClosest(inorder, target, k)
        return rst

    def _inorder(self, root):
        return self._inorder(root.left) + [root.val] + self._inorder(root.right) if root else []

    def _inorder2(self, root):
        stack, rst = [root], []
        while stack:
            cur = stack.pop()
            if isinstance(cur, TreeNode):  # 压栈倒序：右-中-左
                stack.extend([cur.right, cur.val, cur.left])
            elif isinstance(cur, int):
                rst.append(cur)
        return rst

    def _getKClosest(self, inorder, target, k):
        n = len(inorder)
        left, right, rm_num = 0, n - 1, n - k
        while rm_num:
            if target - inorder[left] > inorder[right] - target:
                left += 1
            else:
                right -= 1

            rm_num -= 1
        return inorder[left: left + k]

    # 法1：O(k+logn)
    def closestKValues2(self, root, target, k):
        if not root or k == 0: return []
        lower_stack = self._get_stack(root, target)
        upper_stack = list(lower_stack)
        if lower_stack[-1].val < target:
            self._move_upper(upper_stack)
        else:  # 说明lower_stack需要移动
            self._move_lower(lower_stack)

        rst = []
        for i in range(k):
            if self._is_lower_closer(lower_stack, upper_stack, target):
                rst.append(lower_stack[-1].val)
                self._move_lower(lower_stack)
            else:
                rst.append(upper_stack[-1].val)
                self._move_upper(upper_stack)
        return rst

    def _get_stack(self, root, target):
        stack = []
        while root:
            stack.append(root)
            if root.val > target:
                root = root.left
            else:
                root = root.right
        return stack

    def _move_upper(self, stack):
        # 相当于以栈顶元素为min，再往右探索比它大的n个结点
        # 将大于原栈顶的次小结点们(原栈顶右子树及其所有左孩子)全部压栈
        # stack中的结点是严格紧密单调递增的（维护栈的递增'紧密性'）
        if stack[-1].right:
            node = stack[-1].right
            while node:
                stack.append(node)
                node = node.left
        else:  # 若栈顶结点r无右孩子，就"往上看": 1. 直接是拐点的左孩子(无右孩子)，不进入while，pop后直接得到拐点
            # 2. 一路pop(as 拐点的左孩子)直到拐点出现(次大于r)
            node = stack.pop()
            while stack and stack[-1].right == node:
                node = stack.pop()

    def _move_lower(self, stack):
        if stack[-1].left:
            node = stack[-1].left
            while node:
                stack.append(node)
                node = node.right
        else:
            node = stack.pop()
            while stack and stack[-1].left == node:
                node = stack.pop()

    def _is_lower_closer(self, lower_stack, upper_stack, target):
        if not lower_stack: return False
        if not upper_stack: return True
        return target - lower_stack[-1].val < upper_stack[-1].val - target
