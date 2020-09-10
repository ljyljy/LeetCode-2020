# 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
#
#  相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
#
#
#
#  例如，给定三角形：
#
#  [
#      [2],
#     [3,4],
#    [6,5,7],
#   [4,1,8,3]
# ]
#
#
#  自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
#
#
#
#  说明：
#
#  如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
#  Related Topics 数组 动态规划
#  👍 585 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    # 法3：DP1 + 状态压缩 -- 时间O(n^2), 空间O(n)
    def minimumTotal(self, triangle: List[List[int]]) -> int:
        row = len(triangle)
        if not row: return 0
        col = len(triangle[-1])  # 最后一行最长
        dp = [0 for _ in range(col + 1)]
        for i in range(row - 1, -1, -1):
            for j in range(i + 1):  # j ∈[0, i]
                dp[j] = min(dp[j], dp[j + 1]) + triangle[i][j]
        return dp[0]

    # 法2：DP0 -- O(n^2)
    def minimumTotal2(self, triangle: List[List[int]]) -> int:
        row = len(triangle)
        if not row: return 0
        col = len(triangle[-1])  # 最后一行最长
        dp = [[0] * (col + 1) for _ in range(row + 1)]
        # 【自下而上】推导
        for i in range(row - 1, -1, -1):
            # 对于三角形的每一行，【从右向左】计算
            for j in range(len(triangle[i]) - 1, -1, -1):
                dp[i][j] = min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle[i][j]
        # 最终结果就保存在第一行第一列中
        return dp[0][0]

# 法1：超时
# class Solution {
#     public int minimumTotal(List<List<Integer>> triangle) {
#         return  dfs(triangle, 0, 0);
#     }
#
#     private int dfs(List<List<Integer>> triangle, int i, int j) {
#         if (i == triangle.size()) {
#             return 0;
#         }
#         return Math.min(dfs(triangle, i + 1, j), dfs(triangle, i + 1, j + 1)) + triangle.get(i).get(j);
#     }
# }
#
# 作者：sweetiee
# 链接：https://leetcode-cn.com/problems/triangle/solution/di-gui-ji-yi-hua-dp-bi-xu-miao-dong-by-sweetiee/
# 来源：力扣（LeetCode）
# 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
# leetcode submit region end(Prohibit modification and deletion)
