from typing import List


class Solution:
    def partition(self, s: str) -> List[List[str]]:
        if not s: return []
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
