from typing import List
from collections import deque


class Solution:
    # 方向数组，相对于当前位置的 4 个方向-横、纵坐标的偏移量，这是一个常见的技巧
    directions = [(-1, 0), (0, -1), (1, 0), (0, 1)]

    # 法1：DFS(Flood Fill)
    def numIslands1(self, grid: List[List[str]]) -> int:
        m = len(grid)  # x∈[0, m]
        if m == 0: return 0
        n = len(grid[0])  # y∈[0, n]
        marked = [[False for _ in range(n)] for _ in range(m)]
        cnt = 0
        # 从第 1 行、第 1 格开始，对每一格尝试进行一次 DFS 操作
        for i in range(m):
            for j in range(n):
                # 只要是陆地，且没有被访问过的，就可以使用 DFS 发现与之相连的陆地，并进行标记
                if grid[i][j] == '1' and not marked[i][j]:
                    cnt += 1
                    self._dfs(i, j, m, n, grid, marked)
        return cnt

    def _dfs(self, i, j, m, n, grid, marked):
        marked[i][j] = True
        for direction in self.directions:
            new_i = i + direction[0]
            new_j = j + direction[1]
            if 0 <= new_i < m and 0 <= new_j < n \
                    and grid[new_i][new_j] == '1' and not marked[new_i][new_j]:
                self._dfs(new_i, new_j, m, n, grid, marked)

    # 法2：BFS
    def numIslands2(self, grid: List[List[str]]) -> int:
        m, cnt = len(grid), 0
        if m == 0: return cnt
        n = len(grid[0])
        marked = [[False for _ in range(n)] for _ in range(m)]
        for i in range(m):
            for j in range(n):
                if not marked[i][j] and grid[i][j] == '1':
                    # count 可以理解为连通分量，你可以在广度优先遍历完成以后，再计数，
                    cnt += 1  # 即这行代码放在【位置 1】也是可以的
                    queue = deque()
                    queue.append((i, j))
                    marked[i][j] = True
                    while queue:
                        cur_x, cur_y = queue.popleft()
                        for direction in self.directions:
                            new_i = cur_x + direction[0]
                            new_j = cur_y + direction[1]
                            if 0 <= new_i < m and 0 <= new_j < n \
                                    and not marked[new_i][new_j] and grid[new_i][new_j] == '1':
                                queue.append((new_i, new_j))
                                # 【特别注意】在放入队列以后，要马上标记成已经访问过，语义也是十分清楚的：反正只要进入了队列，你迟早都会遍历到它
                                # 而不是在出队列的时候再标记
                                # 【特别注意】如果是出队列的时候再标记，会造成很多重复的结点进入队列，造成重复的操作，这句话如果你没有写对地方，代码会严重超时的
                                marked[new_i][new_j] = True
                    # 【位置 1】
        return cnt

    # 法3：并查集
    DIRECT = [(1, 0), (0, 1)]
    cnt, father = 0, []

    def find(self, x):
        path = []
        while x != self.father[x]:
            path.append(x)
            x = self.father[x]
        for node in path:
            self.father[node] = x
        return x

    def connect(self, a, b):
        root_a, root_b = self.find(a), self.find(b)
        if root_a != root_b:
            self.father[root_b] = root_a
            self.cnt -= 1  # 被合并了，cnt--

    def numIslands3(self, grid: List[List[str]]) -> int:
        # 二维坐标(i,j) -> 一维：idx = i * ncol + j
        # 拓展：一维 -> 二维(i,j)： i = idx // ncol;  j = idx % ncol
        def coord2id(i, j):
            return i * m + j

        if not grid or not grid[0]: return 0
        n, m = len(grid), len(grid[0])
        self.father = []
        # 初始化并查集数组（一维）
        for i in range(n):
            for j in range(m):
                self.father.append(coord2id(i, j))
                if grid[i][j] == '1': self.cnt += 1  # cnt总数为所有‘1’的个数，合并时再自减
        print(f'cnt初始值为：{self.cnt}')
        # 遍历二维坐标 & 更新并查集数组（一维）
        for i in range(n):
            for j in range(m):
                if grid[i][j] == '1':
                    # # 写法1
                    # if i + 1 < n and grid[i + 1][j]:  # 向下(行)
                    #     self.connect(coord2id(i + 1, j), coord2id(i, j))
                    # if j + 1 < m and grid[i][j + 1]:  # 向右(列)
                    #     self.connect(coord2id(i, j + 1), coord2id(i, j))
                    # 写法2
                    for dir in self.DIRECT:
                        new_i, new_j = i + dir[0], j + dir[1]
                        if new_i < n and new_j < m and grid[new_i][new_j] == '1':  # 向下/右
                            self.connect(coord2id(new_i, new_j), coord2id(i, j))
        return self.cnt

        #
        # class UnionFind:
        #
        #     def __init__(self, n):
        #         self.count = n
        #         self.parent = [i for i in range(n)]
        #         self.rank = [1 for _ in range(n)]
        #
        #     def get_count(self):
        #         return self.count
        #
        #     def find(self, p):
        #         while p != self.parent[p]:
        #             self.parent[p] = self.parent[self.parent[p]]
        #             p = self.parent[p]
        #         return p
        #
        #     def is_connected(self, p, q):
        #         return self.find(p) == self.find(q)
        #
        #     def union(self, p, q):
        #         p_root = self.find(p)
        #         q_root = self.find(q)
        #         if p_root == q_root:
        #             return
        #
        #         if self.rank[p_root] > self.rank[q_root]:
        #             self.parent[q_root] = p_root
        #         elif self.rank[p_root] < self.rank[q_root]:
        #             self.parent[p_root] = q_root
        #         else:
        #             self.parent[q_root] = p_root
        #             self.rank[p_root] += 1
        #
        #         self.count -= 1
        #
        # row = len(grid)
        # # 特判
        # if row == 0:
        #     return 0
        # col = len(grid[0])
        #
        # def get_index(x, y):
        #     return x * col + y
        #
        # # 注意：我们不用像 DFS 和 BFS 一样，4 个方向都要尝试，只要看一看右边和下面就可以了
        # directions = [(1, 0), (0, 1)]
        # # 多开一个空间，把水域 "0" 都归到这个虚拟的老大上
        # dummy_node = row * col
        #
        # # 多开的一个空间就是那个虚拟的空间
        # uf = UnionFind(dummy_node + 1)
        # for i in range(row):
        #     for j in range(col):
        #         # 如果是水域，都连到那个虚拟的空间去
        #         if grid[i][j] == '0':
        #             uf.union(get_index(i, j), dummy_node)
        #         if grid[i][j] == '1':
        #             # 向下向右如果都是陆地，即 "1"，就要合并一下
        #             for direction in directions:
        #                 new_x = i + direction[0]
        #                 new_y = j + direction[1]
        #                 if new_x < row and new_y < col and grid[new_x][new_y] == '1':
        #                     uf.union(get_index(i, j), get_index(new_x, new_y))
        # # 不要忘记把那个虚拟结点减掉
        # return uf.get_count() - 1


if __name__ == '__main__':
    grid = [["1", "1", "0", "0", "0"], ["1", "1", "0", "0", "0"], ["0", "0", "1", "0", "0"], ["0", "0", "0", "1", "1"]]
    sol = Solution()
    ans1 = sol.numIslands1(grid)
    ans2 = sol.numIslands2(grid)
    ans3 = sol.numIslands3(grid)
    print(ans1, ans2, ans3)
