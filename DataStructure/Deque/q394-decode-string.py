# 给定一个经过编码的字符串，返回它解码后的字符串。
#
#  编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
#
#  你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
#
#  此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
#
#
#
#  示例 1：
#
#  输入：s = "3[a]2[bc]"
# 输出："aaabcbc"
#
#
#  示例 2：
#
#  输入：s = "3[a2[c]]"
# 输出："accaccacc"
#
#
#  示例 3：
#
#  输入：s = "2[abc]3[cd]ef"
# 输出："abcabccdcdcdef"
#
#
#  示例 4：
#
#  输入：s = "abc3[cd]xyz"
# 输出："abccdcdcdxyz"
#
#  Related Topics 栈 深度优先搜索
#  👍 567 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from collections import deque


class Solution:
    # 法1：辅助栈，类比q1190
    def decodeString(self, s: str) -> str:
        stack, res, num = deque([]), '', 0
        for c in s:
            if c == '[':
                stack.append([num, res])
                res, num = '', 0
            elif c == ']':
                cur_num, last_res = stack.pop()  # (2, 'a')
                res = last_res + res * cur_num  # 'a' + 'c'*2; '' + 'acc'*3
            elif '0' <= c <= '9':  # 可能有2位数及以上
                num = num * 10 + int(c)
            else:
                res += c
        return res

    # 法2：递归
    def decodeString2(self, s: str) -> str:
        def dfs(s, idx):
            res, num = '', 0
            while idx < len(s):
                if '0' <= s[idx] <= '9':
                    num = num * 10 + int(s[idx])
                elif s[idx] == '[':
                    idx, tmp = dfs(s, idx + 1)
                    res += tmp * num
                    num = 0
                elif s[idx] == ']':
                    return idx, res
                else:
                    res += s[idx]
                idx += 1
            return res

        return dfs(s, 0)

# leetcode submit region end(Prohibit modification and deletion)
