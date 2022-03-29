package DP.P1_Optimize;

public class q746_min_cost_climbing_stairs {
    // ��1, ���q174
    public int minCostClimbingStairs1(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n+1];
        dp[0] = cost[0]; dp[1] = cost[1];
        for (int i = 2; i < n; i++) {
            dp[i] = Math.min(dp[i-1], dp[i-2]) + cost[i];
        }
        return Math.min(dp[n-1], dp[n-2]); // ���һ��̨��dp[n-1]�����޻���[min(dp[n-2]=104, dp[n-1]=6)]
    }

    // ��2-�ռ��Ż�-ȡ��dp[]
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int MOD = 2;
        int[] dp = new int[MOD];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < n; i++) {
            dp[i % MOD] = Math.min(dp[(i-1) % MOD], dp[(i-2) % MOD]) + cost[i];
        }
        dp[n % MOD] = Math.min(dp[(n-1) % MOD], dp[(n-2) % MOD]); // ���һ��̨��dp[n-1]�����޻���[min(dp[n-2]=104, dp[n-1]=6)]
        return dp[n % MOD];
    }


}
