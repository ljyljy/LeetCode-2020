# 1.位运算
#
# 求n的第k位数字:   		n >> k & 1
# 返回n的最后一位1:      lowbit(n) = n & -n

class Solution:
    def lowbit(self, x):
        # 返回n的最后一位1。 如:  lowbit(101000) = 1000
        res = x & -x
        return res

    def get_num_of_1(self, x):
        num_of_1 = 0
        while x:
            num_of_1 += 1
            print(f'lowbit({x})={self.lowbit(x)}')
            x -= self.lowbit(x)
        return num_of_1

if __name__ == "__main__":
    x = 11  # 二进制为1011，共3个1；
    num_of_1 = Solution().get_num_of_1(x)
    print(f'{x}的二进制中1的个数={num_of_1}')

