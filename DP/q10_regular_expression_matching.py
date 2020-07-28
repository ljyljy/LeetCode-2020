# 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
#
#  '.' 匹配任意单个字符
# '*' 匹配零个或多个前面的那一个元素
#
#
#  所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
#
#  说明:
#
#
#  s 可能为空，且只包含从 a-z 的小写字母。
#  p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
#
#
#  示例 1:
#
#  输入:
# s = "aa"
# p = "a"
# 输出: false
# 解释: "a" 无法匹配 "aa" 整个字符串。
#
#
#  示例 2:
#
#  输入:
# s = "aa"
# p = "a*"
# 输出: true
# 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
#
#
#  示例 3:
#
#  输入:
# s = "ab"
# p = ".*"
# 输出: true
# 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
#
#
#  示例 4:
#
#  输入:
# s = "aab"
# p = "c*a*b"
# 输出: true
# 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
#
#
#  示例 5:
#
#  输入:
# s = "mississippi"
# p = "mis*is*p*."
# 输出: false
#  Related Topics 字符串 动态规划 回溯算法
#  👍 1408 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    # memo备忘录，传参: 俩字符串下标
    def isMatch(self, src: str, pat: str) -> bool:
        memo = dict()

        def dp(i, j):  # i,j: src, pat下标∈[1, len(str)-1]
            if (i, j) in memo: return memo[(i, j)]
            if j == len(pat): return i == len(src)
            first = i < len(src) and pat[j] in {src[i], '.'}
            if j <= len(pat) - 2 and pat[j + 1] == '*':  # 勿忘<=的等号！
                res = dp(i, j + 2) or \
                      first and dp(i + 1, j)
            else:
                res = first and dp(i + 1, j + 1)
            memo[(i, j)] = res
            return res

        return dp(0, 0)

    # 法2：暴力递归（传参: 字符串切片--copy）
    def isMatch2(self, text, pattern) -> bool:
        if not pattern: return not text

        first = bool(text) and pattern[0] in {text[0], '.'}

        if len(pattern) >= 2 and pattern[1] == '*':
            return self.isMatch2(text, pattern[2:]) or \
                   first and self.isMatch2(text[1:], pattern)
        else:
            return first and self.isMatch2(text[1:], pattern[1:])

# leetcode submit region end(Prohibit modification and deletion)
