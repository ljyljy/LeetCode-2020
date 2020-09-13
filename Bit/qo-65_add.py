# 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
#
#
#
#  示例:
#
#  输入: a = 1, b = 1
# 输出: 2
#
#
#
#  提示：
#
#
#  a, b 均可能是负数或 0
#  结果不会溢出 32 位整数
#
#  👍 67 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    # Python版本
    def add(self, a: int, b: int) -> int:
        x = 0xffffffff
        a, b = a & x, b & x  # 获取负数的补码： 需要将数字与x相与: 舍去此数字 32 位以上的数字（将 32 位以上都变为 0），
        # ↑ 【从无限长度变为一个 32 位整数】
        while b:
            sum_no_carry = a ^ b  # 不带进位的加法
            carry = (a & b) << 1 & x  # 计算进位处，并进1（左移1）
            a = sum_no_carry
            b = carry
        # 若补码 a 为负数（ 0x7fffffff 是最大的正数的补码 ），需执行 ~(a ^ x) 操作，将补码还原至 Python 的存储格式。
        # a ^ x 运算将 1 至 32 位按位取反； ~ 运算是将整个数字取反；因此， ~(a ^ x) 是将 32 位以上的位取反，1 至 32 位不变。
        return a if a <= 0x7fffffff else ~(a ^ x)  # 返回前数字还原 ↑

    # java & C版本
    def add_java_c(self, a: int, b: int) -> int:
        while b:
            sum_no_carry = a ^ b  # 不带进位的加法
            carry = (a & b) << 1  # 计算进位处，并进1（左移1）
            a = sum_no_carry
            b = carry
        return a


# 链接：https://leetcode-cn.com/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof/solution/mian-shi-ti-65-bu-yong-jia-jian-cheng-chu-zuo-ji-7/
print(hex(1))  # = 0x1 补码
print(hex(-1))  # = -0x1 负号 + 原码 （ Python 特色，Java 会直接输出补码）

print(hex(1 & 0xffffffff))  # = 0x1 正数补码
print(hex(-1 & 0xffffffff))  # = 0xffffffff 负数补码

print(-1 & 0xffffffff)  # = 4294967295 （ Python 将其认为正数）

# leetcode submit region end(Prohibit modification and deletion)
