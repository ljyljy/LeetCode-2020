# 给定一个可包含重复数字的序列，返回所有不重复的全排列。
#
#  示例:
#
#  输入: [1,1,2]
# 输出:
# [
#   [1,1,2],
#   [1,2,1],
#   [2,1,1]
# ]
#  Related Topics 回溯算法
#  👍 361 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class Solution:
    def permuteUnique(self, nums: List[int]) -> List[List[int]]:
        if not nums: return []
        nums = sorted(nums)  # 去重1：排序
        used, rst = [False for _ in range(len(nums))], []
        self.dfs(nums, 0, [], used, rst)
        return rst

    def dfs(self, nums, depth, path, used, rst):
        if depth == len(nums):
            rst.append(list(path))
            return
        for i in range(len(nums)):
            if used[i]: continue
            # 去重2：重复且前一个相同元素未使用过(也可以不加not去重另一部分)
            if i > 0 and nums[i] == nums[i - 1] and not used[i - 1]:
                continue
            used[i] = True
            self.dfs(nums, depth + 1, path + [nums[i]], used, rst)
            used[i] = False  # 回溯

# leetcode submit region end(Prohibit modification and deletion)
