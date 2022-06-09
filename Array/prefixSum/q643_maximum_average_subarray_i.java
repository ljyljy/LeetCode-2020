package Array.prefixSum;

public class q643_maximum_average_subarray_i {
    // 法1：前缀和 O(n)
    public double findMaxAverage1(int[] nums, int k) {
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

    // 法2：滑动窗口 - O(n)
    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;
        int left = 0, right = 0;
        double maxSum = Integer.MIN_VALUE, curSum = 0;

        while (right < n) {
            int num = nums[right++];
            curSum += num;

            while (right - left == k) {
                maxSum = Math.max(maxSum, curSum);
                curSum -= nums[left++];
            }
        }
        
        return maxSum / (double) k;
    }
}
