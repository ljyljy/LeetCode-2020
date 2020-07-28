# # 幻方：
# 3 x 3 的幻方是一个填充有从 1 到 9 的不同数字的 3 x 3 矩阵，其中【每行，每列以及两条对角线上的各数之和】都相等。
# 给定一个由整数组成的 grid，其中有多少个 3 × 3 的 “幻方” 子矩阵？（每个子矩阵都是连续的）。

# 生成幻方：
def generate_magic_square(n):
    magic = [[0] * n for _ in range(n)]  # n*n矩阵
    row, col = n - 1, n // 2
    magic[row][col] = 1  # 规则：1放置于底行、中间列

    for i in range(2, n * n + 1):
        try_row, try_col = (row + 1) % n, (col + 1) % n
        if magic[try_row][try_col] == 0:
            row, col = try_row, try_col
        else:  # 规则：若右下角有数字，则往上写
            row = (row - 1 + n) % n  # '+n': 防止row-1为负数
        magic[row][col] = i

    for x in magic:
        print(x, sep=' ')


generate_magic_square(3)
