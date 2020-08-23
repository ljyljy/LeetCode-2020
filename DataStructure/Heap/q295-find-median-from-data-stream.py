# 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数
# 值排序之后中间两个数的平均值。
#
#  例如，
#
#  [2,3,4] 的中位数是 3
#
#  [2,3] 的中位数是 (2 + 3) / 2 = 2.5
#
#  设计一个支持以下两种操作的数据结构：
#
#
#  void addNum(int num) - 从数据流中添加一个整数到数据结构中。
#  double findMedian() - 返回目前所有元素的中位数。
#
#
#  示例 1：
#
#  输入：
# ["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
# [[],[1],[2],[],[3],[]]
# 输出：[null,null,null,1.50000,null,2.00000]
#
#
#  示例 2：
#
#  输入：
# ["MedianFinder","addNum","findMedian","addNum","findMedian"]
# [[],[2],[],[3],[]]
# 输出：[null,null,2.00000,null,2.50000]
#
#
#
#  限制：
#
#
#  最多会对 addNum、findMedia进行 50000 次调用。
#
#
#  注意：本题与主站 295 题相同：https://leetcode-cn.com/problems/find-median-from-data-strea
# m/
#  Related Topics 堆 设计
#  👍 53 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
import heapq


class MedianFinder:

    def __init__(self):
        self.min_heap = []  # 小顶堆（>=中位数的数）
        self.max_heap = []  # 大顶堆（<中位数的数）

    def addNum(self, num: int) -> None:
        heapq.heappush(self.max_heap, -num)
        # 若大顶堆的max比小顶堆min还大，则换顶。
        if self.min_heap and -self.max_heap[0] > self.min_heap[0]:
            maxv, minv = heapq.heappop(self.max_heap), heapq.heappop(self.min_heap)
            heapq.heappush(self.min_heap, -maxv)
            heapq.heappush(self.max_heap, -minv)
        if len(self.max_heap) > len(self.min_heap) + 1:
            heapq.heappush(self.min_heap, -heapq.heappop(self.max_heap))

    def findMedian(self) -> float:
        if (len(self.max_heap) + len(self.min_heap)) & 1:  # 元素个数为奇数
            return -self.max_heap[0]
        else:
            return float(-self.max_heap[0] + self.min_heap[0]) / 2.0

# Your MedianFinder object will be instantiated and called as such:
# obj = MedianFinder()
# obj.addNum(num)
# param_2 = obj.findMedian()
# leetcode submit region end(Prohibit modification and deletion)
