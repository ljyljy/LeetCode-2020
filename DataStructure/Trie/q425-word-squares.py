# 给定一个单词集合 （没有重复），找出其中所有的 单词方块 。
#
#  一个单词序列形成了一个有效的单词方块的意思是指从第 k 行和第 k 列 (0 ≤ k < max(行数, 列数)) 来看都是相同的字符串。
#
#  例如，单词序列 ["ball","area","lead","lady"] 形成了一个单词方块，因为每个单词从水平方向看和从竖直方向看都是相同的。
#
#  b a l l
# a r e a
# l e a d
# l a d y
#
#
#  注意：
#
#
#  单词个数大于等于 1 且不超过 500。
#  所有的单词长度都相同。
#  单词长度大于等于 1 且不超过 5。
#  每个单词只包含小写英文字母 a-z。
#
#
#
#
#  示例 1：
#
#  输入：
# ["area","lead","wall","lady","ball"]
#
# 输出：
# [
#   [ "wall",
#     "area",
#     "lead",
#     "lady"
#   ],
#   [ "ball",
#     "area",
#     "lead",
#     "lady"
#   ]
# ]
#
# 解释：
# 输出包含两个单词方块，输出的顺序不重要，只需要保证每个单词方块内的单词顺序正确即可。
#
#
#
#
#  示例 2：
#
#  输入：
# ["abat","baba","atan","atal"]
#
# 输出：
# [
#   [ "baba",
#     "abat",
#     "baba",
#     "atan"
#   ],
#   [ "baba",
#     "abat",
#     "baba",
#     "atal"
#   ]
# ]
#
# 解释：
# 输出包含两个单词方块，输出的顺序不重要，只需要保证每个单词方块内的单词顺序正确即可。
#
#
#
#  Related Topics 字典树 回溯算法
#  👍 38 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class TrieNode:
    def __init__(self):
        self.children = {}
        self.is_word = False
        self.startWith = []  # 保留当前前缀下的单词集合


class Trie:
    def __init__(self):
        self.root = TrieNode()

    def add(self, word):
        node = self.root
        for c in word:
            if c not in node.children:
                node.children[c] = TrieNode()
            node = node.children[c]
            node.startWith.append(word)  # ❤ 动态插入的结点均为该单词的前缀
        node.is_word = True

    def search(self, word):
        node = self.root
        for c in word:
            node = node.children.get(c)
            if not node:
                return None
        return node  # 找到则返回叶子，便于后续判断is_word

    def get_words_with_prefix(self, prefix):
        node = self.search(prefix)
        return [] if not node else node.startWith

    def contains(self, whole_word):
        node = self.search(whole_word)
        return node is not None and node.is_word


class Solution:
    def wordSquares(self, words: List[str]) -> List[List[str]]:
        trie = Trie()
        for word in words:
            trie.add(word)

        rst = []
        for word in words:
            self.dfs(trie, [word], rst)
        return rst

    def dfs(self, trie: Trie, square: List[str], rst):
        n_col, row_i = len(square[0]), len(square)
        if row_i == n_col:
            rst.append(list(square))
            return

        # ❤❤❤ 【剪枝Pruning】 it's ok to remove it, but will be slower
        # ❤ 提前预判 当前行以下[row_i, n_col]的、长度为row_i的单词是否为前缀，
        # ❤          如果不是 就没必要继续下探了！！！
        for col_j in range(row_i, n_col):
            prefix = ''.join([square[i][col_j] for i in range(row_i)])
            if trie.search(prefix) is None:
                return
        # dfs下探
        prefix = ''.join([square[i][row_i] for i in range(row_i)])  # 纵向前缀
        for word in trie.get_words_with_prefix(prefix):
            # square.append(word)
            self.dfs(trie, square + [word], rst)
            # square.remove(word)

# leetcode submit region end(Prohibit modification and deletion)
