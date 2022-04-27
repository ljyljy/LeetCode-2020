package DP.P1_Optimize;

import java.util.Arrays;

public class q152_maximum_product_subarray {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int[] dp_max = nums.clone(); // ≥ı ºªØ£°
        int[] dp_min = nums.clone();
        for (int i = 1; i < n; i++) {
            dp_max[i] = Math.max(nums[i],
                    Math.max(dp_max[i-1] * nums[i], dp_min[i-1] * nums[i]));
            dp_min[i] = Math.min(nums[i],
                    Math.min(dp_max[i-1] * nums[i], dp_min[i-1] * nums[i]));
        }
        return Arrays.stream(dp_max).max().getAsInt();
    }

}
