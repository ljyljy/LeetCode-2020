# 给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。现在从这两个数组中选出 k (k <= m + n) 个数字拼接
# 成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
#
#  求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
#
#  说明: 请尽可能地优化你算法的时间和空间复杂度。
#
#  示例 1:
#
#  输入:
# nums1 = [3, 4, 6, 5]
# nums2 = [9, 1, 2, 5, 8, 3]
# k = 5
# 输出:
# [9, 8, 6, 5, 3]
#
#  示例 2:
#
#  输入:
# nums1 = [6, 7]
# nums2 = [6, 0, 4]
# k = 5
# 输出:
# [6, 7, 6, 0, 4]
#
#  示例 3:
#
#  输入:
# nums1 = [3, 9]
# nums2 = [8, 9]
# k = 3
# 输出:
# [9, 8, 9]
#  Related Topics 贪心算法 动态规划
#  👍 205 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class Solution:
    def maxNumber(self, nums1: List[int], nums2: List[int], k: int) -> List[int]:
        # k ∈ [0, len]
        def pick_max(nums, k):
            stack, drop = [], len(nums) - k
            for num in nums:
                while drop and stack and num > stack[-1]:
                    stack.pop()
                    drop -= 1
                stack.append(num)
            return stack[:k]

        def merge(A, B):
            rst = []
            while A or B:
                bigger = A if A > B else B  # 引用！非copy()
                rst.append(bigger[0])
                bigger.pop(0)  # 同时修改了原数组
            return rst

        # ans = []
        # for i in range(k+1):  # ∵子数组num1/2可取元素数目∈[0, k]
        #     if i <= len(nums1) and k-i <= len(nums2):
        #         num1, num2 = pick_max(nums1, i), pick_max(nums2, k-i)
        #         # print(f'num1={num1}, num2={num2}')
        #         ans.append(merge(num1, num2))
        #
        # return max(ans)
        ### 一句话解决以上注释句 ↑(推荐！因为快很多！！！)
        return max(merge(pick_max(nums1, i), pick_max(nums2, k - i)) for i in range(k + 1) if
                   i <= len(nums1) and k - i <= len(nums2))


sol = Solution()
nums1 = [3, 4, 6, 5]
nums2 = [9, 1, 2, 5, 8, 3]
k = 5
print(sol.maxNumber(nums1, nums2, k))
# leetcode submit region end(Prohibit modification and deletion)
