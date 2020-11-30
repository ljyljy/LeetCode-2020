# 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
#
#  若可行，输出任意可行的结果。若不可行，返回空字符串。
#
#  示例 1:
#
#
# 输入: S = "aab"
# 输出: "aba"
#
#
#  示例 2:
#
#
# 输入: S = "aaab"
# 输出: ""
#
#
#  注意:
#
#
#  S 只包含小写字母并且长度在[1, 500]区间内。
#
#  Related Topics 堆 贪心算法 排序 字符串
#  👍 154 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from collections import Counter
import heapq


class Solution:
    def reorganizeString(self, S: str) -> str:
        if len(S) < 2: return S
        n, ch_cnt, rst = len(S), Counter(S), []
        max_cnt = max(ch_cnt.items(), key=lambda x: x[1])[1]  # max(ch_cnt.values())
        if max_cnt > (n + 1) // 2: return ""
        # 最大堆：优先pop (计数大, 字母序靠前) 者
        max_heap = [(-x[1], x[0]) for x in ch_cnt.items()]
        heapq.heapify(max_heap)
        # while len(max_heap) > 1:
        #     _, letter1 = heapq.heappop(max_heap)
        #     _, letter2 = heapq.heappop(max_heap)
        #     rst.extend([letter1, letter2])
        #     ch_cnt[letter1] -= 1
        #     ch_cnt[letter2] -= 1
        #     if ch_cnt[letter1] > 0: heapq.heappush(max_heap, (-ch_cnt[letter1], letter1))
        #     if ch_cnt[letter2] > 0: heapq.heappush(max_heap, (-ch_cnt[letter2], letter2))
        #
        # if max_heap: rst.append(max_heap[0][1]) # heapq.heappop(max_heap)[1]
        # return "".join(rst)
        while len(max_heap) > 1:
            cnt1, letter1 = heapq.heappop(max_heap)
            cnt2, letter2 = heapq.heappop(max_heap)
            rst.extend([letter1, letter2])
            cnt1, cnt2 = -cnt1 - 1, -cnt2 - 1
            if cnt1 > 0: heapq.heappush(max_heap, (-cnt1, letter1))
            if cnt2 > 0: heapq.heappush(max_heap, (-cnt2, letter2))

        if max_heap: rst.append(max_heap[0][1])
        return "".join(rst)

    def reorganizeString2(self, S):
        if len(S) < 2: return S
        counter = Counter(S).most_common()  # 1
        _, max_freq = counter[0]
        if max_freq > (len(S) + 1) // 2:
            return ""
        else:
            buckets = [[] for i in range(max_freq)]  # 2
            begin = 0
            for letter, count in counter:
                for i in range(count):
                    buckets[(i + begin) % max_freq].append(letter)  # 3
                begin += count
        return "".join("".join(bucket) for bucket in buckets)  # 4
# leetcode submit region end(Prohibit modification and deletion)
