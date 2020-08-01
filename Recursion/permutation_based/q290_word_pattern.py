# 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
#
#  这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
#
#  示例1:
#
#  输入: pattern = "abba", str = "dog cat cat dog"
# 输出: true
#
#  示例 2:
#
#  输入:pattern = "abba", str = "dog cat cat fish"
# 输出: false
#
#  示例 3:
#
#  输入: pattern = "aaaa", str = "dog cat cat dog"
# 输出: false
#
#  示例 4:
#
#  输入: pattern = "abba", str = "dog dog dog dog"
# 输出: false
#
#  说明:
# 你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。
#  Related Topics 哈希表
#  👍 174 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def wordPattern(self, pattern: str, str: str) -> bool:
        if not pattern or not str: return False
        words = str.split(" ")  # 如: cat dog dog fish
        if len(pattern) != len(words):  # 不是len(str)!
            return False  # ↑ 需要先split！
        pattern_map = {}
        for i, ch in enumerate(pattern):  # 如: a b b c
            if ch not in pattern_map:
                pattern_map[ch] = words[i]
            elif pattern_map[ch] != words[i]:
                return False
        n_ch = len(pattern_map)
        n_word = len(set(pattern_map.values()))
        return n_ch == n_word

# leetcode submit region end(Prohibit modification and deletion)
