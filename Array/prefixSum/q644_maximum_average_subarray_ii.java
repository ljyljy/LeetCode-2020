package Array.prefixSum;

import java.util.Arrays;

public class q644_maximum_average_subarray_ii {
    private int[] nums;
    private int k, n;
    public double findMaxAverage(int[] nums, int k) {
        this.n = nums.length; this.nums = nums; this.k = k;
        double eps = 1e-5;
        double left = Arrays.stream(nums).min().getAsInt();
        double right = Arrays.stream(nums).max().getAsInt();
        while (left + eps < right) {
            double mid = (double)left + (right - left) / 2.0;
            if (check(mid)) // mid可以增大（右区间）
                left = mid;
            else right = mid;
        }
        return left;
    }

    private boolean check (double tryAvg) {
        double[] prefix_sum = new double[n+1];
        for (int i = 1; i <= n; i++)
            prefix_sum[i] = prefix_sum[i-1] + nums[i-1] - tryAvg;

        double min_preSum_k = 0;
        for (int i = k; i <= n; i++) {
            if (prefix_sum[i] - min_preSum_k >= 0)
                return true; // WHY not p[i+1]? -- 此处是下一轮，i已++
            min_preSum_k = Math.min(min_preSum_k, prefix_sum[i - k + 1]);
        }
        return false;
    }
}
