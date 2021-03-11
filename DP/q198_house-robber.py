# q198. 打家劫舍I
# 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上
# 被小偷闯入，系统会自动报警。
#
#  给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
#
#
#
#  示例 1：
#
#  输入：[1,2,3,1]
# 输出：4
# 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
#      偷窃到的最高金额 = 1 + 3 = 4 。
#
#  示例 2：
#
#  输入：[2,7,9,3,1]
# 输出：12
# 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
#      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
#
#
#
#
#  提示：
#
#
#  0 <= nums.length <= 100
#  0 <= nums[i] <= 400
#
#  Related Topics 动态规划
#  👍 1067 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
from typing import List

# // 主函数
# public int rob(int[] nums) {
#     return dp(nums, 0);
# }
# // 返回 nums[start..] 能抢到的最大值
# private int dp(int[] nums, int start) {
#     if (start >= nums.length) {
#         return 0;
#     }
#
#     int res = Math.max(
#             // 不抢，去下家
#             dp(nums, start + 1),
#             // 抢，去下下家
#             nums[start] + dp(nums, start + 2)
#         );
#     return res;
# }

class Solution:
    # 法1：二维dp[当前偷取了i个房屋][偷/不偷] 情况下的最大rob值
    def rob1(self, nums: List[int]) -> int:
        if not nums: return 0
        n = len(nums)
        if n == 1: return nums[0]
        dp = [[0] * 2 for _ in range(n)]  # (n, 2)-- dp[i][0/1] -- 0:i偷，1:i不偷
        dp[0][0], dp[0][1] = 0, nums[0]
        for i in range(1, n):
            dp[i][0] = max(dp[i - 1][1], dp[i - 1][0])  # i不偷 -> i-1可偷可不偷
            dp[i][1] = dp[i - 1][0] + nums[i]  # i偷 -> i-1必不可偷(在i-1不偷时的最大rob值+nums[i])
        return max(dp[-1][0], dp[-1][1])

    # 法1-2：备忘录memo + 递归（自顶向下）
    def rob1_2(self, nums: List[int]) -> int:
        if not nums: return 0
        n = len(nums)

        def dp(nums, start: int):
            if start >= n: return 0
            # 避免重复计算
            if memo[start] != -1: return memo[start]
            res = max(dp(nums, start + 1),
                      dp(nums, start + 2) + nums[start])
            memo[start] = res
            return res

        memo = [-1] * n
        return dp(nums, 0)


    # 法2：一维dp[当前偷取了i个房屋] 情况下的最大rob值
    # 状态压缩 —— 只记录【偷or不偷的较大者】
    def rob2(self, nums: List[int]) -> int:
        if not nums: return 0
        n = len(nums)
        if n == 1: return nums[0]
        dp = [0] * n
        dp[0], dp[1] = nums[0], max(nums[0], nums[1])
        for i in range(2, n):
            dp[i] = max(dp[i - 1], dp[i - 2] + nums[i])  # i不偷(i-1的最大rob / i-1不偷，偷i-2 & i)
        return dp[-1]



    # 法3：一个数[当前偷取了i个房屋] 情况下的最大rob值
    # 状态压缩 —— 只记录【偷or不偷的较大者】
    # 空间复杂度：O(1)。使用滚动数组，可以只存储前两间房屋的最高总金额，而不需要存储整个数组的结果，因此空间复杂度是 O(1)
    def rob(self, nums: List[int]) -> int:
        if not nums: return 0
        n = len(nums)
        if n == 1: return nums[0]
        dp1, dp2 = nums[0], max(nums[0], nums[1])
        for i in range(2, n):
            dp1, dp2 = dp2, max(dp2, dp1 + nums[i])
        return dp2

    def rob3_2(self, nums):
        if not nums: return 0
        n = len(nums)
        mod_n = 3  # 2也行（即上一解法的 dp1，dp2） # 状态压缩 -- 优化dp[]
        dp = [0] * mod_n
        dp[0], dp[1] = 0, nums[0]  # 勿忘初始化dp[1]！
        for i in range(2, n+1):
            # dp[i] = max(dp[i-1], dp[i-2] + nums[i-1])  # nums[i-1]: WHY i-1？-- 为了与dp下标i同步，需要减1
            dp[i % mod_n] = max(dp[(i - 1) % mod_n], dp[(i - 2) % mod_n] + nums[i-1])
        return dp[n % mod_n]
# leetcode submit region end(Prohibit modification and deletion)
