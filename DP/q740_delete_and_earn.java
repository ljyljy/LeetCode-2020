package DP;

import java.util.Arrays;

public class q740_delete_and_earn {
    private int[] cnt = new int[10009]; // 10^4
    public int deleteAndEarn(int[] nums) {
        int n = nums.length;
        int max = Arrays.stream(nums).max().getAsInt(); // 0
        for (int num: nums) {
            cnt[num]++; // cnt[i]=数字i的频次(i/num升序)
            // max = Math.max(max, num);
        }
        int[][] dp = new int[max+1][2]; // dp[i][0/1]: 数字i获得的点数
        for (int i = 1; i <= max; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]); // 不选num, 则num-1随便选不选
            dp[i][1] = dp[i-1][0] + i*cnt[i]; // num若选，则
            // 1) num-1不可选(自动删除且不算点数)
            // 2) 再加上num的点数(∵求最大点数 ∴所有的num都选上，即乘以频数)
        }
        return Math.max(dp[max][0], dp[max][1]);
    }
}
