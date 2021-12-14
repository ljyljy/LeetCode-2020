# 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
#
#  答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
#
#  示例 1：
#
#  输入：n = 2
# 输出：2
#
#
#  示例 2：
#
#  输入：n = 7
# 输出：21
#
#
#  提示：
#
#
#  0 <= n <= 100
#
#
#  注意：本题与主站 70 题相同：https://leetcode-cn.com/problems/climbing-stairs/
#
#
#  Related Topics 递归
#  👍 51 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    """
    // Q70_Java解法：
    // 法3：完全背包+求方案数+排列(物品内循环)
    // 物品：台阶数(1,2,..m), 题中m=2; 背包总重: n级台阶；
    // 求:装满背包有几种方案？
    public int climbStairs_dp2(int n) {
        int bagsize = n, m = 2;
        int[] dp = new int[bagsize+1];
        dp[0] = 1;
        for (int j = 0; j <= bagsize; j++) {
            for (int i = 1; i <= m; i++) {
                if (j >= i)
                    dp[j] += dp[j-i];
            }
        }
        return dp[bagsize];
    }

    // 法1：DP迭代
    public int climbStairs_dp1(int n) {
        if (n <= 2) return n;
        int f1 = 1, f2 = 2, f3 = 3;
        for (int i = 3; i <= n; i++) {
            f3 = f1 + f2;
        // n: f(n-2)+f(n-1), 但不重复计算，且不开数组保留中间结果
            // 为了下一次循环
            f1 = f2;
            f2 = f3;// 倒着看
        }
        return f3;
    }

    // 法2：回溯
    Map<Integer, Integer> memo;
    public int climbStairs_dfs_memo(int n) {
        memo = new HashMap<>(){{put(1, 1); put(2,2);}};
        return dfs(n, memo);
    }

    private int dfs(int n, Map<Integer, Integer> memo) {
        int key = n;
        if (memo.containsKey(key)) return memo.get(key);

        int cnt =  dfs(n-1, memo) + dfs(n-2, memo);
        memo.put(key, cnt);
        return cnt;
    }


    """
    def numWays(self, n: int) -> int:
        if n <= 1: return 1
        f1, f2, f3 = 1, 2, 3
        for i in range(3, n + 1):
            f3 = f1 + f2
            f1 = f2
            f2 = f3
        return mod(f2, 1000000007)

# leetcode submit region end(Prohibit modification and deletion)
