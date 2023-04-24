package Greedy;

import java.util.Arrays;

public class q53_maximum_subarray {
    // 类比：q53 -> 合唱队、q9_42

    // 2-贪心[荐!]
    public int maxSubArray2(int[] nums) {
        int n = nums.length;
        int maxSum = Integer.MIN_VALUE;
        int curSum = 0;
        for (int i = 0; i < n; i++) {
            curSum += nums[i];
            if (curSum > maxSum) maxSum = curSum;
            if (curSum < 0) curSum = 0;
        }
        return maxSum;
    }

    // 法1：普通dp
    public int maxSubArray_v1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0]; // 易错1
        int res = dp[0]; // 易错2❤
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }
    // 法1-2：空间压缩 - 空间O(1)
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int mod = 2;
        int[] dp = new int[mod];
        dp[0] = nums[0];
        int res = dp[0];
        for (int i = 1; i < n; i++) {
            dp[i % mod] = Math.max(dp[(i-1) % mod] + nums[i], nums[i]);
            res = Math.max(res, dp[i % mod]);
        }
        return res;
    }

    // 变题：返回最大子序和的起始和终止坐标？
    public int[] maxSubArrayIdx(int[] nums) {
        int res[] = new int[]{-1, -1}; // {start, end}
        if (nums == null || nums.length == 0) return res;
        int n = nums.length;
        int mod = 2;
        int[] dp = new int[mod];
        dp[0] = nums[0];
        int maxSum = dp[0], start = -1;
        for (int i = 1; i < n; i++) {
            if (dp[(i-1) % mod] > 0)
                dp[i % mod] = dp[(i-1) % mod] + nums[i];
            else { //当nums[i]自立门户时，记录新起始位置❤
                dp[i % mod] = nums[i];
                start = i;// ❤
            }
            if (maxSum < dp[i % mod]) {
                maxSum = dp[i % mod];
                res[0] = start; // ❤
                res[1] = i;//记录下起始和终止位置
            }
        }
        System.out.println("maxSum = " + maxSum);
        System.out.println("起止点idx：" + Arrays.toString(res));
        return res;
    }

    public static void main(String[] args) {
        q53_maximum_subarray sol = new q53_maximum_subarray();
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        sol.maxSubArrayIdx(nums);
    }
}
