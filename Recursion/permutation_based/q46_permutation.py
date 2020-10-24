# 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
#
#  示例:
#
#  输入: [1,2,3]
# 输出:
# [
#   [1,2,3],
#   [1,3,2],
#   [2,1,3],
#   [2,3,1],
#   [3,1,2],
#   [3,2,1]
# ]
#  Related Topics 回溯算法
#  👍 809 👎 0

# 回溯模板
# for 选择 in 选择列表:
#     # 做选择
#     将该选择从选择列表移除
#     路径.add(选择)
#     backtrack(路径, 选择列表)
#     # 撤销选择
#     路径.remove(选择)
#     将该选择再加入选择列表

# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        if not nums: return []
        used, rst = [False for _ in range(len(nums))], []
        self.dfs(nums, 0, [], used, rst)
        return rst

    def dfs(self, nums, depth, path, used, rst):
        n = len(nums)
        if depth == n:
            rst.append(list(path))
            return
        for i in range(n):
            if used[i]:  # 选择列表（True表示已选择过）
                continue
            used[i] = True
            # path.append(nums[i])
            # self.dfs(nums, depth+1, path, used, rst)
            self.dfs(nums, depth + 1, path + [nums[i]], used, rst)
            # path.pop()
            used[i] = False


if __name__ == "__main__":
    nums = [1, 2, 3]
    sol = Solution()
    rst = sol.permute(nums)
    print(rst)

# leetcode submit region end(Prohibit modification and deletion)
