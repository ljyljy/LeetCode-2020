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

# 	ACW786. 第K个数
# 给定一个长度为n的整数数列，以及一个整数k，请用快速选择算法求出数列从小到大排序后的第k个数。
# 输入格式
# 第一行包含两个整数 n 和 k。
# 第二行包含 n 个整数（所有整数均在1~109109范围内），表示整数数列。
# 输出格式
# 输出一个整数，表示数列的第k小数。
# 输入样例：
# 5 3
# 2 4 1 5 3
# 输出样例：
# 3
# def tok_k_smallest_num(arr, k):
#     import heapq
#     if not arr or k <= 0: return []
#     max_heap = []
#     for num in arr:
#         heapq.heappush(max_heap, -num)
#         if len(max_heap) > k:
#             heapq.heappop(max_heap)
#     # max_heap = list(map(lambda x: -x, max_heap))
#     # max_heap.sort(reverse=False)
#     return -max_heap[0] # -heapq.heappop(max_heap)  # max_heap[0]: 第k小的数
#
# n, k = list(map(int, input().split()))
# arr = list(map(int, input().split()))
# ans = tok_k_smallest_num(arr, k)
# print(ans)
