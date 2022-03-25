# 有一种新的使用拉丁字母的外来语言。但是，你不知道字母之间的顺序。你会从词典中收到一个非空的单词列表，其中的单词在这种新语言的规则下按字典顺序排序。请推导出这种语言的字母顺序。
# 说明：
# 你可以假设所有的字母都是小写。
# 如果a是b的前缀且b出现在a之前，那么这个顺序是无效的。
# 如果顺序是无效的，则返回空字符串。
# 这里可能有多个有效的字母顺序，返回以正常字典顺序看来最小的。
# 例1：
# 输入：["wrt","wrf","er","ett","rftt"]
# 输出："wertf"
# 解释：
# 从 "wrt"和"wrf" ,我们可以得到 't'<'f'
# 从 "wrt"和"er" ,我们可以得到'String.HJ_msg'<'e'
# 从 "er"和"ett" ,我们可以得到 get 'r'<'t'
# 从 "ett"和"rftt" ,我们可以得到 'e'<'r'
# 所以返回 "wertf"
# 例2:
# 输入：["z","x"]
# 输出："zx"
# 解释：
# 从 "z" 和 "x"，我们可以得到 'z' < 'x'
# 所以返回"zx"。
from heapq import heapify, heappop, heappush


class Solution:
    """
    @param words: a list of words
    @return: a string which is correct order
    """

    def alienOrder(self, words):
        graph = self._build_graph(words)
        if not graph: return ""
        return self._topo_sort(graph)

    def _build_graph(self, words):
        # key is node, value is neighbors
        graph = {}
        # 1. initialize graph
        for word in words:
            for ch in word:
                graph[ch] = set()
        # 2. add edges
        n = len(words)
        for i in range(n - 1):
            for j in range(min(len(words[i]), len(words[i + 1]))):
                if words[i][j] != words[i + 1][j]:
                    graph[words[i][j]].add(words[i + 1][j])
                    break  # 若成功，每对单词仅判断一次！
                if j == min(len(words[i]), len(words[i + 1])) - 1:  # 下标idx==len-1
                    # 此时两单词依旧前j个相同, 甚至word[i]比后者还长，显然不满足字典序
                    if len(words[i]) > len(words[i + 1]):  # 到达短单词的末端，出口
                        return None
        return graph

    def _topo_sort(self, graph):
        indegree = {node: 0 for node in graph}
        for node in graph:
            for nei in graph[node]:
                indegree[nei] += 1
        # use 【heapq】 instead of regular queue so that we can get the
        # smallest lexicographical order ❤❤❤
        queue = [n for n in indegree if indegree[n] == 0]
        heapify(queue)
        topo_order = ""
        # regular bfs algorithm to do topological sorting
        while queue:
            cur = heappop(queue)
            topo_order += cur
            for next in graph[cur]:
                indegree[next] -= 1
                if indegree[next] == 0:
                    heappush(queue, next)

        # if all nodes popped
        if len(topo_order) == len(graph):
            return topo_order
        return ""
