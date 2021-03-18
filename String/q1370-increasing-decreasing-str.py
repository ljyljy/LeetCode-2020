# 给你一个字符串 s ，请你根据下面的算法重新构造字符串： 
# 
#  
#  从 s 中选出 最小 的字符，将它 接在 结果字符串的后面。 
#  从 s 剩余字符中选出 最小 的字符，且该字符比上一个添加的字符大，将它 接在 结果字符串后面。 
#  重复步骤 2 ，直到你没法从 s 中选择字符。 
#  从 s 中选出 最大 的字符，将它 接在 结果字符串的后面。 
#  从 s 剩余字符中选出 最大 的字符，且该字符比上一个添加的字符小，将它 接在 结果字符串后面。 
#  重复步骤 5 ，直到你没法从 s 中选择字符。 
#  重复步骤 1 到 6 ，直到 s 中所有字符都已经被选过。 
#  
# 
#  在任何一步中，如果最小或者最大字符不止一个 ，你可以选择其中任意一个，并将其添加到结果字符串。 
# 
#  请你返回将 s 中字符重新排序后的 结果字符串 。 
# 
#  
# 
#  示例 1： 
# 
#  输入：s = "aaaabbbbcccc"
# 输出："abccbaabccba"
# 解释：第一轮的步骤 1，2，3 后，结果字符串为 result = "abc"
# 第一轮的步骤 4，5，6 后，结果字符串为 result = "abccba"
# 第一轮结束，现在 s = "aabbcc" ，我们再次回到步骤 1
# 第二轮的步骤 1，2，3 后，结果字符串为 result = "abccbaabc"
# 第二轮的步骤 4，5，6 后，结果字符串为 result = "abccbaabccba"
#  
# 
#  示例 2： 
# 
#  输入：s = "rat"
# 输出："art"
# 解释：单词 "rat" 在上述算法重排序以后变成 "art"
#  
# 
#  示例 3： 
# 
#  输入：s = "leetcode"
# 输出："cdelotee"
#  
# 
#  示例 4： 
# 
#  输入：s = "ggggggg"
# 输出："ggggggg"
#  
# 
#  示例 5： 
# 
#  输入：s = "spo"
# 输出："ops"
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= s.length <= 500 
#  s 只包含小写英文字母。 
#  
#  Related Topics 排序 字符串 
#  👍 33 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from collections import Counter


class Solution:
    def sortString(self, s: str) -> str:
        # s: 'ccaaaabbb', ch_num.items(): [('c', 2), ('a', 4), ('b', 3)]
        # sorted(ch_num.items()) 或 sorted(ch_num.items(), key=lambda x: x[0]):
        #                                         ->  [('a', 4), ('b', 3), ('c', 2)]
        # sorted(ch_num.items(), key=lambda x: x[1]): [('c', 2), ('b', 3), ('a', 4)]

        if not s: return ''
        ch_cnt, rst = Counter(s), []  # list(ch_num.items())[0][0]
        ch_cnt_sorted = sorted(ch_cnt.items(), key=lambda x: x[0])  # (默认)按字母升序
        ch_cnt_sorted = [list(x) for x in ch_cnt_sorted]  # ❤❤❤ tuple不可改，转为list
        while len(rst) < len(s):
            for i in range(len(ch_cnt_sorted)):
                ch, cnt = ch_cnt_sorted[i]  # dict[i]形如：('a', 4)
                if cnt != 0:
                    rst.append(ch)
                    ch_cnt_sorted[i][1] -= 1
            for j in reversed(range(len(ch_cnt_sorted))):
                ch, cnt = ch_cnt_sorted[j]  # dict[i]形如：('a', 4)
                if cnt != 0:
                    rst.append(ch)
                    ch_cnt_sorted[j][1] -= 1
        return ''.join(rst)


if __name__ == '__main__':
    sol = Solution()
    s = 'ccccaabbb'
    print(sol.sortString(s))

# leetcode submit region end(Prohibit modification and deletion)