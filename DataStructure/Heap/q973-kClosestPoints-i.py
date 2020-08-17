# 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
#
#  （这里，平面上两点之间的距离是欧几里德距离。）
#
#  你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
#
#
#
#  示例 1：
#
#  输入：points = [[1,3],[-2,2]], K = 1
# 输出：[[-2,2]]
# 解释：
# (1, 3) 和原点之间的距离为 sqrt(10)，
# (-2, 2) 和原点之间的距离为 sqrt(8)，
# 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
# 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
#
#
#  示例 2：
#
#  输入：points = [[3,3],[5,-1],[-2,4]], K = 2
# 输出：[[3,3],[-2,4]]
# （答案 [[-2,4],[3,3]] 也会被接受。）
#
#
#
#
#  提示：
#
#
#  1 <= K <= points.length <= 10000
#  -10000 < points[i][0] < 10000
#  -10000 < points[i][1] < 10000
#
#  Related Topics 堆 排序 分治算法
#  👍 101 👎 0


# leetcode submit region begin(Prohibit modification and deletion)

# 【Follow Up】：
# (1) 提高：按照距离由小到大返回。如果两个点有相同距离，则按照x值来排序；若x值也相同，就再按照y值排序.
# (2) 将其变为“在线算法”问题（如：数据流问题、LRU等） -- 见"前K大数II"
from typing import List
import heapq


class Solution:
    def kClosest(self, points: List[List[int]], K: int) -> List[List[int]]:
        self.heap, rst = [], []
        for p in points:
            dist = self._getDist(p)  # 入堆：↓ 同样distance还要比x 和 y
            heapq.heappush(self.heap, (-dist, -p[0], -p[1]))  # 设置排序优先级（取负）
            if len(self.heap) > K:
                heapq.heappop(self.heap)

        while len(self.heap):
            _, x, y = heapq.heappop(self.heap)  # pop顺序：优先级排序的【逆序】
            rst.append([-x, -y])  # 再次取负还原。

        return rst[::-1]  # 由于pop出的是优先级逆序，故需翻转

    def _getDist(self, p: List[int]):
        return (p[0] - 0) ** 2 + (p[1] - 0) ** 2
# leetcode submit region end(Prohibit modification and deletion)
