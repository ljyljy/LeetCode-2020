# 有重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合。
#
#  示例1:
#
#   输入：S = "qqe"
#  输出：["eqq","qeq","qqe"]
#
#
#  示例2:
#
#   输入：S = "ab"
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
#  👍 20 👎 0


# leetcode submit region begin(Prohibit modification and deletion)

class Solution:
    def permutation(self, S: str) -> List[str]:
        if not S: return []
        S_sorted = list(sorted(S))
        used = [False] * len(S)  # [False for _ in range(len(str))]
        rst = []
        self.backtrack(S_sorted, [], used, rst)
        return rst

    def backtrack(self, S_, path, used, rst):
        if len(path) == len(S_):
            rst.append(''.join(path))
            return

        for i in range(len(S_)):
            if used[i]: continue  # 不能跳过一个a选下一个a ↓
            if i > 0 and S_[i] == S_[i - 1] and not used[i - 1]:
                # a' a" b => （1）a' a" b => √ （2） a" a' b => x
                continue
            used[i] = True
            self.backtrack(S_, path + [S_[i]], used, rst)
            used[i] = False

# leetcode submit region end(Prohibit modification and deletion)
