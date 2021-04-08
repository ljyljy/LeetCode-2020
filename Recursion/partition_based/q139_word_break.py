# 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
#
#  说明：
#
#
#  拆分时可以重复使用字典中的单词。
#  你可以假设字典中没有重复的单词。
#
#
#  示例 1：
#
#  输入: s = "leetcode", wordDict = ["leet", "code"]
# 输出: true
# 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
#
#
#  示例 2：
#
#  输入: s = "applepenapple", wordDict = ["apple", "pen"]
# 输出: true
# 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
#      注意你可以重复使用字典中的单词。
#
#
#  示例 3：
#
#  输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
# 输出: false
#
#  Related Topics 动态规划
#  👍 596 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    # 法2：迭代递归 ❤❤❤
    def wordBreak(self, s, dict):
        if not s and not dict:
            return True
        n = len(s)
        dp = [False] * (n + 1)
        dp[0] = True
        for i in range(1, n + 1):
            for word in dict:
                if dp[i - len(word)] and s[i - len(word):i] == word:
                    dp[i] = True
        return dp[-1]

    # 法1：dfs+memo
    def wordBreak1(self, s: str, wordDict: List[str]) -> bool:
        if not wordDict: return not s
        max_dict_len = len(max(wordDict, key=len))
        return self.dfs(s, wordDict, {}, max_dict_len)

    def dfs(self, s, dict, memo, max_dict_len):
        if s in memo: return memo[s]
        if s == "": return True  # ↓ range注意范围！❤❤❤
        for i in range(1, min(len(s), max_dict_len) + 1):
            pre, nextt = s[:i], s[i:]
            if pre not in dict:
                continue
            if self.dfs(nextt, dict, memo, max_dict_len):
                memo[s] = True
                return memo[s]
        memo[s] = False
        return memo[s]

# leetcode submit region end(Prohibit modification and deletion)
