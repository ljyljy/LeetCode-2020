# 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
#
#  示例 1:
#
#  输入: "abcabcbb"
# 输出: 3
# 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
#
#
#  示例 2:
#
#  输入: "bbbbb"
# 输出: 1
# 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
#
#
#  示例 3:
#
#  输入: "pwwkew"
# 输出: 3
# 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
#      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
#
#  Related Topics 哈希表 双指针 字符串 Sliding Window
#  👍 4358 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        from collections import defaultdict
        if not s: return 0
        i, j = 0, 0
        dictt, res = defaultdict(int), 0  # 字典默认值为0
        while i < len(s) and i - j + 1 >= 0:
            dictt[s[i]] += 1
            # print(f'dictt[s[i]] -- {s[i]} : {dictt[s[i]]}')
            while dictt[s[i]] > 1:  # (1)'bbbb': j会挪到最后
                dictt[s[j]] -= 1  # (2)'abcb': j会挪到c（扫描到abc时，res=3，
                # 下次碰到b-> j后移至c，不重复长度为2 -- cb, 与res作比较）
                j += 1
            res = max(res, i - j + 1)
            # print(f'{i}, {j}, res: {res}')
            i += 1
        return res


if __name__ == "__main__":
    sol = Solution()
    res = sol.lengthOfLongestSubstring('abcabcbb')
    print(res)
# leetcode submit region end(Prohibit modification and deletion)
