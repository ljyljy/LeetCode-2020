# 给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。
#
#  单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
#
#
#  示例:
#
#  输入:
# words = ["oath","pea","eat","rain"] and board =
# [
#   ['o','a','a','n'],
#   ['e','t','a','e'],
#   ['i','h','k','r'],
#   ['i','f','l','v']
# ]
#
# 输出: ["eat","oath"]
#
#  说明:
# 你可以假设所有输入都由小写字母 a-z 组成。
#
#  提示:
#
#
#  你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？
#  如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。什么样的数据结构可以有效地执行这样的操作？散列表是否可行？为什么？ 前缀树如何？如果你想学习如何
# 实现一个基本的前缀树，请先查看这个问题： 实现Trie（前缀树）。
#
#  Related Topics 字典树 回溯算法
#  👍 208 👎 0


# leetcode submit region begin(Prohibit modification and deletion)

from heapq import heapify, heappop, heappush

DIRECTIONS = [(0, -1), (0, 1), (-1, 0), (1, 0)]


class Solution:
    def findWords(self, board: List[List[str]], words: List[str]) -> List[str]:
        if not board or not words: return []
        # 预处理: 设置单词set与前缀set，未来只对前缀为prefix_set中的单词进行dfs
        word_set = set(words)
        prefix_set, rst = set(), set()
        for word in words:
            for i in range(len(word)):
                prefix_set.add(word[:i + 1])
        # 遍历所有点
        for i in range(len(board)):
            for j in range(len(board[0])):
                ch = board[i][j]
                self.dfs(board, ch, i, j, word_set, prefix_set, set([(i, j)]), rst)

        heapify(list(rst))
        return list(rst)

    def dfs(self, board, word, i, j, word_set, prefix_set, visited, rst):
        if word not in prefix_set:
            return
        if word in word_set:
            rst.add(word)
            # return

        for dir in DIRECTIONS:
            x_ = i + dir[0]
            y_ = j + dir[1]
            if (x_, y_) in visited or not self.isBounded(x_, y_, board):
                continue

            visited.add((x_, y_))
            self.dfs(board, word + board[x_][y_], x_, y_, word_set, prefix_set, visited, rst)
            visited.remove((x_, y_))

    def isBounded(self, x, y, board):
        return 0 <= x < len(board) and 0 <= y < len(board[0])

# leetcode submit region end(Prohibit modification and deletion)
