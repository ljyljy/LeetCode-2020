package DP.P1_Optimize;

import java.util.Arrays;

public class q152_maximum_product_subarray {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int[] dp_max = nums.clone(); // 初始化！或new int[n] & dp_max[0] = nums[0];
        int[] dp_min = nums.clone(); // 或new int[n] & dp_min[0] = nums[0];
        for (int i = 1; i < n; i++) {
            dp_max[i] = Math.max(nums[i],
                    Math.max(dp_max[i-1] * nums[i], dp_min[i-1] * nums[i]));
            dp_min[i] = Math.min(nums[i],
                    Math.min(dp_max[i-1] * nums[i], dp_min[i-1] * nums[i]));
        }
        return Arrays.stream(dp_max).max().getAsInt();
    }

    // 写法2
    public int maxProduct2(int[] nums) {
        int n = nums.length;
        int[] dp_max = new int[n];
        int[] dp_min = new int[n];
        dp_max[0] = nums[0];
        dp_min[0] = nums[0];
        int ans = nums[0];
        for (int i = 1; i < n; i++) {
            dp_max[i] = Math.max(dp_max[i-1] * nums[i],
                    Math.max(dp_min[i-1] * nums[i], nums[i]));
            dp_min[i] = Math.min(dp_max[i-1] * nums[i],
                    Math.min(dp_min[i-1] * nums[i], nums[i]));
            ans = Math.max(ans, dp_max[i]);
        }
        return ans;
    }

}
