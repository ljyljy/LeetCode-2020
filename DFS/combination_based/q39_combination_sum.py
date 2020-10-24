# 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
#
#  candidates 中的数字可以无限制重复被选取。
#
#  说明：
#
#
#  所有数字（包括 target）都是正整数。
#  解集不能包含重复的组合。
#
#
#  示例 1：
#
#  输入：candidates = [2,3,6,7], target = 7,
# 所求解集为：
# [
#   [7],
#   [2,2,3]
# ]
#
#
#  示例 2：
#
#  输入：candidates = [2,3,5], target = 8,
# 所求解集为：
# [
#   [2,2,2,2],
#   [2,3,3],
#   [3,5]
# ]
#
#
#
#  提示：
#
#
#  1 <= candidates.length <= 30
#  1 <= candidates[i] <= 200
#  candidate 中的每个元素都是独一无二的。
#  1 <= target <= 500
#
#  Related Topics 数组 回溯算法
#  👍 766 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class Solution:
    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
        rst = []
        if not candidates: return rst
        # 利用set去重后排序【candidates中可能有重复数字】
        canNew = sorted(list(set(candidates)))
        self.dfs(canNew, target, 0, [], rst)
        return rst

    def dfs(self, canNew, target_remain, idx, cur_path, rst):
        if target_remain == 0:
            rst.append(list(cur_path))
            return
        # 递归的拆解：挑一个数放入 cur_path
        for i in range(idx, len(canNew)):
            # 剪枝：当发现当前的数字加入已超过remainTarget可进行剪枝。
            if target_remain < canNew[i]:
                break
            cur_path.append(canNew[i])
            # 与subsets不同之处：递归时idx无需每层+1，原因：
            #  -- 因为数字可以重复使用，即canNew[i]可以在多轮中保持不变
            self.dfs(canNew, target_remain - canNew[i], i, cur_path, rst)
            cur_path.pop()  # 注意这里是i！不是idx！（↑否则会导致rst重复）

# leetcode submit region end(Prohibit modification and deletion)
