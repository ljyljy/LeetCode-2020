package DP.G1_BagPack.q_01BagPack;

import java.util.Arrays;

public class q416_partition_equal_subset_sum {
    // 法2:01背包（滚动数组）
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) return false;
        int[] dp = new int[sum/2+1]; // dp[价值]
        // ∵价值/nums[i]>0 ∴初始化dp[0]=0（略）
        for (int i = 0; i < n; i++) {
            for (int j = sum/2; j >= 0; j--) { // j递减：避免nums[i]重复放入背包!
                if(j - nums[i] >= 0)         //   ↓体积     ↓价值
                    dp[j] = Math.max(dp[j], dp[j-nums[i]]+nums[i]);
            }
        }
        return dp[sum/2] == sum/2;
    }
    // 法1:01背包（二维dp）
    public boolean canPartition1(int[] nums) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) return false; // 俩子集和均为sum/2
        // 转化01背包: 选/不选, s.t.最终背包最大价值==sum/2(体积/价值),
        int[][] dp = new int[n+1][sum/2+1]; // 初始化: dp[0][:]=0, dp[:][0]=0
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum/2; j++) {
                dp[i][j] = dp[i-1][j]; // 1)不选nums[i-1]
                if (j - nums[i-1] >= 0) // 2) 选
                    dp[i][j] = Math.max(dp[i-1][j],
                            dp[i-1][j-nums[i-1]] + nums[i-1]);
            }
        }
        return dp[n][sum/2] == sum / 2;
    }
}
