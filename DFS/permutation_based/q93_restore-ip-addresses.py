# 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
#
#  有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。
#
#
#
#  示例:
#
#  输入: "25525511135"
# 输出: ["255.255.11.135", "255.255.111.35"]
#  Related Topics 字符串 回溯算法
#  👍 348 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def restoreIpAddresses(self, s: str) -> List[str]:
        def dfs(s, idx, path, rst):
            if idx == 4:
                if s == '':
                    rst.append(path[1:])  # 去掉开头的'.'
                    return
            for i in range(3):  # 0,1,2 -- 形如'255'的idx
                if i + 1 <= len(s):  # 切片允许返回空串'', 故i+1 <= len(s)❤
                    cur, nextt = s[:i + 1], s[i + 1:]
                    if int(cur) <= 255:
                        dfs(nextt, idx + 1, path + '.' + cur, rst)  # 首次进入时path为'',以'.'开头
                    if s[0] == '0': break  # 若首字为0，则无需遍历后续数字，这一整个子字段(idx)只能为‘0’

        rst = []
        if not s or len(s) > 3 * 4: return rst
        dfs(s, 0, '', rst)
        return rst

# leetcode submit region end(Prohibit modification and deletion)
