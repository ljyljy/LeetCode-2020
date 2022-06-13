package Binary_Search.bin_Answer;

import java.util.Arrays;

public class q644_maximum_average_subarray_ii {
    private int[] nums;
    private int k, n;
    public double findMaxAverage(int[] nums, int k) {
        this.n = nums.length; this.nums = nums; this.k = k;
        double eps = 1e-5;
        double start = Arrays.stream(nums).min().getAsInt();
        double end = Arrays.stream(nums).max().getAsInt();
        while (start + eps < end) { // eps & [L, mid (-eps)] & [mid, R]
            double mid = start + (end - start) / 2.0; // 不可>>1 (整除×)！
            // 确保mid & R在一个区间
            if (check(mid)) start = mid; // mid可以增大（右区间）
            else end = mid;
        }
        return start;
    }

    private boolean check (double tryAvg) {
        double[] preSum = new double[n+1];
        for (int i = 1; i <= n; i++)
            preSum[i] = preSum[i-1] + nums[i-1] - tryAvg;

//        double min_preSum_k = 0;
//        for (int i = k; i <= n; i++) {
//            if (preSum[i] - min_preSum_k >= 0)
//                return true;
//            min_preSum_k = Math.min(min_preSum_k, preSum[i - k + 1]);
//        }
        double min_preSum_k = 0;
        // 写法1
        for (int i = k; i <= n; i++) { // 窗口大小=[prev+1, i] ∈ [k, n]
            min_preSum_k = Math.min(min_preSum_k, preSum[i-k]);
            // 如：k = 4, min_preSum_k = preSum[4|5|6]-preSum[0|1|2]
            if (preSum[i] - min_preSum_k >= 0) return true;
        }
        // 写法2
//        for (int i = k; i <= n; i++) {
//            if (preSum[i] - min_preSum_k >= 0) return true;
//            // ↑ WHY not preSum[i+1]? -- 此处是下一轮，i已++
//            min_preSum_k = Math.min(min_preSum_k, preSum[i - k + 1])
//        }
        return false;
    }
}
