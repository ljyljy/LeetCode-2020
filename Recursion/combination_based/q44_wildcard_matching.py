# 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。 
# 
#  '?' 可以匹配任何单个字符。
# '*' 可以匹配任意字符串（包括空字符串）。
#  
# 
#  两个字符串完全匹配才算匹配成功。 
# 
#  说明: 
# 
#  
#  s 可能为空，且只包含从 a-z 的小写字母。 
#  p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。 
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
#  示例 2: 
# 
#  输入:
# s = "aa"
# p = "*"
# 输出: true
# 解释: '*' 可以匹配任意字符串。
#  
# 
#  示例 3: 
# 
#  输入:
# s = "cb"
# p = "?a"
# 输出: false
# 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
#  
# 
#  示例 4: 
# 
#  输入:
# s = "adceb"
# p = "*a*b"
# 输出: true
# 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
#  
# 
#  示例 5: 
# 
#  输入:
# s = "acdcb"
# p = "a*c?b"
# 输出: false 
#  Related Topics 贪心算法 字符串 动态规划 回溯算法 
#  👍 479 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        return self._is_match_helper(s, 0, p, 0, {})

    # source 从 i 开始的后缀能否匹配上 pattern 从 j 开始的后缀
    # 能 return True
    def _is_match_helper(self, s, i, p, j, memo):
        if (i, j) in memo:
            return memo[(i, j)]
        if i == len(s):  # every character should be "*"
            for jj in range(j, len(p)):
                if p[jj] != '*':
                    return False
            return True
        if j == len(p): return False

        if p[j] != '*':
            matched = self._is_match_char(s[i], p[j]) and \
                      self._is_match_helper(s, i + 1, p, j + 1, memo)
        else:  # '*'未匹配
            matched = self._is_match_helper(s, i, p, j + 1, memo) or \
                      self._is_match_helper(s, i + 1, p, j, memo)  # 匹配成功， '*'继续匹配下一个s[i+1]
        memo[(i, j)] = matched
        return matched

    def _is_match_char(self, ch_s, ch_p):
        return ch_s == ch_p or ch_p == '?'

# ps: 嵌套list不是数组，且无法切片取出指定列！-> list1[i]: 取出第i行
# [aa[i][1] for i in range(len(aa))] --> 取出aa所有行的第1列（取出指定列，无法切片操作）
#  aa[k] = [True]*len(aa[0])   # 且不可直接写True（值），必须[True]*len才是列表！！！❤❤❤ 否则k行将变为一个值

# leetcode submit region end(Prohibit modification and deletion)
