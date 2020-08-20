# 给一非空的单词列表，返回前 k 个出现次数最多的单词。
#
#  返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
#
#  示例 1：
#
#
# 输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
# 输出: ["i", "love"]
# 解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
#     注意，按字母顺序 "i" 在 "love" 之前。
#
#
#
#
#  示例 2：
#
#
# 输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k
#  = 4
# 输出: ["the", "is", "sunny", "day"]
# 解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
#     出现次数依次为 4, 3, 2 和 1 次。
#
#
#
#
#  注意：
#
#
#  假定 k 总为有效值， 1 ≤ k ≤ 集合元素数。
#  输入的单词均由小写字母组成。
#
#
#
#
#  扩展练习：
#
#
#  尝试以 O(n log k) 时间复杂度和 O(n) 空间复杂度解决。
#
#  Related Topics 堆 字典树 哈希表
#  👍 136 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
import heapq
from collections import defaultdict

from typing import List


class Item:
    def __init__(self, count, word):
        self.count = count
        self.word = word

    # compare函数: less than
    # 最小堆：每次pop较小的（词频小 || 【字典序大】 -- 需要对字典序取反）
    def __lt__(self, other):
        if self.count == other.count:
            return not self.word < other.word  # pop【字典序大】的
        return self.count < other.count  # pop 【词频小】的


class Solution:
    def topKFrequent(self, words: List[str], k: int) -> List[str]:
        if not words or len(words) < k: return
        mapping = defaultdict(int)  # Hash {word: 词频cnt}
        min_heap = []  # 每次【pop频次小/字母序大】的元素
        # ↑ 由于heappop实现的是最小堆，故频次√ 字母序还需取反
        for word in words:
            mapping[word] += 1  # 词频
        for word in mapping:
            item = Item(mapping[word], word)
            heapq.heappush(min_heap, item)
            # 最小堆：每次pop 词频小 or [取负(字典序大)]的元素
            if len(min_heap) > k:  # 堆内保持k个元素，这样push可以保证O(nlogk)，而非O(nlogn)
                heapq.heappop(min_heap)
        rst = []
        while min_heap:
            rst.append(heapq.heappop(min_heap).word)
        return rst[::-1]  # 最小堆pop出来的需要取反，实现升序排列
# leetcode submit region end(Prohibit modification and deletion)
