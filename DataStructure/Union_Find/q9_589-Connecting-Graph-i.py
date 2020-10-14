# 	Q9_589. 连接图I · Connecting Graph I
# 给一个图中的n个节点, 记为 1 到 n . 在开始的时候图中没有边。
# 你需要完成下面两个方法:
# connect(a, b), 添加连接节点 a, b 的边.
# query(a, b), 检验两个节点是否联通。
# Example：
# 5 // n = 5
# query(1, 2) return false
# connect(1, 2)
# query(1, 3) return false
# connect(2, 4)
# query(1, 4) return true

from collections import defaultdict


class ConnectingGraph:
    def __init__(self, n):
        self.father = {}
        for i in range(n + 1):  # [0, n]
            self.father[i] = i

    def connect(self, a, b):
        root_a, root_b = self.find(a), self.find(b)
        if root_a != root_b:
            self.father[root_a] = root_b

    def query(self, a, b):
        return self.find(a) == self.find(b)

    # 尾递归
    def find(self, x):
        if self.father[x] == x:
            return x
        self.father[x] = self.find(self.father[x])
        return self.father[x]

    # 非递归
    def find1(self, x):
        if self.father[x] == x:
            return x
        x2 = x
        while self.father[x] != x:
            x = self.father[x]
        while x2 != x:
            tmp = self.father[x2]
            self.father[x2] = x
            x2 = tmp
        return x
