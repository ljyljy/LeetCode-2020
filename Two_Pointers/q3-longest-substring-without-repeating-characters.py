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
    def lengthOfLongestSubstring(self, s) -> int:
        from collections import defaultdict
        if not s: return 0
        i, j = 0, 0
        dictt, res = defaultdict(int), 0  # 字典默认值为0
        while i < len(s) and i - j + 1 >= 0:
            dictt[s[i]] += 1
            print(f'dictt[s[i]] -- {s[i]} : {dictt[s[i]]}')
            # 遇到重复元素：
            # 首先，清空当前答案 ↓（①字典中将j处词频-1 ②j++ ③重复，直到s[i]词频<=1）
            while dictt[s[i]] > 1:  # (1)'bbbb': j会挪到最后
                print(f'===============遇到重复:{s[i]}, 词频：{dictt[s[i]]}=====================')
                print(f'before j++： dictt[s[j]] -- {s[j]} : {dictt[s[j]]}')
                dictt[s[j]] -= 1  # (2)'abcb': j会挪到c（扫描到abc时，res=3，
                # 下次碰到b-> j后移至c，不重复长度为2 -- cb, 与res作比较）
                j += 1
                print(f'after j++： dictt[s[i]] -- {s[i]} : {dictt[s[i]]};  dictt[s[j]] -- {s[j]} : {dictt[s[j]]}')

            res = max(res, i - j + 1)
            print(f'i={i}, j={j}, res={res}')
            i += 1
        return res




if __name__ == "__main__":
    # seq = list(map(str, input().split()))  # 1 2 2 3 5 / a b c a b c b b / 9 3 6 9 5 10 1 2 3 9
    seq = 'abcabcbb'
    print(seq)
    sol = Solution()
    res = sol.lengthOfLongestSubstring(seq)
    print(res)


# leetcode submit region end(Prohibit modification and deletion)
