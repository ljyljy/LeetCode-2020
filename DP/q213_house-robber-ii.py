# 	Q213.打家劫舍II
# 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
# 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
# 示例 1：
# 输入：[1,2,3,1]
# 输出：4
# 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
#      偷窃到的最高金额 = 1 + 3 = 4 。
# 示例 2：
# 输入：nums = [2,3,2]
# 输出：3
# 解释：你不能先偷窃1号房屋（金额=2），然后偷窃3号房屋（金额=2）, 因为他们是相邻的。


###
# https://mp.weixin.qq.com/s/z44hk0MW14_mAQd7988mfw
#
# 那就简单了啊，这三种情况，哪种的结果最大，就是最终答案呗！
# 不过，其实我们不需要比较三种情况，只要比较情况二和情况三就行了，因为这两种情况对于房子的选择余地比情况一大呀，房子里的钱数都是非负数，所以选择余地大，最优决策结果肯定不会小。
#
#
# 单调栈 —— 处理【循环数组】
# https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247484525&idx=1&sn=3d2e63694607fec72455a52d9b15d4e5&chksm=9bd7fa65aca073734df90b45054448e09c14e6e35ad7b778bff62f9bd6c2b4f6e1ca7bc4f844&scene=21#wechat_redirect

class Solution:
    def rob(self, nums):
        n = len(nums)
        if n == 1: return nums[0]
        # 处理【循环数组】 - 分裂
        return max(self.robRange(nums[0:n-1]),
                   self.robRange(nums[1:n]))

    # 仅计算闭区间[start, end]序列的最优结果
    def robRange(self, nums):
        n = len(nums)
        if n == 1: return nums[0]
        mod_n = 2  # 或3
        dp = [0] * mod_n
        dp[0], dp[1] = 0, nums[0]
        for i in range(2, n+1):
            dp[i % mod_n] = max(dp[(i-1) % mod_n],
                                dp[(i-2) % mod_n] + nums[i-1])
        return dp[n % mod_n]

