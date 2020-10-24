# 	680. 分割字符串· split-string
# 给一个字符串,你可以选择在一个字符或两个相邻字符之后拆分字符串,使字符串由仅一个字符或两个字符组成,输出所有可能的结果。
# 例1：
# 输入： "123"
# 输出： [["1","2","3"],["12","3"],["1","23"]]
# 例 2:
# 输入： "12345"
# 输出： [["1","23","45"],["12","3","45"],["12","34","5"],["1","2","3","45"],["1","2","34","5"],["1","23","4","5"],["12","3","4","5"],["1","2","3","4","5"]]
# 相关题目:
# 702. 连接两个字符串中的不同字符582. 单词拆分II107. 单词拆分 I18. 子集 II17. 子集

class Solution:
    def splitString(self, s):
        rst = []
        if not s: return [[]]
        self._helper(s, [], rst)
        return rst

    def _helper(self, s, path, rst):
        if s == "":
            rst.append(path[:])
            return
        for i in range(2):
            if i + 1 <= len(s):  # ∴ 需判断i+1是否越界 ↓
                # ∵ 切片':'后者必须>0 且 切片str[:2+1]才能保证有连续2位数, 故i+1有必要。
                cur, nextt = s[:i + 1], s[i + 1:]  # next若越界，则切片时为空(允许next越界，但cur不可)
                path.append(cur)
                self._helper(nextt, path, rst)  # next可以为" "
                path.pop()
