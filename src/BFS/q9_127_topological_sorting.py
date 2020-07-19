# 给定一个有向图，图节点的拓扑排序定义如下:
# 对于图中的每一条有向边 A -> B , 在拓扑排序中A一定在B之前.
# 拓扑排序中的第一个节点可以是图中的任何一个没有其他节点指向它的节点.
# 针对给定的有向图找到任意一种拓扑排序的顺序.
# 说明：
# 你可以假设图中至少存在一种拓扑排序
# 例1：
# 输入：如左图
# 输出：
# [0, 1, 2, 3, 4, 5]
# [0, 2, 3, 1, 5, 4]
# ...
# 挑战：
# 能否分别用BFS和DFS完成？

# 注意：BFS只能输出一条拓扑排序路径！（若要求输出all，则需要用DFS！）
from collections import deque

from typing import List


class DirectedGraphNode:
    def __init__(self, x):
        self.label = x
        self.neighbors = []


class Solution:
    """
    @param graph: 【A list of Directed graph node】
    @return: Any topological order for the given graph.
    """

    def topSort(self, graph: List[DirectedGraphNode]) -> List[DirectedGraphNode]:
        in_cnt_dict = self._get_all_indegree(graph)
        order = []
        # bfs
        start_nodes = [node for node in graph if in_cnt_dict[node] == 0]
        queue = deque(start_nodes)  # 将所有入度为0的起点放入队列（但bfs只能找到一条路径）
        while queue:
            # for _ in range(len(queue)): # 若要求判断graph有且仅有一条拓扑路径，则可以判断len(queue)始终保持为1
            tmp = queue.popleft()
            order.append(tmp)
            for neighbor in tmp.neighbors:
                in_cnt_dict[neighbor] -= 1
                if in_cnt_dict[neighbor] == 0:
                    queue.append(neighbor)
        return order

    def _get_all_indegree(self, graph) -> dict:
        indegree_cnt_dict = {x: 0 for x in graph}
        for node in graph:
            for nei in node.neighbors:
                indegree_cnt_dict[nei] += 1
        return indegree_cnt_dict
