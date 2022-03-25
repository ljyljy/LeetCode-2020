# 现在你总共有 n 门课需要选，记为 0 到 n-1。
#
#  在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
#
#  给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
#
#  可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
#
#  示例 1:
#
#  输入: 2, [[1,0]]
# 输出: [0,1]
# 解释: 总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
#
#  示例 2:
#
#  输入: 4, [[1,0],[2,0],[3,1],[3,2]]
# 输出: [0,1,2,3] or [0,2,1,3]
# 解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
#      因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
#
#
#  说明:
#
#
#  输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
#  你可以假定输入的先决条件中没有重复的边。
#
#
#  提示:
#
#
#  这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。
#  通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。
#
#  拓扑排序也可以通过 BFS 完成。
#
#
#  Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序
#  👍 228 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
import collections
from collections import deque
from typing import List


# 在q207基础上加上路径即可~

class Solution:
    def findOrder(self, numCourses: int, prerequisites: List[List[int]]) -> List[int]:
        in_cnt_dict = [0] * numCourses
        adjacency = [[] for _ in range(numCourses)]  # 领接表（类似无向图的:node.neighbors）
        for cur, pre in prerequisites:
            in_cnt_dict[cur] += 1
            adjacency[pre].append(cur)
        # bfs
        start_nodes = [n for n in range(numCourses) if in_cnt_dict[n] == 0]
        queue = deque(start_nodes)
        topOrder = []
        while queue:
            tmp = queue.popleft()
            numCourses -= 1
            topOrder.append(tmp)
            for next in adjacency[tmp]:
                in_cnt_dict[next] -= 1
                if in_cnt_dict[next] == 0:
                    queue.append(next)
        if not numCourses:  # 说明所有结点均已加入路径，存在拓扑排序
            return topOrder
        return []  # DAG中有环

    # 法2：dfs（将bfs的queue改为stack？？？非递归版）
    def findOrder2(self, numCourses, prerequisites):
        parent = collections.defaultdict(set)  # indegree
        child = collections.defaultdict(set)
        for next, pre in prerequisites:
            parent[next].add(pre)
            child[pre].add(next)
        stack = [i for i in range(numCourses) if not parent[i]]  # 入度为0的点入栈
        res = []
        while stack:
            cur = stack.pop()
            res.append(cur)
            for next in child[cur]:
                parent[next].remove(cur)
                if not parent[next]:
                    stack.append(next)
            parent.pop(cur)
        return res if not parent else []
# leetcode submit region end(Prohibit modification and deletion)
