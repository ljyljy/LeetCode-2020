# 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
#
#  返回 s 所有可能的分割方案。
#
#  示例:
#
#  输入: "aab"
# 输出:
# [
#   ["aa","b"],
#   ["a","a","b"]
# ]
#  Related Topics 回溯算法
#  👍 326 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class Solution:
    # 法2：dfs + memo
    def partition(self, s: str) -> List[List[str]]:
        return self.helper(s, {})

    def helper(self, s, memo):
        if s in memo: return memo[s]
        if s == "": return []
        partitions = []

        for i in range(1, len(s) + 1):
            pre, nextt = s[:i], s[i:]
            if not self._is_palindrome(pre):
                continue

            sub_partitions = self.helper(nextt, memo)
            for sp in sub_partitions:
                partitions.append([pre] + sp)

        if self._is_palindrome(s):
            partitions.append([s])  # 勿忘'[]'!!!

        memo[s] = partitions
        return memo[s]

    def _is_palindrome(self, substr):
        return substr == substr[::-1]

    # 法1：dfs
    def partition1(self, s: str) -> List[List[str]]:
        # if not s: return []
        rst = []
        self.dfs(s, [], rst)
        return rst

    # 在原字符串s上直接操作，可以省去dfs中idx的传参
    # 否则可能需要记录每次切割的idx和上一次的idx_last以分割新字符串（似乎不好操作）
    def dfs(self, s, path, rst):
        if len(s) == 0:
            rst.append(list(path))
            return
        # 插空/分割下标i∈[1, len(s)]
        for i in range(1, len(s) + 1):
            pre = s[:i]
            if self.is_palindrome(pre):
                path.append(pre)
                self.dfs(s[i:], path, rst)
                path.pop()

    def is_palindrome(self, substr):
        return substr == substr[::-1]

# leetcode submit region end(Prohibit modification and deletion)
