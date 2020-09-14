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

# leetcode submit region end(Prohibit modification and deletion)
