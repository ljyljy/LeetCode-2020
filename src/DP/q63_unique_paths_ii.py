# 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。 
# 
#  机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。 
# 
#  现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？ 
# 
#  
# 
#  网格中的障碍物和空位置分别用 1 和 0 来表示。 
# 
#  说明：m 和 n 的值均不超过 100。 
# 
#  示例 1: 
# 
#  输入:
# [
#   [0,0,0],
#   [0,1,0],
#   [0,0,0]
# ]
# 输出: 2
# 解释:
# 3x3 网格的正中间有一个障碍物。
# 从左上角到右下角一共有 2 条不同的路径：
# 1. 向右 -> 向右 -> 向下 -> 向下
# 2. 向下 -> 向下 -> 向右 -> 向右
#  
#  Related Topics 数组 动态规划 
#  👍 386 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class Solution:
    # 法2：迭代动归
    def uniquePathsWithObstacles(self, obstacleGrid: List[List[int]]) -> int:
        m, n = len(obstacleGrid), len(obstacleGrid[0])
        if m <= 0 or n <= 0 or obstacleGrid[-1][-1] == 1:
            return 0  # ↑ 终点不能有障碍物
        memo = [[0] * n for _ in range(m)]  # memo = dict()
        for i in range(m - 1, -1, -1):  # [m-1: -1 :0]
            for j in range(n - 1, -1, -1):  # [n-1: -1 :0]
                if not obstacleGrid[i][j]:
                    if i == m - 1 and j == n - 1:
                        memo[i][j] = 1
                    elif i == m - 1:
                        memo[i][j] = memo[i][j + 1]
                    elif j == n - 1:
                        memo[i][j] = memo[i + 1][j]
                    else:
                        memo[i][j] = memo[i + 1][j] + memo[i][j + 1]
        return memo[0][0]

    # 法1： dfs动归+memo
    def uniquePathsWithObstacles01(self, obstacleGrid: List[List[int]]) -> int:
        m, n = len(obstacleGrid), len(obstacleGrid[0])
        if m <= 0 or n <= 0 or obstacleGrid[-1][-1] == 1:
            return 0  # ↑ 终点不能有障碍物
        memo = dict()
        return self._dp_helper(obstacleGrid, m, n, 0, 0, memo)

    # memo[i,j] 对应obstacleGrid[i, j] --> cur_row <=> i,  cur_col <=> j
    def _dp_helper(self, grid, m, n, cur_row, cur_col, memo):
        # base case & terminator
        if cur_row == m - 1 and cur_col == n - 1:
            return 1  # 即：成功走到终点，res += 1
        if cur_row >= m or cur_col >= n or grid[cur_row][cur_col] == 1:
            return 0  # ↑当前点为障碍物
        if cur_row < m and cur_col < n and (cur_row, cur_col) in memo:
            return memo[(cur_row, cur_col)]

        res = self._dp_helper(grid, m, n, cur_row + 1, cur_col, memo) + \
              self._dp_helper(grid, m, n, cur_row, cur_col + 1, memo)
        memo[(cur_row, cur_col)] = res
        return res

    # 法3？？？：迭代动归（从起点递推到终点）
    def uniquePathsWithObstacles03(self, obstacleGrid: List[List[int]]) -> int:
        m, n = len(obstacleGrid), len(obstacleGrid[0])
        if m <= 0 or n <= 0 or obstacleGrid[-1][-1] == 1:
            return 0  # ↑ 终点不能有障碍物
        memo = [[0] * n for _ in range(m)]  # memo = dict()
        # 对于x > 1 and y > 1的点来说，它们的路径就是(x-1, y) + (x, y-1)，
        # 而对于边界的点来说，路径为(x-1, y) or (x, y-1)。
        for i in range(0, m):
            for j in range(0, n):
                if not obstacleGrid[i][j]:
                    if i == 0 and j == 0:
                        memo[i][j] = 1
                    else:
                        memo[i][j] = memo[i - 1][j] + memo[i][j - 1]  # 存在memo[-1][j]等情况？
        return memo[-1][-1]

    # leetcode submit region end(Prohibit modification and deletion)
