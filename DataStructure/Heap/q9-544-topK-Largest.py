class Solution:
    def topk(self, nums, k):
        import heapq
        if not nums: return
        heap = []
        for num in nums:
            # 最小堆(要保证大于k个元素时，从heappop的是最小的数)
            heapq.heappush(heap, num)
            if len(heap) > k:
                heapq.heappop(heap)  # pop较小的数
        heap.sort(reverse=True)  # 翻转变成前K大(不可以直接[::-1]!因为要求从小到大排序)
        return heap
