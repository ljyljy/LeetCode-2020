package Greedy;

import java.util.Arrays;

public class q55_jump_game {
    // 法1：贪心
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int farthest = nums[0];
        for (int i = 0; i < n; i++) {
            if (i <= farthest) { // 新大前提：必须可达！
                farthest = Math.max(farthest, i + nums[i]);
                if (farthest >= n-1) {
                    return true;
                }
            }
        }
        return false;
    }

    // 法2：dp[i] -- i处的最远跳跃点，若>=n-1, 则true
    public boolean canJump22(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MIN_VALUE);
        int farthest = nums[0];
        for (int i = 0; i < n; i++) {
            if (i <= farthest) {
                dp[i] = Math.max(dp[i], i + nums[i]);
                farthest = Math.max(farthest, dp[i]);
                if (farthest >= n-1) return true;
            }
        }
        return false;
    }

    // 法2-2：动规
    public boolean canJump2(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int farthest = 0;
        Arrays.fill(dp, Integer.MIN_VALUE);
        for (int i = 0; i < n; i++) {
            if (i <= farthest) {
                dp[i] = Math.max(dp[i],  i + nums[i]);
                if (dp[i] >= n-1) return true;
            } else return false;
            farthest = Math.max(farthest, dp[i]);
        }
        return dp[n-1] >= n-1;
    }

    // 法3
    public boolean canJump3(int[] nums) {
        int n = nums.length;
        int farthest = nums[0];
        int[] dp = new int[n]; // 最远距离
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = nums[0];
        for (int i = 0; i < n; i++) {
            if (i <= farthest) {
                for (int j = 0; j < i; j++) {
                    if (j + nums[j] >= i) {
                        dp[i] = Math.max(dp[i], Math.max(i + nums[i], j + nums[j]));
                        if (dp[i] >= n-1) return true;
                    }
                }
                farthest = Math.max(farthest, dp[i]);
            }
        }
//        System.out.println(Arrays.toString(dp));
        return farthest >= n-1;
    }
}
