# 	Q9_405. 和为0的子矩阵
# 给定一个整数矩阵，请找出一个子矩阵，使得其数字之和等于0.输出答案时，请返回左上数字和右下数字的坐标。如果有多个答案, 你可以返回其中任意一个.
# 说明：
# 0 ≤ N ≤ 30
# 样例1：
# 输入:
# [
#   [1, 5, 7],
#   [3, 7, -8],
#   [4, -8 ,9]
# ]
# 输出: [[1, 1], [2, 2]]
# 样例 2:
# 输入:
# [
#   [0, 1],
#   [1, 0]
# ]
# 输出: [[0, 0], [0, 0]]
# 挑战： O(n3) 时间复杂度。
# 相关题目： 944. 最大子矩阵139. 最接近零的子数组和138. 子数组之和
# 用前缀和优化, 令 sum[i][j] = sum[0][j] + sum[1][j] + ... + sum[i][j]
# 然后枚举上下边界, 这样就相当于在一行内, 求一个数组连续子串和为0的问题了.
#
# 算法思路：
# •	求出数组的前缀和数组pre，对于左上角(x1,y1)，右下角(x2,y2)的矩阵，矩阵和为pre[x2][y2]-pre[x1-1][y2]-pre[x2][y1-1]+pre[x1-1][y1-1]
# •	那么我们枚举左上角和右小角，找到其中一个和为0的矩阵即可
# 代码思路
# 1.	求出数组的前缀和数组pre
# 2.	枚举左上角位置
# 3.	对于每个左上角，在其位置的右下部分枚举右下角
# 4.	计算子矩阵和，若为0则返回
# 复杂度分析： N表示数组长度
# •	空间复杂度：O(N2)
# •	时间复杂度：O(N4)

class Solution:
    # 法2：空间O(n^3) - hash
    def submatrixSum2(self, matrix):
        if not matrix or not matrix[0]: return None
        nrow, ncol = len(matrix), len(matrix[0])

        # 枚举上下边界nrow（pair<top_row, bottom_row> -- 某两行）
        for top_row in range(nrow):
            tmp_row = [0] * ncol
            for bottom_row in range(top_row, nrow):
                prefix_hash = {0: -1}
                # 求出固定上下边界的前缀和
                prefix_sum = 0
                for col in range(ncol):
                    tmp_row[col] += matrix[bottom_row][col]
                    prefix_sum += tmp_row[col]
                    # hashmap判断是否存在相同前缀和
                    if prefix_sum in prefix_hash:  # ↓ 记得要+1！！！
                        return [(top_row, prefix_hash[prefix_sum] + 1), (bottom_row, col)]
                    prefix_hash[prefix_sum] = col
        return None

    # 法1：空间O(n^4)
    def submatrixSum(self, matrix):
        nrow, ncol = len(matrix), len(matrix[0])
        res = []
        # 1. 求出前缀和数组   # 边界 pre[0,:] = pre[:, 0] = 0
        pre = [[0 for _ in range(ncol + 1)] for _ in range(nrow + 1)]
        pre[1][1] = matrix[0][0]  # 1.1 前缀和[1][1] <=> 对应matrix[0][0]
        # 1.2 初始化前缀和的第1列(2:n, 1)
        for i in range(2, nrow + 1):
            pre[i][1] = pre[i - 1][1] + matrix[i - 1][0]  # pre[i][1]对应位置的matrix下标为[i-1, 0]
        # 1.3 初始化前缀和的第1行(1, 2:j)
        for j in range(2, ncol + 1):
            pre[1][j] = pre[1][j - 1] + matrix[0][j - 1]  # pre[1][j]对应位置的matrix下标为[0, j-1]
        # 1.4 填充[2:nrow, 2:ncol]
        for i in range(2, nrow + 1):
            for j in range(2, ncol + 1):
                pre[i][j] = pre[i - 1][j] + pre[i][j - 1] - pre[i - 1][j - 1] + matrix[i - 1][j - 1]

        # 枚举左上角
        for x1 in range(1, nrow + 1):
            for y1 in range(1, ncol + 1):
                # 枚举右下角
                for x2 in range(x1, nrow + 1):
                    for y2 in range(y1, ncol + 1):
                        # 若矩阵和为0，则返回 ❤❤❤ ↓容易出错！！
                        if pre[x2][y2] - pre[x1 - 1][y2] - pre[x2][y1 - 1] + pre[x1 - 1][y1 - 1] == 0:
                            res.append([x1 - 1, y1 - 1])
                            res.append([x2 - 1, y2 - 1])
                            return res
#
#
# def get_submatrix_sum(matrix, x1, y1, x2, y2):
#     nrow, ncol = len(matrix), len(matrix[0])
#     pre = [[0 for _ in range(ncol + 1)] for _ in range(nrow + 1)]
#     pre[1][1] = matrix[0][0]
#     for i in range(2, nrow + 1):
#         pre[i][1] = pre[i - 1][1] + matrix[i - 1][0]
#     for j in range(2, ncol + 1):
#         pre[1][j] = pre[1][j - 1] + matrix[0][j - 1]
#     for i in range(2, nrow + 1):
#         for j in range(2, ncol + 1):
#             pre[i][j] = pre[i - 1][j] + pre[i][j - 1] - pre[i - 1][j - 1] + matrix[i - 1][j - 1]
#     return pre[x2][y2] - pre[x1 - 1][y2] - pre[x2][y1 - 1] + pre[x1 - 1][y1 - 1]
#
#
# if __name__ == "__main__":
#     n, m, q = list(map(int, input().split()))
#     mat = []
#     for i in range(n):
#         line = list(map(int, input().split()))
#         mat.append(line)
#     while q:
#         query = list(map(int, input().split()))
#         rst = get_submatrix_sum(mat, *query)
#         print(rst)
#         q -= 1
#
