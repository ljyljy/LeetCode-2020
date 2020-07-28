# 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
#
#
#
#  示例 1：
#
#  输入：s = "We are happy."
# 输出："We%20are%20happy."
#
#
#
#  限制：
#
#  0 <= s 的长度 <= 10000
#  👍 26 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def replaceSpace(self, s: str) -> str:
        if not isinstance(s, str) or not s:
            return ''  # 不可加空格
        n_space = 0
        for ch in s:
            if ch == " ": n_space += 1

        new_len = len(s) + n_space * 2
        new_str = [''] * new_len  # 生成新长度的空数组
        old, new = len(s) - 1, new_len - 1
        while new >= 0 and old <= new:
            if s[old] == ' ':
                new_str[new - 2: new + 1] = ['%', '2', '0']
                new -= 3
                old -= 1
            else:
                new_str[new] = s[old]
                new -= 1
                old -= 1
        return ''.join(new_str)  # 数组转字符串 # 不可加空格！！！！
# leetcode submit region end(Prohibit modification and deletion)
