class ConnectingGraph3:
    def __init__(self, n):
        self.father = {}
        self.cnt_connected = n  # 连通块个数
        for i in range(n + 1):
            self.father[i] = i

    def connect(self, a, b):
        root_a, root_b = self.find(a), self.find(b)
        if root_a != root_b:
            self.father[root_a] = root_b
            self.cnt_connected -= 1

    def query(self):
        return self.cnt_connected

    def find(self, x):
        if self.father[x] == x:
            return x
        self.father[x] = self.find(self.father[x])
        return self.father[x]
