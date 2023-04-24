# 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
#
#
#
#  示例 1：
#
#  输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
# 输出：[1,2,3,6,9,8,7,4,5]
#
#
#  示例 2：
#
#  输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
# 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
#
#
#
#
#  限制：
#
#
#  0 <= matrix.length <= 100
#  0 <= matrix[i].length <= 100
#
#
#  注意：本题与主站 54 题相同：https://leetcode-cn.com/problems/spiral-matrix/
#  Related Topics 数组
#  👍 125 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List

# 二维数组(row, col)下标与方向不是同一个！
#     → col+1, ↓ row+1, ← col-1, ↑ row-1
DIRS = [(0, 1), (1, 0), (0, -1), (-1, 0)]  # 顺时针方向


class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        n_row = len(matrix)  # 边界情况不可合并一起写！否则'[]'会出界！
        # print(f"n_row: {n_row}")     # ↓ 判断matrix可能为一列向量的情况
        if not matrix or not n_row or isinstance(matrix[0], int): return []
        n_col = len(matrix[0])  # 【针对特例：m=[1,2,3] —> len(m)=3, m[0]=1, int没有len方法！】

        def isValid(new_x, new_y):
            return 0 <= new_x < n_row and 0 <= new_y < n_col \
                   and not visited[new_x][new_y]

        visited = [[False] * n_col for _ in range(n_row)]
        x, y, d = 0, 0, 0  # 不放在for里遍历，而是通过公式trick计算！
        rst = []
        for i in range(n_row * n_col):  # 这里不做两层for i,j
            rst.append(matrix[x][y])
            # print(f"x,y: {x}, {y}")
            visited[x][y] = True
            new_x, new_y = x + DIRS[d][0], y + DIRS[d][1]
            if not isValid(new_x, new_y):
                # print(f'newx, newy not valid: {new_x}, {new_y}')
                d = (d + 1) % 4  # 4个DIRS顺时针方向(转向！)
                new_x, new_y = x + DIRS[d][0], y + DIRS[d][1]
            x, y = new_x, new_y
        return rst


if __name__ == "__main__":
    sol = Solution()
    mat = [[i, i + 1, i + 2, i + 3] for i in range(1, 9 + 1, 4)]
    print(mat)
    rst = sol.spiralOrder(mat)
    print(rst)

    mat = [1, 2, 3]
    print(mat)
    rst = sol.spiralOrder(mat)
    print(rst)
# leetcode submit region end(Prohibit modification and deletion)
