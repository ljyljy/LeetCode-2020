# 	Q130. 被围绕的区域
# 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
# 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
# 例1：
# X X X X
# X O O X
# X X O X
# X O X X
# 运行你的函数后，矩阵变为：
# X X X X
# X X X X
# X X X X
# X O X X
# 相关题目
# 663. 墙和门589. 连接图434. 岛屿的个数II433. 岛屿的个数

# 思路：
# 可以使用BFS或DFS解题.
# 方法1:在记录每个节点是否访问过的前提下, 依次从每个 'O' 开始BFS/DFS, 并且只访问未访问过的 'O'.如果从一个 'O' 可以访问到边界, 那么不做任何操作; 否则便将这个 'O' 可以访问到的所有的 'O' 替换为 'X'.
# 方法2: 从每个边界的 'O' 开始遍历, 只访问 'O', 先都暂时设置为 'W' 或其他字符.
# 遍历结束之后, 将剩下的 'O' 替换为 'X' 然后再将 'W' 还原即可.( 这样非 W 的地方就全都需要标记为 X)

class Solution:
    DIRS = [(1, 0), (-1, 0), (0, 1), (0, -1)]

    # 法1：DFS
    def surroundedRegions1(self, board):
        def isBorder(x, y):
            return x == 0 or y == 0 or x == n - 1 or y == m - 1

        def isValid(x, y):
            return 0 <= x <= n - 1 and 0 <= y <= m - 1

        def dfs(x, y):
            if not isValid(x, y): return
            if board[x][y] != 'O': return
            board[x][y] = '*'  # 将'边界O'暂时替换为‘*’
            # dill down 下探（任何与边界相连的'O'，都将他们替换为“*”——最后结果：需还原回'O'）【类比q934(染色)】
            for dir in self.DIRS:
                new_x, new_y = x + dir[0], y + dir[1]
                dfs(new_x, new_y)

        if not len(board) or not len(board[0]): return
        n, m = len(board), len(board[0])

        # 四周->中间搜索 todo任何与边界相连的“O”暂时替换为‘*’
        for i in range(n):
            for j in range(m):
                if isBorder(i, j):
                    if board[i][j] == 'O':
                        dfs(i, j)
        # 更新结果：将剩余的'内陆O'->'X', （原先的边界O）'*'->'O'
        for i in range(n):
            for j in range(m):
                # 未经替换的、剩余的'内陆O'->'X'
                if board[i][j] == 'O':
                    board[i][j] = 'X'
                # '边界O'->'*' -- 需还原回'O'
                elif board[i][j] == '*':
                    board[i][j] = 'O'

    # 法2：BFS

    # 法3：union find，设置一个dummy node 链接所有边缘的O 并作为father， 链接所有的O，只要father不是dummy node就变为X
    class UnionFind:
        def __init__(self, n):
            self.father = [i for i in range(n)]

        def find(self, x):
            if x == self.father[x]:
                return x
            # x = self.find(self.father[x])  # 递归版【TLE】
            # 迭代版（pass 100%）
            path = []
            while x != self.father[x]:
                path.append(x)
                x = self.father[x]
            for node in path:
                self.father[node] = x
            return x

        def union(self, a, b):
            root_a, root_b = self.find(a), self.find(b)
            if root_a != root_b:
                self.father[min(root_a, root_b)] = max(root_b, root_a)

    def surroundedRegions(self, board):
        def isBorder(x, y):
            return x == 0 or y == 0 or x == n - 1 or y == m - 1

        def isValid(x, y):
            return 0 <= x <= n - 1 and 0 <= y <= m - 1

        def pos2id(x, y):
            return x * m + y

        def id2pos(id):
            return

        if not len(board) or not len(board[0]): return
        n, m = len(board), len(board[0])
        # 2d坐标 映射 一维（x*ncol + y）
        total = n * m
        uf = self.UnionFind(total + 1)  # WHY +1? - 对应dummy node('假想边界')，idx = len+1
        for i in range(n):
            for j in range(m):
                if board[i][j] == 'X': continue
                # 仅对'边界O'与'内陆O'讨论：
                if isBorder(i, j):  # 1. '边界O'のUnion
                    uf.union(pos2id(i, j), total)  # 将所有'边界O'的一维坐标与'假想边界dummy'相连
                else:  # 2. '内陆O'のUnion
                    for dir in self.DIRS:
                        new_i, new_j = i + dir[0], j + dir[1]
                        if board[new_i][new_j] == 'O':  # 新旧<i', j'>均对应'内陆O'
                            uf.union(pos2id(i, j), pos2id(new_i, new_j))

        for i in range(n):
            for j in range(m):
                if board[i][j] == 'O' and uf.find(pos2id(i, j)) != total:  # 内陆O
                    board[i][j] = 'X'
