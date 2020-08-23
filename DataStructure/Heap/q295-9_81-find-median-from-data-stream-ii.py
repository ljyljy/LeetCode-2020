# 	Q9_81. 数据流中位数
# 数字是不断进入数组的，在每次添加一个新的数进入数组的同时返回当前新数组的中位数。
# 中位数的定义：
# -	这里的中位数不等同于数学定义里的中位数。
# -	中位数是排序后数组的中间值，如果有数组中有n个数，则中位数为A[(n−1)/2]。
# -	比如：数组A=[1,2,3]的中位数是2，数组A=[1,19]的中位数是1。
# 挑战：时间复杂度为O(nlogn)？
# 例 1:
# 输入: [1,2,3,4,5]
# 输出: [1,1,2,2,3]
# 样例说明：
# [1] 和 [1,2] 的中位数是 1.
# [1,2,3] 和 [1,2,3,4] 的中位数是 2.
# [1,2,3,4,5] 的中位数是 3.
# 样例2：
# 输入: [4,5,1,3,2,6,0]
# 输出: [4,4,4,3,3,3,3]
# 样例说明：
# [4], [4,5] 和 [4,5,1] 的中位数是 4.
# [4,5,1,3], [4,5,1,3,2], [4,5,1,3,2,6] 和 [4,5,1,3,2,6,0] 的中位数是 3.
# 相关题目：685. 数据流中第一个唯一的数字360. 滑窗中位数642. 数据流滑窗平均值80. 中位数65. 两个排序数组的中位数

# %% 思路
# 把比 median 小的放在 maxheap 里，把比 median 大的放在 minheap 里。median 单独放在一个变量里。 每新增一个数，先根据比当前的 median 大还是小丢到对应的 heap 里。
# 再处理左右两边的平衡性: 如果左边太少了，就把 median 丢到左边，从右边拿一个最小的出来作为 median。 如果右边太少了，就把 median 丢到右边，从左边拿一个最大的出来作为新的 median。

import heapq


class Solution:
    def medianII(self, nums):
        if not nums: return []
        # self.median = nums[0]
        self.max_heap = []  # 大顶堆（<=中位数的数）
        self.min_heap = []  # 小顶堆（>中位数的数）
        rst = []
        for num in nums:
            med = self._add(num)
            rst.append(med)
        return rst

    def _add(self, num):
        heapq.heappush(self.max_heap, -num)
        if self.min_heap and -self.max_heap[0] > self.min_heap[0]:
            maxv, minv = heapq.heappop(self.max_heap), heapq.heappop(self.min_heap)
            heapq.heappush(self.min_heap, -maxv)
            heapq.heappush(self.max_heap, -minv)
        if len(self.max_heap) > len(self.min_heap) + 1:
            heapq.heappush(self.min_heap, -heapq.heappop(self.max_heap))

        return -self.max_heap[0]
