# 给你一个 m x n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。
#
#
#
#  示例：
#
#  给出如下 3x6 的高度图:
# [
#   [1,4,3,1,3,2],
#   [3,2,1,3,2,4],
#   [2,3,3,2,3,1]
# ]
#
# 返回 4 。
#
#
#
#
#  如上图所示，这是下雨前的高度图[[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]] 的状态。
#
#
#
#
#
#  下雨后，雨水将会被存储在这些方块中。总的接雨水量是4。
#
#
#
#  提示：
#
#
#  1 <= m, n <= 110
#  0 <= heightMap[i][j] <= 20000
#
#  Related Topics 堆 广度优先搜索
#  👍 264 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
import heapq
from typing import List


class Solution:
    DIRS = [(-1, 0), (1, 0), (0, -1), (0, 1)]

    def trapRainWater(self, heightMap: List[List[int]]) -> int:
        def is_valid(x, y):
            return 0 <= x < n and 0 <= y < m

        n, m = len(heightMap), len(heightMap[0])
        heap, visited, water = [], {}, 0
        for i in range(n):
            for j in range(m):
                if i in (0, n - 1) or j in (0, m - 1):  # 将 边界 入堆
                    heapq.heappush(heap, (heightMap[i][j], i, j))
                    visited[(i, j)] = 1
                else:
                    visited[(i, j)] = 0
        while heap:
            min_h, x, y = heapq.heappop(heap)  # 边界 / 候选'盛水围墙柱'中的min_h
            for dir in self.DIRS:
                new_x, new_y = x + dir[0], y + dir[1]
                if not is_valid(new_x, new_y): continue
                if visited[(new_x, new_y)]: continue

                max_h_around = max(min_h, heightMap[new_x][new_y])  # 比较候选min_h & 其周围的h，选较大者作为'新候选围墙柱'
                water += max_h_around - heightMap[new_x][new_y]
                heapq.heappush(heap, (max_h_around, new_x, new_y))  # ❤是max_h，而非hM[nx][ny]
                visited[(new_x, new_y)] = 1
        return water

# leetcode submit region end(Prohibit modification and deletion)
