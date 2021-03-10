# 	ACW800.数组元素的目标和
# 给定两个升序排序的有序数组A和B，以及一个目标值x。数组下标从0开始。
# 请你求出满足A[i] + B[j] = x的数对(i, j)。
# 数据保证有唯一解。
# 输入格式
# 第一行包含三个整数n，m，x，分别表示A的长度，B的长度以及目标值x。
# 第二行包含n个整数，表示数组A。
# 第三行包含m个整数，表示数组B。
# 输出格式
# 共一行，包含两个整数 i 和 j。
# 输入样例：
# 4 5 6
# 1 2 4 7
# 3 4 6 8 9
# 输出样例：
# 1 1

class Solution:
    def get_sum_two_arr(self, A, B, x) -> [int, int]:
        i, j = 0, len(B) - 1
        while i < len(A) and j >= 0:
            # 同时求i，j（单调性）
            while A[i] + B[j] > x: j -= 1
            while A[i] + B[j] < x: i += 1
            if A[i] + B[j] == x:
                return [i, j]
        return [-1, -1]

    def get_sum_two_arr2(self, A, B, x) -> [int, int]:
        i, j = 0, len(B) - 1
        # 针对每个i(右移)，求出最小的j（单调，左移）
        for i in range(len(A)):
            while j >= 0 and A[i] + B[j] > x:
                j -= 1
            if A[i] + B[j] == x:
                return [i, j]
        return [-1, -1]


if __name__ == "__main__":
    n, m, x = map(int, input().split())
    A = list(map(int, input().split()))
    B = list(map(int, input().split()))

    sol = Solution()
    res = sol.get_sum_two_arr(A, B, x)
    print(f'{res[0]} {res[1]}')
