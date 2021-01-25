# 	ACW796. 子矩阵的前缀和
# https://www.acwing.com/problem/content/798/
# 输入格式
# 第一行包含三个整数n，m，q。
# 接下来n行，每行包含m个整数，表示整数矩阵。
# 接下来q行，每行包含四个整数x1, y1, x2, y2，表示一组询问。
# 输出格式
# 共q行，每行输出一个询问的结果。

def get_submatrix_sum(matrix, x1, y1, x2, y2):
    nrow, ncol = len(matrix), len(matrix[0])
    pre = [[0 for _ in range(ncol + 1)] for _ in range(nrow + 1)]
    pre[1][1] = matrix[0][0]
    for i in range(2, nrow + 1):
        pre[i][1] = pre[i - 1][1] + matrix[i - 1][0]
    for j in range(2, ncol + 1):
        pre[1][j] = pre[1][j - 1] + matrix[0][j - 1]
    for i in range(2, nrow + 1):
        for j in range(2, ncol + 1):
            pre[i][j] = pre[i - 1][j] + pre[i][j - 1] - pre[i - 1][j - 1] + matrix[i - 1][j - 1]
    return pre[x2][y2] - pre[x1 - 1][y2] - pre[x2][y1 - 1] + pre[x1 - 1][y1 - 1]


if __name__ == "__main__":
    n, m, q = list(map(int, input().split()))
    mat = []
    for i in range(n):
        line = list(map(int, input().split()))
        mat.append(line)
    while q:
        query = list(map(int, input().split()))
        rst = get_submatrix_sum(mat, *query)
        print(rst)
        q -= 1
