package DP;

import java.util.Arrays;

public class q674_longest_continuous_increasing_subsequence {
    // 法1：dp
    public int findLengthOfLCIS_DPv1(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int LCIS = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i-1])
                dp[i] = Math.max(dp[i-1], dp[i]) + 1;
            if (LCIS < dp[i]) LCIS = dp[i];
        }
        return LCIS;
    }

    // 法1-2
    public int findLengthOfLCIS_DPv2(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int LCIS = 1;
        for (int i = 0; i+1 < n; i++) {
            if (nums[i+1] > nums[i])
                dp[i+1] = Math.max(dp[i+1], dp[i] + 1);
            if (LCIS < dp[i+1]) LCIS = dp[i+1];
        }
        return LCIS;
    }

    // 法2：贪心
    public int findLengthOfLCIS(int[] nums) {
        int n = nums.length;
        int LCIS = 1, cnt = 1;
        for (int i = 0; i+1 < n; i++) {
            if (nums[i+1] > nums[i]) cnt++;
            else cnt = 1;
            if (LCIS < cnt) LCIS = cnt;
        }
        return LCIS;
    }
}
