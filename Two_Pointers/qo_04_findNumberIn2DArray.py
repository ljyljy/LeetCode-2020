# 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，
# 判断数组中是否含有该整数。
#
#
#
#  示例:
#
#  现有矩阵 matrix 如下：
#
#  [
#   [1,   4,  7, 11, 15],
#   [2,   5,  8, 12, 19],
#   [3,   6,  9, 16, 22],
#   [10, 13, 14, 17, 24],
#   [18, 21, 23, 26, 30]
# ]
#
#
#  给定 target = 5，返回 true。
#
#  给定 target = 20，返回 false。
#
#
#
#  限制：
#
#  0 <= n <= 1000
#
#  0 <= m <= 1000
#
#
#
#  注意：本题与主站 240 题相同：https://leetcode-cn.com/problems/search-a-2d-matrix-ii/
#  Related Topics 数组 双指针


# leetcode submit region begin(Prohibit modification and deletion)
# 总结上述查找的过程，我们发现如下规律: 首先选取数组中右上角的数字。
# 如果该数字等于要查找的数字，则查找过程结束;
# 如果该数字大于要查找的数字，则剔除这个数字所在的列:
# 如果该数字小于要查找的数字，则剔除这个数字所在的行。
# 也就是说，如果要查找的数字不在数组的右上角，则每一次都在数组的查找范围中剔除一行或者一列，
# 这样每一步都可以缩小查找的范围，直到找到要查找的数字，或者查找范围为空。
from typing import List


class Solution:
    def findNumberIn2DArray(self, matrix: List[List[int]], target: int) -> bool:
        if not matrix: return False
        row, col = len(matrix), len(matrix[0])
        i, j = 0, col - 1
        while j >= 0 and i < row:
            if matrix[i][j] > target:
                j -= 1
            elif matrix[i][j] < target:
                i += 1
            else:
                return True
        return False
# leetcode submit region end(Prohibit modification and deletion)
