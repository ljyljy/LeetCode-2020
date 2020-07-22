# 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
#
#  candidates 中的每个数字在每个组合中只能使用一次。
#
#  说明：
#
#
#  所有数字（包括目标数）都是正整数。
#  解集不能包含重复的组合。
#
#
#  示例 1:
#
#  输入: candidates = [10,1,2,7,6,1,5], target = 8,
# 所求解集为:
# [
#   [1, 7],
#   [1, 2, 5],
#   [2, 6],
#   [1, 1, 6]
# ]
#
#
#  示例 2:
#
#  输入: candidates = [2,5,2,1,2], target = 5,
# 所求解集为:
# [
#   [1,2,2],
#   [5]
# ]
#  Related Topics 数组 回溯算法
#  👍 312 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class Solution:
    def combinationSum2(self, candidates: List[int], target: int) -> List[List[int]]:
        rst, use = [], [0] * len(candidates)  # 标记每一个数是否用过（用于有重复数字时）
        if not candidates: return rst
        candidates.sort()  # 为了dfs中可以剪枝
        self.dfs(candidates, target, 0, [], rst, use)
        return rst

    def dfs(self, candidates, target_remain, idx, cur_path, rst, use):
        if target_remain == 0:
            rst.append(list(cur_path))
            return
        for i in range(idx, len(candidates)):
            if target_remain < candidates[i]: break  # 剪枝
            # 若前后元素重复 & 前者已用过，便依旧可以遍历当前(重复的后者)↓
            if i == 0 or candidates[i - 1] != candidates[i] or use[i - 1] == 1:
                use[i] = 1
                cur_path.append(candidates[i])  # 由于can[]中元素只能 ↓ 使用一次，故每层i+1
                self.dfs(candidates, target_remain - candidates[i], i + 1, cur_path, rst, use)
                cur_path.pop()
                use[i] = 0

    # Follow up: 如果

# leetcode submit region end(Prohibit modification and deletion)
