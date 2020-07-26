# 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
#
#  机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
#
#  问总共有多少条不同的路径？
#
#
#
#  例如，上图是一个7 x 3 的网格。有多少可能的路径？
#
#
#
#  示例 1:
#
#  输入: m = 3, n = 2
# 输出: 3
# 解释:
# 从左上角开始，总共有 3 条路径可以到达右下角。
# 1. 向右 -> 向右 -> 向下
# 2. 向右 -> 向下 -> 向右
# 3. 向下 -> 向右 -> 向右
#
#
#  示例 2:
#
#  输入: m = 7, n = 3
# 输出: 28
#
#
#
#  提示：
#
#
#  1 <= m, n <= 100
#  题目数据保证答案小于等于 2 * 10 ^ 9
#
#  Related Topics 数组 动态规划
#  👍 619 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def uniquePaths(self, m: int, n: int) -> int:
        if m == 1 or n == 1: return 1
        # //dp[i][j]表示机器人从点（0,0）出发到点（i,j）的不同路径数目
        dp = [[0] * n for _ in range(m)]
        dp[0][0] = 1
        #   /*考虑边缘情况*/ -- n = 1的情况，只有一条直线路径（纵向）
        for i in range(m):
            dp[i][0] = 1
        #  // m = 1的情况，只有一条直线路径(横向)
        for j in range(n):
            dp[0][j] = 1
        # 动归
        for i in range(1, m):
            for j in range(1, n):
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1]  # //到达点（i,j）有两种路径：
                # //（1）机器人从点（i-1,j）往下移动 （2）机器人从点（i,j-1）往右移动
        return dp[-1][-1]

# leetcode submit region end(Prohibit modification and deletion)
