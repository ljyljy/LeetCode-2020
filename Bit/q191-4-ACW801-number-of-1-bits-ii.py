# 	ACW801.（191. 位1的个数-II）
# 给定一个长度为n的数列，请你求出数列中每个数的二进制表示中1的个数。
# 输入格式
# 第一行包含整数n。
# 第二行包含n个整数，表示整个数列。
# 输出格式
# 共一行，包含n个整数，其中的第 i 个数表示数列中的第 i 个数的二进制表示中1的个数。
# 输入样例：
# 5
# 1 2 3 4 5
# 输出样例：
# 1 1 2 1 2

class Solution:
    def lowbit(self, x):
        return x & -x

    def get_num_of_1(self, x):
        res = 0
        while x:
            x -= self.lowbit(x)
            res += 1
        return res

if __name__ == "__main__":
    n = map(int, input().split())
    nums = list(map(int, input().split()))
    sol = Solution()
    for num in nums:
        print(sol.get_num_of_1(num), end=' ')
