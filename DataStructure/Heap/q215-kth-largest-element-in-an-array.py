# 	Q9_5. 第K大数
# 思路：最小堆 – pop出前k-1个数，剩下堆中的第一个就是第k大
# 	Q215. 数组中的第K大元素
# 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
# 示例 1:
# 输入: [3,2,1,5,6,4] 和 k = 2
# 输出: 5
# 示例 2:
# 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
# 输出: 4
# 挑战：要求时间复杂度为O(n)，空间复杂度为O(1)。
# 相关题目 1281. 前K个高频元素606. 第K大的元素 II507. 摆动排序 II461. 无序数组K小元素401. 排序矩阵中的从小到大第k个数80. 中位数

class Solution:
    # 法1：最小堆 -- O(nlogk) – pop出前k-1个数，剩下堆中的第一个就是第k大
    def findKthLargest1(self, k, nums):
        import heapq
        if not nums or k <= 0: return None
        min_heap = []
        for num in nums:
            heapq.heappush(min_heap, num)
            if len(min_heap) > k:
                heapq.heappop(min_heap)
        return min_heap[0]

    # 法2：快速选择 1 -- O(n)
    class Solution:
        def findKthLargest0(self, nums: List[int], k: int) -> int:
            import heapq
            if not nums or k <= 0: return None
            min_heap = []
            for num in nums:
                heapq.heappush(min_heap, num)
                if len(min_heap) > k:
                    heapq.heappop(min_heap)
            return min_heap[0]

        # 法2：快速选择 1 -- O(n)
        def findKthLargest(self, nums, k):
            # nums_set = list(set(nums))
            n = len(nums)
            self.quick_select_v1(nums, 0, n - 1)  # 无需去重！
            print(nums)
            return nums[-k]  # 升序的倒数第k个

        def quick_select_v1(self, arr, left, right):
            if left >= right: return
            i, j, pivot = left, right, arr[left + right >> 1]
            while i <= j:
                while i <= j and arr[i] < pivot: i += 1
                while i <= j and arr[j] > pivot: j -= 1
                if i <= j:
                    arr[i], arr[j] = arr[j], arr[i]
                    i += 1
                    j -= 1
            self.quick_select_v1(arr, left, j)
            self.quick_select_v1(arr, i, right)
