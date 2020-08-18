# 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。 
# 
#  示例： 
# 
#  输入： arr = [1,3,5,7,2,4,6,8], k = 4
# 输出： [1,2,3,4]
#  
# 
#  提示： 
# 
#  
#  0 <= len(arr) <= 100000 
#  0 <= k <= min(100000, len(arr)) 
#  
#  Related Topics 堆 排序 分治算法 
#  👍 22 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class Solution:
    def smallestK(self, arr: List[int], k: int) -> List[int]:
        if not arr or k <= 0: return []
        import heapq
        max_heap = []  # 最【大】堆
        for num in arr:
            heapq.heappush(max_heap, -num)
            if len(max_heap) > k:  # 【pop较大】的数，剩余的就是需要的k个较小数
                heapq.heappop(max_heap)
        max_heap = list(map(lambda x: -x, max_heap))
        max_heap.sort(reverse=False)  # 默认 升序排列
        return max_heap

# leetcode submit region end(Prohibit modification and deletion)
