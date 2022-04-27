package DP;

import java.util.Arrays;

public class q198_house_robber {
    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        if (n > 1) dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2]+nums[i]);
        }
        return dp[n-1];
    }

    // 空间优化
    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        return robHelper(nums);
    }

    private int robHelper(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        int mod_n = 2; // 或3
        int[] dp = new int[mod_n];
        Arrays.fill(dp, 0); // dp = [0] * mod_n
        dp[0] = 0; dp[1] = nums[0];
        for (int i = 2; i <= n; i++) {
            dp[i % mod_n] = Math.max(dp[(i-1) % mod_n],
                                     dp[(i-2) % mod_n] + nums[i-1]);
        }
        return dp[n % mod_n];
    }
}
