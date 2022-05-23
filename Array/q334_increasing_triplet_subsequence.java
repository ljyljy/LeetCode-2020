package Array;

import java.util.Arrays;

public class q334_increasing_triplet_subsequence {
    // 法1：贪心+一次遍历 - O(n)
    public boolean increasingTriplet1(int[] nums) {
        int n = nums.length;
        if(n < 3) return false;
        int first = nums[0], second = Integer.MAX_VALUE;

        for (int i = 1; i < n; i++) {
            if (nums[i] > second) {
                return true;
            } else if (nums[i] > first) {
                second = nums[i];
            } else { // nums[i] <= first
                first = nums[i];
            }
        }
        return false;
    }

    // 法2：贪心+双向遍历 - O(n) - 类似q42
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        if(n < 3) return false;
        int[] l_min = new int[n], r_max = new int[n];
        l_min[0] = nums[0]; r_max[n-1] = nums[n-1];

        for (int i = 1; i < n; i++) {
            l_min[i] = Math.min(l_min[i-1], nums[i]);
        }
        for (int i = n-2; i >= 0; i--) {
            r_max[i] = Math.max(r_max[i+1], nums[i]);
        }

        for (int i = 1; i <= n-2; i++) {
            if (l_min[i] < nums[i] && nums[i] < r_max[i]) {
                return true;
            }
        }
        return false;
    }

    // 法3：dp， 类比LIS(q300, q334) —— O(n^2) - TLE
    // todo：LIS + 二分 - https://leetcode.cn/problems/increasing-triplet-subsequence/solution/gong-shui-san-xie-zui-chang-shang-sheng-xa08h/
    public boolean increasingTriplet_DP_TLE(int[] nums) {
        int n = nums.length;
        if(n < 3) return false;
        int[] dp = new int[n]; // [0,i]间，小于nums[i]的个数（包括nums[i]）
        Arrays.fill(dp, 1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            if(dp[i] >= 3) return true;
        }
        return false;
    }
}
