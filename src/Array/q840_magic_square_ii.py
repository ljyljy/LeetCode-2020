# 3 x 3 的幻方是一个填充有从 1 到 9 的不同数字的 3 x 3 矩阵，其中每行，每列以及两条对角线上的各数之和都相等。
#
#  给定一个由整数组成的 grid，其中有多少个 3 × 3 的 “幻方” 子矩阵？（每个子矩阵都是连续的）。
#
#
#
#  示例：
#
#  输入: [[4,3,8,4],
#       [9,5,1,9],
#       [2,7,6,2]]
# 输出: 1
# 解释:
# 下面的子矩阵是一个 3 x 3 的幻方：
# 438
# 951
# 276
#
# 而这一个不是：
# 384
# 519
# 762
#
# 总的来说，在本示例所给定的矩阵中只有一个 3 x 3 的幻方子矩阵。
#
#
#  提示:
#
#
#  1 <= grid.length <= 10
#  1 <= grid[0].length <= 10
#  0 <= grid[i][j] <= 15
#
#  Related Topics 数组
#  👍 36 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# class Solution:
#     def numMagicSquaresInside(self, grid: List[List[int]]) -> int:
#         row, col = len(grid), len(grid[0])
#
#         def magic(a, b, c, d, e, f, g, h, i):
#             return (sorted([a, b, c, d, e, f, g, h, i]) == range(1, 10) and     # a, b, c,
#                     (a + b + c == d + e + f == g + h + i == a + e + i ==        # d, e, f,
#                      a + d + g == b + e + h == c + f + i == c + e + g == 15))   # g, h, i
#
#         ans = 0
#         for r in range(row - 2):  # (r,c) 幻方的左上角
#             for c in range(col - 2):
#                 if grid[r + 1][c + 1] != 5: continue  # optional skip
#                 if magic(grid[r][c], grid[r][c + 1], grid[r][c + 2],
#                          grid[r + 1][c], grid[r + 1][c + 1], grid[r + 1][c + 2],
#                          grid[r + 2][c], grid[r + 2][c + 1], grid[r + 2][c + 2]):
#                     ans += 1
#         return ans
class Solution(object):
    def numMagicSquaresInside(self, grid):
        R, C = len(grid), len(grid[0])

        def magic(a, b, c, d, e, f, g, h, i):
            return (sorted([a, b, c, d, e, f, g, h, i]) == list(range(1, 10)) and
                    (a + b + c == d + e + f == g + h + i == a + d + g ==
                     b + e + h == c + f + i == a + e + i == c + e + g == 15))

        ans = 0
        for r in range(R - 2):
            for c in range(C - 2):
                if grid[r + 1][c + 1] != 5: continue  # optional skip
                if magic(grid[r][c], grid[r][c + 1], grid[r][c + 2],
                         grid[r + 1][c], grid[r + 1][c + 1], grid[r + 1][c + 2],
                         grid[r + 2][c], grid[r + 2][c + 1], grid[r + 2][c + 2]):
                    ans += 1
        return ans

# leetcode submit region end(Prohibit modification and deletion)
