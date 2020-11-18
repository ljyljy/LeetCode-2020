# 给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
#
#  另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。
#
#  返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈
# 顿距离，|r1 - r2| + |c1 - c2|。（你可以按任何满足此条件的顺序返回答案。）
#
#
#
#  示例 1：
#
#  输入：R = 1, C = 2, r0 = 0, c0 = 0
# 输出：[[0,0],[0,1]]
# 解释：从 (r0, c0) 到其他单元格的距离为：[0,1]
#
#
#  示例 2：
#
#  输入：R = 2, C = 2, r0 = 0, c0 = 1
# 输出：[[0,1],[0,0],[1,1],[1,0]]
# 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
# [[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。
#
#
#  示例 3：
#
#  输入：R = 2, C = 3, r0 = 1, c0 = 2
# 输出：[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
# 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
# 其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。
#
#
#
#
#  提示：
#
#
#  1 <= R <= 100
#  1 <= C <= 100
#  0 <= r0 < R
#  0 <= c0 < C
#
#  Related Topics 排序
#  👍 54 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class Solution:
    # 法3：BFS
    def allCellsDistOrder(self, R, C, r0, c0) -> List[List[int]]:
        def isValid(r, c):
            return 0 <= r < R and 0 <= c < C

        from collections import deque
        dirs = [(0, 1), (1, 0), (0, -1), (-1, 0)]
        visited, rst = [[False] * C for _ in range(R)], []
        queue, visited[r0][c0] = deque([[r0, c0]]), True
        while queue:
            size = len(queue)
            for i in range(size):
                cur = queue.popleft()
                print(cur)
                rst.append(cur)
                for dir in dirs:
                    x, y = cur[0] + dir[0], cur[1] + dir[1]
                    if not isValid(x, y) or visited[x][y]:
                        continue
                    queue.append([x, y])
                    visited[x][y] = True
        return list(rst)

    # ❤法2：【桶排序❤】：注意到方法一中排序的时间复杂度太高。实际在枚举所有点时，我们可以直接按照哈曼顿距离分桶。这样我们就可以实现线性的桶排序。
    #  1.遍历所有坐标，按照距离的大小分组，每组的距离相等（即放入一个桶中）
    #  2.按照距离从小到大的原则，遍历所有桶，并输出结果
    # 本解法关键在于求得可能的最大距离，即行距离和列距离都最大时：max(r0, R - 1 - r0) + max(c0, C - 1 - c0)
    # 注意：
    # 此解法时间复杂度为 O(R*C)，理论上已达到最快可能
    # 实际时间消耗会比预估要差，不同语言便利程度和优化不一，原因如下：
    #   - 桶的制作涉及大量容器的初始化和存取
    #   - 桶中要存储大量的坐标信息，不论是直接使用长度为 2 的小数组存储，还是用新的简单数据类，都会耗费很多时间

    # 时间复杂度：O(RC)，存储所有点时间复杂度 O(RC)，桶排序O(RC)。
    # 空间复杂度：O(RC)，需要存储矩阵内所有点。
    def allCellsDistOrder2(self, R, C, r0, c0) -> List[List[int]]:
        import collections  # ↓ ranR, ranC = [0, R-1], [0, C-1]
        bucket = collections.defaultdict(list)
        dist_key = lambda r1, c1, r0, c0: abs(r1 - r0) + abs(c1 - c0)
        for i in range(R):
            for j in range(C):  # ❤注意是append！不是等号（∵等距离的可能有多个）
                bucket[dist_key(i, j, r0, c0)].append([i, j])
        # ❤对字典排序：https://www.cnblogs.com/wqbin/p/10222768.html
        rst = []
        # 求法1：
        dists = sorted(bucket.keys())  # 按升序排列
        for dist in dists:
            rst.extend(bucket[dist])  # # ∵defaultdict ∴key不存在不报错！
        # 求法2：不推荐，遍历效率不高，很多[0,maxDist]可能不存在
        # maxDist = max(r0-0, R-1 -r0) + max(c0-0, C-1 -c0)
        # for i in range(maxDist+1):  # ∵defaultdict ∴key不存在，返回[]
        #     rst.extend(bucket[i])
        return rst

    # 法1: 【直接排序】：首先存储矩阵内所有的点，然后将其按照哈曼顿距离直接排序。
    #  - 优化：本解法可以使用哈希表优化，即使用坐标作 key，使用距离作 value，然后按照距离排序，这样就不会因为多次对同一下标进行比较而重复计算距离
    # 无论如何优化，核心仍然是直接排序，时间复杂度不会优于 O(RC*log(RC))
    # 时间复杂度：O(RC log(RC))，存储所有点时间复杂度 O(RC)，排序时间复杂度 O(RC log(RC))。
    # 空间复杂度：O(log(RC))，即为排序需要使用的栈空间，不考虑返回值的空间占用。
    def allCellsDistOrder1(self, R: int, C: int, r0: int, c0: int) -> List[List[int]]:
        points = [[i, j] for i in range(R) for j in range(C)]
        points.sort(key=lambda x: abs(x[0] - r0) + abs(x[1] - c0))
        return points

# leetcode submit region end(Prohibit modification and deletion)
