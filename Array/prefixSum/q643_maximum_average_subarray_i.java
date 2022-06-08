package Array.prefixSum;

public class q643_maximum_average_subarray_i {
    // 法1：前缀和 O(n)
    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;
        int[] preSum = new int[n+1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i-1] + nums[i-1];
        }

        int maxSum = Integer.MIN_VALUE;
        for (int i = k; i <= n; i++) {
            int tmpSum = preSum[i] - preSum[i-k];
            maxSum = Math.max(maxSum, tmpSum);
        }
        return (double)maxSum / (double)k;
    }

    // 法2：滑动窗口（todo）
}
