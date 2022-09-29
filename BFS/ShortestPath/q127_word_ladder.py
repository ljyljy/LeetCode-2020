# 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
#
#
#
#  每次转换只能改变一个字母。
#  转换过程中的中间单词必须是字典中的单词。
#
#
#  说明:
#
#
#  如果不存在这样的转换序列，返回 0。
#  所有单词具有相同的长度。
#  所有单词只由小写字母组成。
#  字典中不存在重复的单词。
#  你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
#
#
#  示例 1:
#
#  输入:
# beginWord = "hit",
# endWord = "cog",
# wordList = ["hot","dot","dog","lot","log","cog"]
#
# 输出: 5
#
# 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
#      返回它的长度 5。
#
#
#  示例 2:
#
#  输入:
# beginWord = "hit"
# endWord = "cog"
# wordList = ["hot","dot","dog","lot","log"]
#
# 输出: 0
#
# 解释: endWord "cog" 不在字典中，所以无法进行转换。
#  Related Topics 广度优先搜索


# leetcode submit region begin(Prohibit modification and deletion)
from collections import deque
from typing import List


class Solution:

    def ladderLength(self, start: str, end: str, wordList: List[str]) -> int:
        word_set = set()
        for word in wordList:
            word_set.add(word)
        word_len = len(start)
        queue = deque([(start, 1)])  # 勿漏'[]', 形如[root]
        while queue:
            cur_word, cur_len = queue.popleft()
            if cur_word == end:
                return cur_len
            for i in range(word_len):
                left = cur_word[:i]  # 取出当前单词左部分【左边切片可以为空，此时j为cur_word[0]】
                right = cur_word[i + 1:]  # 取出当前单词右部分
                for j in 'abcdefghijklmnopqrstuvwxyz':  # 枚举替换字母
                    if cur_word[i] == j:  # 相同则不替换【从idx=0开始！】
                        continue
                    new_word = left + j + right
                    if new_word in word_set:  # 勿写成wordList!
                        queue.append((new_word, cur_len + 1))  # 加入候选队列
                        word_set.remove(new_word)
        return 0

# leetcode submit region end(Prohibit modification and deletion)
