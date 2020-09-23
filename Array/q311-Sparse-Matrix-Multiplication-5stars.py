# 	Q311. 稀疏矩阵乘法 · Sparse Matrix Multiplication
# 给定两个 稀疏矩阵 A 和 B，返回AB的结果。 您可以假设A的列数等于B的行数。
# 说明：
# 0 ≤ N ≤ 30
# 例1：
# 输入：
# [[1,0,0],[-1,0,3]]
# [[7,0,0],[0,0,0],[0,0,1]]
# 输出：
# [[7,0,0],[-7,0,3]]
# 解释：
# A = [
#   [ 1, 0, 0],
#   [-1, 0, 3]
# ]
#
# B = [
#   [ 7, 0, 0 ],
#   [ 0, 0, 0 ],
#   [ 0, 0, 1 ]
# ]
#      |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
# AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
#                   | 0 0 1 |.
# 例2：
# [[1,0],[0,1]]
# [[0,1],[1,0]]
# 输出：
# [[0,1],[1,0]]

# 思路：
# 其中 n 为矩阵的长宽（假设一样吧），k为系数度，也就是大概一行有几个非零位。

class Solution:
    # 矩阵乘法： A i行 * B all列 = C i行
    def multiply(self, A, B):  # mcolA == mrowB
        nrow, mcolA, kcolB = len(A), len(A[0]), len(B[0])  # A行数:2, B: 3*3（例1）
        row_vecsA = [[(j, A[i][j])  # (A列号，A值) -- (nrows_A, 每行非0的元素 & 列号)
                      for j in range(mcolA) if A[i][j]]  # 内for
                     for i in range(nrow)]  # 外for； [[(0, 1)], [(0, -1), (2, 3)]]
        col_vecsB = [[(i, B[i][j])  # (B行号，B值)
                      for i in range(mcolA) if B[i][j]]  # 内for ❤ # mcolA == mrowB
                     for j in range(kcolB)]  # 外for；  [[(0, 7)], [], [(2, 1)]]
        # retuaa = [[(row, col) for col in col_vecs] for row in row_vecs]
        # ↓ row, col: [[([(0, 1)], [(0, 7)]), ([(0, 1)], []), ([(0, 1)], [(2, 1)])], [([(0, -1), (2, 3)], [(0, 7)]), ([(0, -1), (2, 3)], []), ([(0, -1), (2, 3)], [(2, 1)])]]
        return nrow, mcolA, kcolB, row_vecsA, col_vecsB, \
               [[self.multi(row, col) for col in col_vecsB] for row in row_vecsA], \
               [[(row, col) for col in col_vecsB] for row in row_vecsA]  # ljytest

    def multi(self, mat1_rows, mat2_cols):
        print("mat1_rows -- mat2_cols:", mat1_rows, ' -- ', mat2_cols)
        i, j, sum = 0, 0, 0
        while i < len(mat1_rows) and j < len(mat2_cols):
            idx1_col, val1 = mat1_rows[i]  # 行向量组 -- 需要知道列号
            idx2_row, val2 = mat2_cols[j]  # 列向量组 -- 需要知道行号
            if idx1_col < idx2_row:
                i += 1
            elif idx1_col > idx2_row:
                j += 1
            else:
                sum += val1 * val2
                i += 1
                j += 1
        return sum


if __name__ == "__main__":
    sol = Solution()
    A = [
        [1, 0, 0],
        [-1, 0, 3]
    ]

    B = [
        [7, 0, 0],
        [0, 0, 0],
        [0, 0, 1]
    ]
    nrow, mcolA, kcolB, row_vecsA, col_vecsB, res, ljytest = sol.multiply(A, B)
    print("A * B = ", res)
