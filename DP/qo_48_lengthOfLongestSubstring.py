# 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
#
#
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
#
#
#
#  提示：
#
#
#  s.length <= 40000
#
#
#  注意：本题与主站 3 题相同：https://leetcode-cn.com/problems/longest-substring-without-rep
# eating-characters/
#  Related Topics 哈希表 双指针 Sliding Window
#  👍 76 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    # 法3：双指针 + 哈希 [简单]
    def lengthOfLongestSubstring(self, s: str) -> int:
        dic, res, i = {}, 0, -1
        for j in range(len(s)):
            if s[j] in dic:
                i = max(dic[s[j]], i)  # 更新左指针 i（前任重复下标）
            dic[s[j]] = j  # 更新哈希表
            res = max(res, j - i)  # ∵s[i]==s[j]，故不重复长度=j-i,无需再+1！！
        return res

    # 法1：动归 + 哈希
    def lengthOfLongestSubstring_1(self, s: str) -> int:
        dic = {}
        rst = tmp = 0
        for j in range(len(s)):
            i = dic.get(s[j], -1)  # 获取索引 i
            dic[s[j]] = j  # 更新哈希表
            tmp = tmp + 1 if tmp < j - i else j - i  # # dp[j - 1] -> dp[j]
            rst = max(rst, tmp)  # max(dp[j - 1], dp[j])
        return rst

# leetcode submit region end(Prohibit modification and deletion)
