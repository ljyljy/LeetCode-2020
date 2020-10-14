class UnionFind:
    father = dict()

    def find(self, x):
        if self.father[x] == x:
            return self.father[x]
        self.father[x] = self.find(self.father[x])
        return self.father[x]

    def union(self, a, b):
        root_a = self.find(a)
        root_b = self.find(b)
        if root_a != root_b:
            self.father[root_a] = root_b
