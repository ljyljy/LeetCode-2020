# 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，
# 则输出"student. a am I"。
#
#
#
#  示例 1：
#
#  输入: "the sky is blue"
# 输出: "blue is sky the"
#
#
#  示例 2：
#
#  输入: "  hello world!  "
# 输出: "world! hello"
# 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
#
#
#  示例 3：
#
#  输入: "a good   example"
# 输出: "example good a"
# 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
#
#
#
#
#  说明：
#
#
#  无空格字符构成一个单词。
#  输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
#  如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
#
#
#  注意：本题与主站 151 题相同：https://leetcode-cn.com/problems/reverse-words-in-a-string/
#
#
#  注意：此题对比原题有改动
#  Related Topics 字符串
#  👍 37 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def reverseWords(self, s: str) -> str:
        if not s or not s.strip(): return ''
        s = s.strip()
        res, s, n = [], s[::-1], len(s)  # 1st翻转-整个str
        i = 0
        while i < n:
            j = i
            while j <= n - 1 and s[j] != ' ':  # 由于strip，故首个单词前无多余空格
                j += 1  # 退出时，j指向' '
            substr = s[i:j]  # [i, j-1]
            substr = substr[::-1]  # 2nd翻转-单词内部
            res.append(substr)
            while j <= n - 1 and s[j] == ' ': j += 1  # 去除单词间多余空格
            i = j
        return ' '.join(res)

    # 语法糖1
    def reverseWords1(self, s: str) -> str:
        s = s.strip()  # 删除首尾空格
        strs = s.split()  # 分割字符串
        strs.reverse()  # 翻转单词列表
        return ' '.join(strs)  # 拼接为字符串并返回

    # 语法糖2
    def reverseWords2(self, s: str) -> str:
        return ' '.join(s.strip().split()[::-1])

# leetcode submit region end(Prohibit modification and deletion)
