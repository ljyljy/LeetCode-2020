# 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
#
#
#
#  示例 1:
#
#  输入: nums = [1,1,1,2,2,3], k = 2
# 输出: [1,2]
#
#
#  示例 2:
#
#  输入: nums = [1], k = 1
# 输出: [1]
#
#
#
#  提示：
#
#
#  你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
#  你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
#  题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
#  你可以按任意顺序返回答案。
#
#  Related Topics 堆 哈希表
#  👍 439 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        import heapq
        # from collections import defaultdict
        if not nums: return []
        minmax_heap, mapp = [], {}  # defaultdict(int) -- 鲁棒 / dict()
        for num in nums:
            mapp[num] = mapp.get(num, 0) + 1  # 鲁棒：dictt.get(key, 否则返回XXX);
            # mapping[num] += 1 只有collections.defaultdict不会报错！
        for num in mapp:  # 最小堆pop：词频小 & 字典序大(取负)
            heapq.heappush(minmax_heap, (mapp[num], -num))
            if len(minmax_heap) > k:
                heapq.heappop(minmax_heap)
        rst = []
        while minmax_heap:
            rst.append(-heapq.heappop(minmax_heap)[1])  # 恢复num：再次取负
        return rst[::-1]
# leetcode submit region end(Prohibit modification and deletion)
