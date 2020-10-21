# 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
#
#  比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
#
#  L   C   I   R
# E T O E S I I G
# E   D   H   N
#
#
#  之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
#
#  请你实现这个将字符串进行指定行数变换的函数：
#
#  string convert(string s, int numRows);
#
#  示例 1:
#
#  输入: s = "LEETCODEISHIRING", numRows = 3
# 输出: "LCIRETOESIIGEDHN"
#
#
#  示例 2:
#
#  输入: s = "LEETCODEISHIRING", numRows = 4
# 输出: "LDREOEIIECIHNTSG"
# 解释:
#
# L     D     R
# E   O E   I I
# E C   I H   N
# T     S     G
#  Related Topics 字符串
#  👍 871 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def convert(self, s: str, n: int) -> str:
        res = ''
        if not str or n <= 0: return res
        if n <= 1 or len(s) <= n: return s
        offset = (n - 1) << 1  # 2*n - 2
        for i in range(n):
            if i == 0 or i == n - 1:  # 首尾两行为等差序列(公差=2n-2)
                for j in range(i, len(s), offset):
                    res += s[j]
            else:
                for j, k in zip(range(i, len(s) + offset, offset), range(2 * n - 2 - i, len(s) + offset, offset)):
                    if j < len(s): res += s[j]
                    if k < len(s): res += s[k]
        return res
# leetcode submit region end(Prohibit modification and deletion)
