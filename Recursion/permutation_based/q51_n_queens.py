# n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
#
#
#
#  上图为 8 皇后问题的一种解法。
#
#  给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
#
#  每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
#
#  示例:
#
#  输入: 4
# 输出: [
#  [".Q..",  // 解法 1
#   "...Q",
#   "Q...",
#   "..Q."],
#
#  ["..Q.",  // 解法 2
#   "Q...",
#   "...Q",
#   ".Q.."]
# ]
# 解释: 4 皇后问题存在两个不同的解法。
#
#
#
#
#  提示：
#
#
#  皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一到七步
# ，可进可退。（引用自 百度百科 - 皇后 ）
#
#  Related Topics 回溯算法
#  👍 477 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    # 写法1：紧凑
    def solveNQueens(self, n: int) -> List[List[str]]:
        def dfs(path_cols, xy_dif, xy_sum):
            row = len(path_cols)
            if row == n:
                res.append(list(path_cols))
                return

            for col in range(n):  # 遍历每一列
                if col not in path_cols and row - col not in xy_dif and row + col not in xy_sum:
                    dfs(path_cols + [col], xy_dif + [row - col], xy_sum + [row + col])

        res = []
        dfs([], [], [])
        return [["." * i + "Q" + "." * (n - i - 1) for i in subres] for subres in res]

    # 法1(写法2)：拆分开
    def solveNQueens1_2(self, n: int) -> List[List[str]]:
        if n <= 0: return []
        res = []
        self.dfs(n, [], [], [], res)
        return self.drawBoard(n, res)

    def dfs(self, n, path_cols, xy_dif, xy_sum, res):
        def isValid():
            return col not in path_cols and \
                   row - col not in xy_dif and row + col not in xy_sum

        row = len(path_cols)
        if row == n:
            res.append(list(path_cols))
            return

        for col in range(n):  # 遍历每一列
            if not isValid(): continue
            self.dfs(n, path_cols + [col], xy_dif + [row - col], xy_sum + [row + col], res)

    def drawBoard(self, n, res):
        return [['.' * i + 'Q' + '.' * (n - i - 1) for i in subRes] for subRes in res]

    # 法2：# ↓不建议global，否则solveNQueens中应在执行dfs2前将它们clear
    # result = []
    # cols, pie,na = set(), set(), set()
    def solveNQueens2(self, n: int) -> List[List[str]]:
        if n < 1: return []
        self.result = []
        self.cols, self.pie, self.na = set(), set(), set()
        self.dfs2(n, 0, [])
        return [['.' * i + 'Q' + '.' * (n - i - 1) for i in states] \
                for states in self.result]

    def dfs2(self, n, row, cur_state):
        if row >= n:
            self.result.append(cur_state)
            return  # cur_state ↑: 保存每行的Q所在的col_idx

        for col in range(n):
            if col in self.cols or \
                    row + col in self.pie or row - col in self.na:
                continue
            self.cols.add(col)
            self.pie.add(row + col)
            self.na.add(row - col)
            self.dfs2(n, row + 1, cur_state + [col])
            self.cols.remove(col)
            self.pie.remove(row + col)
            self.na.remove(row - col)

# leetcode submit region end(Prohibit modification and deletion)
