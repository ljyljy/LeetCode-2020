# 	Q5. 最长回文子串·longest-palindromic-substring
# 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
# 示例 1：
# 输入: "babad" 	输出: "bab"
# 注意: "aba" 也是一个有效答案。
# 示例 2：
# 输入: "cbbd"	输出: "bb"
# 思路1：
# 从每一个位置出发，向两边扩散即可。遇到不是回文的时候结束。举个例子，str = acdbbdaastr=acdbbdaa 我们需要寻找从第一个 b（位置为 33）出发最长回文串为多少。怎么寻找？
# 首先往左寻找与当期位置相同的字符，直到遇到不相等为止。
# 然后往右寻找与当期位置相同的字符，直到遇到不相等为止。
# 最后左右双向扩散，直到左和右不相等。每个位置向两边扩散都会出现一个窗口大小（len）。如果 len>maxLen(用来表示最长回文串的长度）。则更新 maxLen 的值。
# 因为我们最后要返回的是具体子串，而不是长度，因此，还需要记录一下 maxLen 时的起始位置（maxStart），即此时还要 maxStart=len

class Solution:
    # v2: DP
    def longestPalindrome(self, s: str) -> str:
        n = len(s)
        if n < 2: return s
        dp = [[False for _ in range(n)] for _ in range(n)]
        max_len, start = 1, 0
        for i in range(n):
            dp[i][i] = True
        # “无后效性”： 填表方向—— ↓↓↓
        for j in range(1, n):  # ∵对角线已填表，直接是True
            for i in range(0, j):  # 上三角
                if s[i] == s[j]:
                    if j - i < 3:  # i.e. (j-1) - (i + 1) + 1 < 2:
                        dp[i][j] = True
                    else:
                        dp[i][j] = dp[i + 1][j - 1]  # ∴不能有“后效性”！填表顺序！
                else:  # s[i] != s[j]
                    dp[i][j] = False
                if dp[i][j]:
                    cur_len = j - i + 1
                    if cur_len > max_len:
                        max_len = cur_len
                        start = i
        return s[start: start + max_len]

    # v1：双指针（中心扩散法）
    def longestPalindrome1(self, s: str) -> str:
        res = ''
        # 枚举中心点c -- [L,...c,...R]
        # -> 当前最大回文串[L+1:R-1], 长度=R-1-(L+1)+1=R-L-1
        for i in range(0, len(s)):
            l, r = i - 1, i + 1  # 1.若s长度为奇数
            while l >= 0 and r < len(s) and s[l] == s[r]:
                l -= 1
                r += 1
            # 退出while后，当前lr不满足回文串，但[l+1, r-1]是回文串
            if len(res) < r - l - 1: res = s[l + 1:r]

            l, r = i, i + 1  # 2.若s长度为偶数
            while l >= 0 and r < len(s) and s[l] == s[r]:
                l -= 1
                r += 1
            # 退出while后，当前lr不满足回文串，但[l+1, r-1]是回文串
            if len(res) < r - l - 1: res = s[l + 1:r]
        return res
