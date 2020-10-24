# 无重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合，字符串每个字符均不相同。 
# 
#  示例1: 
# 
#  
#  输入：S = "qwe"
#  输出：["qwe", "qew", "wqe", "weq", "ewq", "eqw"]
#  
# 
#  示例2: 
# 
#  
#  输入：S = "ab"
#  输出：["ab", "ba"]
#  
# 
#  提示: 
# 
#  
#  字符都是英文字母。 
#  字符串长度在[1, 9]之间。 
#  
#  Related Topics 回溯算法 
#  👍 21 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class Solution:
    def permutation(self, S: str) -> List[str]:
        if not S: return []
        rst, used = [], [False] * len(S)
        self.dfs(S, [], used, rst)
        return rst

    def dfs(self, S, path, used, rst):
        if len(path) == len(S):
            rst.append("".join(path))
            return

        for i in range(len(S)):
            if used[i]: continue
            used[i] = True
            self.dfs(S, path + [S[i]], used, rst)
            used[i] = False

# leetcode submit region end(Prohibit modification and deletion)
