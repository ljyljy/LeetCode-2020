# 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"0123"都表示数值，但
# "12e"、"1a3.14"、"1.2.3"、"+-5"、"-1E-16"及"12e+5.4"都不是。
#
#
#
#  注意：本题与主站 65 题相同：https://leetcode-cn.com/problems/valid-number/
#  Related Topics 数学
#  👍 35 👎 0


# leetcode submit region begin(Prohibit modification and deletion)

# Py的五种解法
# https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/solution/onpythonjie-ti-wu-fa-luo-ji-pan-duan-regexdfadeng-/
class Solution:
    def isNumber(self, s: str) -> bool:
        import re
        # 使用Pattern匹配文本，获得匹配结果，无法匹配时将返回None
        # '^': 匹配开头；'$'匹配结尾
        pat = re.compile(r'^[+-]?(\.\d+|\d+\.?\d*)([eE][+-]?\d+)?$')
        return bool(pat.match(s.strip()))  # 勿忘清除s中的空格！

# leetcode submit region end(Prohibit modification and deletion)
