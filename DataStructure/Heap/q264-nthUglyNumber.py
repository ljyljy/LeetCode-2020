# 编写一个程序，找出第 n 个丑数。
#
#  丑数就是质因数只包含 2, 3, 5 的正整数。
#
#  示例:
#
#  输入: n = 10
# 输出: 12
# 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
#
#  说明:
#
#
#  1 是丑数。
#  n 不超过1690。
#
#  Related Topics 堆 数学 动态规划
#  👍 355 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def nthUglyNumber(self, n: int) -> int:
        import heapq

        heap, visited = [1], set([1])
        rst = None
        for _ in range(n):
            rst = heapq.heappop(heap)
            for factor in [2, 3, 5]:
                if rst * factor in visited:
                    continue
                heapq.heappush(heap, rst * factor)
                visited.add(rst * factor)
        return rst

# 判断num是否为丑数：
#     def isUgly(self, num: int) -> bool:
#         if num < 1: return False
#         if num == 1: return True  # 1是丑数
#
#         while num >= 2 and num % 2 == 0: num /= 2
#         while num >= 3 and num % 3 == 0: num /= 3
#         while num >= 5 and num % 5 == 0: num /= 5
#         return num == 1
# leetcode submit region end(Prohibit modification and deletion)
