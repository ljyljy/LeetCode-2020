from typing import List


class Solution:
    # 方向数组，相对于当前位置的 4 个方向-横、纵坐标的偏移量，这是一个常见的技巧
    dirs = [(1, 0), (0, 1), (-1, 0), (0, -1)]

    def exist(self, board: List[List[str]], word: str) -> bool:
        if not board: return False
        rows, cols = len(board), len(board[0])
        # rows和cols勿写反！！❤ 且 不可写成 [[False] * cols] * rows【错×】
        visited = [[False] * cols for _ in range(rows)]  # ↑ 引用，某一行改了，所有行会跟着一起改变
        # 遍历起点（棋盘for*2） + 遍历方向（dfs）
        for i in range(rows):
            for j in range(cols):
                visited[i][j] = True
                if self.dfs(board, word, i, j, 0, visited):
                    return True
                visited[i][j] = False
        return False

    def dfs(self, board, word, i, j, idx, visited):
        ch = board[i][j]
        visited[i][j] = True
        if ch != word[idx]: return False
        if idx >= len(word) - 1: return True
        # dfs中遍历方向
        for dir in self.dirs:
            new_x, new_y = i + dir[0], j + dir[1]
            if not self._isBounded(new_x, new_y, board):
                continue
            if visited[new_x][new_y]: continue

            visited[new_x][new_y] = True
            if self.dfs(board, word, new_x, new_y, idx + 1, visited):
                return True
            visited[new_x][new_y] = False
        return False

    def _isBounded(self, new_x, new_y, board):
        m, n = len(board) - 1, len(board[0]) - 1
        return 0 <= new_x <= m and 0 <= new_y <= n


if __name__ == "__main__":
    sol = Solution()
    board = [["A", "B", "C", "E"], ["S", "F", "C", "S"], ["A", "D", "E", "E"]]
    word = "ABCCED"
    res = sol.exist(board, word)
    print(res)
