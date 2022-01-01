package DP;

import java.util.Arrays;

public class q213_house_robber_ii {
    // 写法1
    private int MOD = 2;
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);
        int res1 = robRange(nums, 0, n-2);
        int res2 = robRange(nums, 1, n-1);
        return Math.max(res1, res2);
    }

    private int robRange(int[] nums, int start, int end) {
        int n = end - start + 1;
        int[] dp = new int[MOD];
        dp[0] = nums[start];
        dp[1] = Math.max(nums[start], nums[start+1]);
        for (int i = 2; i < n; i++) {
            dp[i % MOD] = Math.max(dp[(i-1) % MOD], dp[(i-2) % MOD] + nums[i+start]);
        }
        return dp[(n-1) % MOD];
    }

    // 写法2
    // 注意：1) System.arraycopy(..) 2) Arrays.fill(..)
    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        if (n == 1) return nums[0];
        // ❤ System.arraycopy(原数组名称，原数组起始下标，目标数组名称，目标数组起始下标，截取长度）
        int[] nums1 = new int[n-1], nums2 = new int[n-1];
        System.arraycopy(nums, 0, nums1, 0, n-1); // nums[0:n-2]
        System.arraycopy(nums, 1, nums2, 0, n-1); // nums[1:n-1]
        return Math.max(robRange(nums1), robRange(nums2)); // 处理【循环数组】 - 分裂
    }

    // 仅计算闭区间[start, end]序列的最优结果
    private int robRange(int[] nums) {
        int n = nums.length;
        int mod_n = 2; // 或3
        if (n == 1) return nums[0];
        int[] dp = new int[mod_n];
        Arrays.fill(dp, 0); // mod_n个0
        dp[0] = 0; dp[1] = nums[0];
        for (int i = 2; i <= n; i++) {
            dp[i % mod_n] = Math.max(dp[(i-2) % mod_n] + nums[i-1],
                    dp[(i-1) % mod_n]);
        }
        return dp[n % mod_n];
    }
}
