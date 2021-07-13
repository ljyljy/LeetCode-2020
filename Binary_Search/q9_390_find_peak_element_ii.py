# 	Q9_390. 寻找峰值 II
# 给定一个整数矩阵 A, 它有如下特性:
# 	相邻的整数不同
# 	矩阵有 n 行 m 列，n和m不会小于3。
# 	对于所有的 i < n, 都有 A[i][0] < A[i][1] && A[i][m - 2] > A[i][m - 1]
# 	对于所有的 j < m, 都有 A[0][j] < A[1][j] && A[n - 2][j] > A[n - 1][j]
# 我们定义一个位置 [i,j] 是峰值, 当且仅当它满足:
# 	A[i][j] > A[i + 1][j] && A[i][j] > A[i - 1][j] &&
# A[i][j] > A[i][j + 1] && A[i][j] > A[i][j - 1]
# 找到该矩阵的一个峰值元素, 返回它的坐标.
# 样例1:
# 输入:
#     [
#       [1, 2, 3, 6,  5],
#       [16,41,23,22, 6],
#       [15,17,24,21, 7],
#       [14,18,19,20,10],
#       [13,14,11,10, 9]
#     ]
# 输出: [1,1]
# 解释: [2,2] 也是可以的. [1,1] 的元素是 41, 大于它四周的每一个元素 (2, 16, 23, 17).
# 示例2: 输入:
#     [
#       [1, 5, 3],
#       [4,10, 9],
#       [2, 8, 7]
#     ]
# 输出: [1,1]
# 解释: 只有这一个峰值1
# 说明:
# 保证至少存在一个峰值, 而如果存在多个峰值, 返回任意一个即可.
# 挑战：
# O(n+m) 的时间复杂度.
# 如果你 认为 你使用了 O(nlogm) 或 O(mlogn) 的算法, 能否证明它的复杂度其实是 O(n+m)? 或者想一个类似的算法但是复杂度是O(n+m)？。


# 法一：贪心
# 思路
# 	峰值不是最大值，只是比四个方向上的数值都大，是局部性的最值。
# 	对于每一个点，它总能属于某一座山峰（可以不止一座）。
# 	找峰值可以想象成爬山，总是要不断的从低处向高处移动，这样移动到最后一定是峰值。
# 代码思路
# 	在图上随机取一点，若有相邻位置比当前点大则向该相邻位置移动，直到当前点成为峰值。
# 	最坏情况是螺旋式上升，且随机在了起点位置，那么就要爬升一半的点
# 	如果遇到最坏的情况，我们可以通过当爬升超过一定距离后重新随机一个点，使得相对平均效率较高。

class Solution:
    # 法2： 二分
    def findPeakII(self, A):
        n, m = len(A), len(A[0])
        up, bottom = 0, n - 1
        while up + 1 < bottom:
            mid = up + bottom >> 1
            # mid = up + (bottom - up) // 2
            maxCol = self.find_maxCol(A, mid)
            # 若上一行位置比当前位置值大，则下边界上移
            if A[mid - 1][maxCol] > A[mid][maxCol]:
                bottom = mid
            # 若下一行位置比当前位置值大，则上边界下移
            elif A[mid + 1][maxCol] > A[mid][maxCol]:
                up = mid
            else:  # 否则该位置为峰值，直接返回答案
                return [mid, maxCol]
        # while外：[up + 1 == bottom]
        # 比较上下边界上最大值，取较大的位置返回答案
        bottom_maxCol = self.find_maxCol(A, bottom)
        up_maxCol = self.find_maxCol(A, up)
        if A[bottom][bottom_maxCol] > A[up][up_maxCol]:
            return [bottom, bottom_maxCol]
        else:
            return [up, up_maxCol]

    def find_maxCol(self, A, row):
        m, maxCol = len(A[0]), 0

        for j in range(1, m):
            if A[row][maxCol] < A[row][j]:
                maxCol = j
        return maxCol
        # 不可以用二分！因为是无序的！！
        # start, end = 0, m
        # while start + 1 < end:
        #     mid = start + end >> 1
        #     if A[row][mid] < A[row][mid + 1]:
        #         start = mid
        #     elif A[row][mid] < A[row][mid - 1]:
        #         end = mid
        #     else:
        #         print('A[row]: ', A[row],', mid', mid)
        #         return mid
        # if A[row][start] <= A[row][end]:
        #     print('A[row]: ', A[row],', start', start)
        #     return start
        # else:
        #     print('A[row]: ', A[row],', end', end)
        #     return end

    # 法1： 贪心
    def findPeakII_greedy(self, A):
        n, m = len(A), len(A[0])
        now_x, now_y = n // 2, m // 2
        while 1:
            next_x, next_y = now_x, now_y
            if now_x + 1 < n and A[now_x + 1][now_y] > A[now_x][now_y]:
                next_x = now_x + 1
            elif now_x - 1 >= 0 and A[now_x - 1][now_y] > A[now_x][now_y]:
                next_x = now_x - 1
            elif now_y + 1 < m and A[now_x][now_y + 1] > A[now_x][now_y]:
                next_y = now_y + 1
            elif now_y - 1 >= 0 and A[now_x][now_y - 1] > A[now_x][now_y]:
                next_y = now_y - 1

            # 若四个方向上都比当前位置小，即为峰值直接返回答案
            if next_x == now_x and next_y == now_y:
                return [now_x, now_y]

            now_x, now_y = next_x, next_y


if __name__ == "__main__":
    sol = Solution()
    A = [[1, 3, 2], [4, 6, 5], [7, 9, 8], [13, 15, 14], [10, 12, 11]]
    # A = [[1,2,1,2,1,2,1],[2,17,13,6,5,17,2],[1,15,8,10,8,15,1],[2,14,12,11,12,14,2],[1,2,1,2,1,2,1]]
    idxes = sol.findPeakII(A)
    print(idxes)
