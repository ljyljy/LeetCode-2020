# 请实现一个函数，输入一个整数，输出该数二进制表示中 1 的个数。例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出
# 2。
#
#  示例 1：
#
#  输入：00000000000000000000000000001011
# 输出：3
# 解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
#
#
#  示例 2：
#
#  输入：00000000000000000000000010000000
# 输出：1
# 解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
#
#
#  示例 3：
#
#  输入：11111111111111111111111111111101
# 输出：31
# 解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
#
#
#
#  注意：本题与主站 191 题相同：https://leetcode-cn.com/problems/number-of-1-bits/
#  Related Topics 位运算
#  👍 39 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    # 法1
    def hammingWeight1(self, n: int) -> int:
        res = []
        abs_n = abs(n)
        while abs_n:
            remainder = abs_n % 2
            abs_n >>= 1
            res.insert(0, remainder)  # 正数：除二取余,倒序排列,高位补零。
        if n >= 0:  # 若数字本身为正数
            cnt_1 = res.count(1)
        else:  # 负数？？？（补码 = 原码取反+1）
            # 负数要用32 减去 二进制数的长度。然后找1，找到一个加一个
            cnt_1 = (32 - len(res)) + res.count(1)
        return cnt_1

    # 法2：位运算
    def hammingWeight(self, n: int) -> int:
        count = 0
        if n < 0:
            n = n & 0xffffffff
        while n:
            n = n & (n - 1)
            count += 1
        return count

# C++
# 时间复杂度
# 每次会将 nn 除以2，最多会除 lognlogn 次，所以时间复杂度是 O(logn)O(logn)。
#
# class Solution {
# public:
#     int NumberOf1(int n) {
#         int res = 0;
#         unsigned int un = n;
#         while (un) res += un & 1, un >>= 1;
#         return res;
#     }
# };
# leetcode submit region end(Prohibit modification and deletion)
