package DP;

import java.util.Arrays;

public class q674_longest_continuous_increasing_subsequence {
    // ·¨1£ºdp
    public int findLengthOfLCIS_DP(int[] nums) {
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

    // ·¨2£ºÌ°ÐÄ
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
