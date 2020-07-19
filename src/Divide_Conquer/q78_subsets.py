# 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
#
#  说明：解集不能包含重复的子集。
#
#  示例:
#
#  输入: nums = [1,2,3]
# 输出:
# [
#   [3],
#   [1],
#   [2],
#   [1,2,3],
#   [1,3],
#   [2,3],
#   [1,2],
#   []
# ]
#  Related Topics 位运算 数组 回溯算法


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    # 1.
    def subsets(self, nums: List[int]) -> List[List[int]]:
        res = [[]]
        for num in nums:
            res = res + [[num] + r for r in res]
        return res

    # 2.
    def subsets2(self, nums: List[int]) -> List[List[int]]:
        res = [[]]
        for num in nums:
            newsets = []
            for r in res:
                new_r = r + [num]
                newsets.append(new_r)
            res.extend(newsets)
        return res
# leetcode submit region end(Prohibit modification and deletion)
