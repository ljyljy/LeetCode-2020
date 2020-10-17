class ConnectingGraph2:
    def __init__(self, n):
        self.father = {}
        self.count = {}
        for i in range(1, n + 1):
            self.father[i] = i
            self.count[i] = 1

    def connect(self, a, b):
        root_a, root_b = self.find(a), self.find(b)
        if root_a != root_b:
            self.father[root_a] = root_b
            self.count[root_b] += self.count[root_a]
            # 法2：运行更快 # need check root connect leaf or not
            # if self.count[root_b] >= self.count[root_a]:  # 小的并到大的，加速计算
            #     self.father[root_a] = root_b  # 数量大的b做根
            #     self.count[root_b] += self.count[root_a]
            # else:
            #     self.father[root_b] = root_a  # 数量大的a做根
            #     self.count[root_a] += self.count[root_b]

    def query(self, a):
        return self.count[self.find(a)]

    def find(self, x):
        if x == self.father[x]:
            return x
        self.father[x] = self.find(self.father[x])
        return self.father[x]

    def find2(self, node):
        path = []
        while node != self.father[node]:
            path.append(node)
            node = self.father[node]
        # 退出while: 找到node的老祖宗，并赋值给node
        for n in path:
            self.father[n] = node
        return node
