# 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
#
#  说明：本题中，我们将空字符串定义为有效的回文串。
#
#  示例 1:
#
#  输入: "A man, a plan, a canal: Panama"
# 输出: true
#
#
#  示例 2:
#
#  输入: "race a car"
# 输出: false
#
#  Related Topics 双指针 字符串
#  👍 249 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def isPalindrome(self, s: str) -> bool:
        start, end = 0, len(s) - 1
        while start < end:
            while start < end and not s[start].isalpha() and not s[start].isdigit():
                start += 1
            while start < end and not s[end].isalnum():  # ！
                end -= 1
            if start < end and s[start].lower() != s[end].lower():  # ！
                return False
            start += 1
            end -= 1
        return True
# leetcode submit region end(Prohibit modification and deletion)
