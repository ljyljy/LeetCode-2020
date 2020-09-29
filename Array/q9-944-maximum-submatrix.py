# 给定一个正整数、负整数和 0 组成的 N × M 矩阵，编写代码找出元素总和最大的子矩阵。
#
#  返回一个数组 [r1, c1, r2, c2]，其中 r1, c1 分别代表子矩阵左上角的行号和列号，r2, c2 分别代表右下角的行号和列号。若有多个满
# 足条件的子矩阵，返回任意一个均可。
#
#  注意：本题相对书上原题稍作改动
#
#  示例：
#
#  输入：
# [
#    [-1,0],
#    [0,-1]
# ]
# 输出：[0,1,0,1]
# 解释：输入中标粗的元素即为输出所表示的矩阵
#
#
#
#  说明：
#
#
#  1 <= matrix.length, matrix[0].length <= 200
#
#  Related Topics 动态规划
#  👍 30 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List


class Solution:
    # V2: 返回([左上角], [右下角])
    BEST_i1k1_j2k2 = [0 for _ in range(4)]  # ([左上角], [右下角])

    def getMaxMatrix(self, matrix):
        if not matrix or not len(matrix) or not len(matrix[0]): return 0
        nrow, ncol, max_sum = len(matrix), len(matrix[0]), -sys.maxsize
        best_i1, best_k1 = 0, 0
        # 枚举子矩阵的上下边界 up & down, 然后将这之间的数压缩为一个一维数组（降维攻击），剩下的任务就是一维数组如何求 Maximum Subarray 了。
        for i in range(nrow):  # 上边界
            sum = [0 for _ in range(ncol)]  # 记录当前i~j行组成大矩阵的每一列的和，将二维转化为一维
            for j in range(i, nrow):  # 下边界
                dp = 0
                for k in range(ncol):
                    sum[k] += matrix[j][k]
                    if dp > 0:
                        dp += sum[k]
                    else:
                        dp = sum[k]
                        best_i1 = i  # 自立门户，暂时保存其左上角
                        best_k1 = k
                    if dp > max_sum:
                        max_sum = max(max_sum, dp)
                        self.BEST_i1k1_j2k2[0] = best_i1
                        self.BEST_i1k1_j2k2[1] = best_k1
                        self.BEST_i1k1_j2k2[2] = j
                        self.BEST_i1k1_j2k2[3] = k
        return self.BEST_i1k1_j2k2

    # V1: 返回 最大子矩阵的和
    def getMaxMatrix_v1(self, matrix: List[List[int]]) -> List[int]:
        nrow, ncol, rst = len(matrix), len(matrix[0]), 0
        if not matrix or not nrow or not ncol: return rst
        for i in range(nrow):
            sum = [0 for _ in range(ncol)]
            for j in range(i, nrow):
                for k in range(ncol):
                    sum[k] += matrix[j][k]
                tmp = self.maxSubarray(sum)
                rst = max(rst, tmp)
        return rst

    def maxSubarray(self, arr):
        rst, sum = 0, 0
        for i in range(len(arr)):
            sum += arr[i]
            rst = max(rst, sum)
            sum = max(sum, 0)
        return rst
# leetcode submit region end(Prohibit modification and deletion)
