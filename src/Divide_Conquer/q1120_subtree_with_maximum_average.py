class TreeNode:
    def __init__(self, val):
        self.val = val
        self.left, self.right = None, None


class q1120_subtree_with_maximum_average:
    def maxAvgSubtree(self, root: TreeNode) -> float:
        avg = 0.0

        def dfs(root):  # 返回subtreeSum, n_nodes
            nonlocal avg  # 非局部/全局变量,而是【外部嵌套函数内的变量】
            if root is None:
                return 0, 0
            # zip = [当前子树和, 当前节点总数]
            l_zip, r_zip = dfs(root.left), dfs(root.right)
            curSum, n_nodes = l_zip[0] + r_zip[0] + root.val, \
                              l_zip[1] + r_zip[1] + 1
            avg = max(avg, curSum / n_nodes)
            return curSum, n_nodes

        dfs(root)
        return avg


# (2) Py – Q9_597(返回最大平均值子树的根节点)
class q9_597_subtree_with_maximum_average:
    avg, resRoot = 0.0, None

    def findSubtree2(self, root: TreeNode) -> float:
        self.helper(root)
        return self.resRoot

    def helper(self, root):  # 返回curSum, n_nodes
        # global avg, resRoot
        if root is None:
            return 0, 0
        # zip = [curSum, n_nodes]
        l_zip, r_zip = self.helper(root.left), self.helper(root.right)
        curSum, n_nodes = l_zip[0] + r_zip[0] + root.val, \
                          l_zip[1] + r_zip[1] + 1
        curAvg = curSum * 1.0 / n_nodes
        # self.avg = max(self.avg, curSum * 1.0 / n_nodes)
        if self.resRoot is None or self.avg < curAvg:
            self.resRoot = root
            self.avg = curAvg
        return curSum, n_nodes
