# 	ACW798. 差分矩阵
# 输入一个n行m列的整数矩阵，再输入q个操作，每个操作包含五个整数x1, y1, x2, y2, c，其中(x1, y1)和(x2, y2)表示一个子矩阵的左上角坐标和右下角坐标。
# 每个操作都要将选中的子矩阵中的每个元素的值加上c。
# 请你将进行完所有操作后的矩阵输出。
# 输入格式
# 第一行包含整数n,m,q1310_xor_queries_of_a_subarray。
# 接下来n行，每行包含m个整数，表示整数矩阵。
# 接下来q行，每行包含5个整数x1, y1, x2, y2, c，表示一个操作。
# 输出格式
# 共 n 行，每行 m 个整数，表示所有操作进行完毕后的最终矩阵。
# 数据范围
# 1≤n,m≤1000,
# 1≤q1310_xor_queries_of_a_subarray≤100000,
# 1≤x1≤x2≤n,
# 1≤y1≤y2≤m,
# −1000≤c≤1000,
# −1000≤矩阵内元素的值≤1000
# 输入样例：
# 3 4 3
# 1 2 2 1
# 3 2 2 1
# 1 1 1 1
# 1 1 2 2 1
# 1 3 2 3 2
# 3 1 3 4 1
# 输出样例：
# 2 3 4 1
# 4 3 4 1
# 2 2 2 2

def insert(diff_mat, x1, y1, x2, y2, c):
    # 由原数组mat转换为差分数组diff_mat
    # 注意：非x1,y1的，后面都需再+1
    diff_mat[x1][y1] += c
    diff_mat[x2 + 1][y1] -= c
    diff_mat[x1][y2 + 1] -= c
    diff_mat[x2 + 1][y2 + 1] += c


if __name__ == "__main__":
    n, m, q = map(int, input().split())  # n*m矩阵, q个操作
    N = 1010  # 省去边界条件的考虑
    mat = [[0] * N for i in range(N)]
    diff_mat = [[0] * N for i in range(N)]
    # 构造1：原数组mat
    for i in range(1, n + 1):  # 下标后移 + 首尾哨兵'0'（idx=0、n+1、m+1）
        row_i = list(map(int, input().split()))
        for j, val in enumerate(row_i):
            mat[i][j + 1] = val  # 下标后移：j+1

    # 构造2：差分数组diff_mat
    for i in range(1, n + 1):
        for j in range(1, m + 1):
            insert(diff_mat, i, j, i, j, mat[i][j])  # ❤
    # q1310_xor_queries_of_a_subarray 次操作
    while q > 0:
        q -= 1
        x1, y1, x2, y2, c = map(int, input().split())
        insert(diff_mat, x1, y1, x2, y2, c)  # ❤
    # 由差分数组diff_mat, 还原数组mat(即差分数组的前缀和)
    for i in range(1, n + 1):
        for j in range(1, m + 1):  # 见ipad笔记（前缀和数组S = 原数组mat, 对diff_mat原地修改↓）
            # S[i][j] = S[i-1][j] + S[i][j-1] - S[i-1][j-1] + diff_mat[i][j]
            diff_mat[i][j] = diff_mat[i - 1][j] + diff_mat[i][j - 1] - diff_mat[i - 1][j - 1] + diff_mat[i][j]
    # 输出
    for i in range(1, n + 1):
        for j in range(1, m + 1):
            print(diff_mat[i][j], end=' ')
        print()

