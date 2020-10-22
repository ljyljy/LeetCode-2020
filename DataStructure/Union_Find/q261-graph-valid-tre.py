# 	Q261/ Q9-178. 以图判树
# 给出 n 个节点，标号分别从 0 到 n - 1 并且给出一个 无向 边的列表, 写一个函数去判断这张｀无向｀图是否是一棵树
# 说明：边的列表当中, 无向边不重复，如　[0, 1] 和 [1, 0]　是同一条边，
# 例1：
# 输入: n = 5 edges = [[0, 1], [0, 2], [0, 3], [1, 4]]
# 输出: true.
# 例 2:
# 输入: n = 5 edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]]
# 输出: false.
# 相关题目
# 814. 无向图中的最短路径750. 传送门589. 连接图431. 找无向图的连通块

# 思路：
# 判断无向图中有无环——若无环 & 所有点都在一个连通块内 -》 合法树
# 此时：由于初始化的self.size = n, 每次union时size--，最后的size应为1

class Solution:
    def validTree(self, n, edges):
        if n - 1 != len(edges): return False
        self.father = {i: i for i in range(n)}
        self.size = n

        for a, b in edges:
            self._union(a, b)
        return self.size == 1

    def _union(self, a, b):
        root_a, root_b = self._find(a), self._find(b)
        if root_a != root_b:
            self.father[root_b] = root_a
            self.size -= 1

    def _find(self, x):
        if x == self.father[x]:
            return x
        # 写法1：（递归）
        # x = self._find(self.father[x])
        # 写法2：（迭代）
        path = []
        while x != self.father[x]:
            tmp = self.father[x]
            path.append(x)
            x = tmp
        for node in path:
            self.father[node] = x
        return x
