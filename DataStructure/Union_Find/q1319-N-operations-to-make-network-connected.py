# 用以太网线缆将 n 台计算机连接成一个网络，计算机的编号从 0 到 n-1。线缆用 connections 表示，其中 connections[i] = [
# a, b] 连接了计算机 a 和 b。
#
#  网络中的任何一台计算机都可以通过网络直接或者间接访问同一个网络中其他任意一台计算机。
#
#  给你这个计算机网络的初始布线 connections，你可以拔开任意两台直连计算机之间的线缆，并用它连接一对未直连的计算机。请你计算并返回使所有计算机都连
# 通所需的最少操作次数。如果不可能，则返回 -1 。
#
#
#
#  示例 1：
#
#
#
#  输入：n = 4, connections = [[0,1],[0,2],[1,2]]
# 输出：1
# 解释：拔下计算机 1 和 2 之间的线缆，并将它插到计算机 1 和 3 上。
#
#
#  示例 2：
#
#
#
#  输入：n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
# 输出：2
#
#
#  示例 3：
#
#  输入：n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
# 输出：-1
# 解释：线缆数量不足。
#
#
#  示例 4：
#
#  输入：n = 5, connections = [[0,1],[0,2],[3,4],[2,3]]
# 输出：0
#
#
#
#
#  提示：
#
#
#  1 <= n <= 10^5
#  1 <= connections.length <= min(n*(n-1)/2, 10^5)
#  connections[i].length == 2
#  0 <= connections[i][0], connections[i][1] < n
#  connections[i][0] != connections[i][1]
#  没有重复的连接。
#  两台计算机不会通过多条线缆连接。
#
#  Related Topics 深度优先搜索 广度优先搜索 并查集
#  👍 70 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
import collections
from typing import List


class UnionFind:
    def __init__(self, n: int):
        self.father = list(range(n))
        self.cnt_connected = n  # 连通块个数

    def union(self, a, b):
        root_a, root_b = self.find(a), self.find(b)
        if root_a != root_b:
            self.father[root_a] = root_b
            self.cnt_connected -= 1

    def find(self, x):
        if x == self.father[x]:
            return x
        self.father[x] = self.find(self.father[x])
        return self.father[x]

    def query(self):
        return self.cnt_connected


class Solution:
    # 法2 并查集
    # 时间复杂度：O(n+m)，其中 m 是数组 connections 的长度。
    # 空间复杂度：O(n+m)，其中 O(m)为存储所有边需要的空间，O(n) 为深度优先搜索中使用的栈空间。
    def makeConnected(self, n: int, connections: List[List[int]]) -> int:
        if len(connections) < n - 1: return -1
        uf = UnionFind(n)
        for x, y in connections:
            uf.union(x, y)
        return uf.query() - 1

    # 法1 DFS
    # 时间复杂度：O(n+m)，其中 m 是数组 connections 的长度。
    # 空间复杂度：O(n+m)，其中 O(m)为存储所有边需要的空间，O(n) 为深度优先搜索中使用的栈空间。
    def makeConnected1(self, n: int, connections: List[List[int]]) -> int:
        if len(connections) < n - 1: return -1
        edges = collections.defaultdict(list)
        for x, y in connections:
            edges[x].append(y)
            edges[y].append(x)

        seen = set()

        def dfs(curNode: int):
            seen.add(curNode)
            for v in edges[curNode]:
                if v not in seen:
                    dfs(v)

        ans = 0  # 连通分量个数
        for i in range(n):
            if i not in seen:
                dfs(i)  # 将与i连接的结点全部放入seen
                ans += 1
        return ans - 1  # '整合'操作数 = 连通分量 - 1

# leetcode submit region end(Prohibit modification and deletion)
