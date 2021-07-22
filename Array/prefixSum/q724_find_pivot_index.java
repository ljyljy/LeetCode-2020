package Array.prefixSum;

public class q724_find_pivot_index {
    public int pivotIndex(int[] nums) {
        int n = nums.length;
        int[] sum = new int[n+1];
        for (int i = 1; i <= n; i++)
            sum[i] = sum[i-1] + nums[i-1];

        for (int i = 1; i <= n; i++) {
            int x = nums[i-1];
            int sum_L = sum[i-1]; // 不包括x，x以左的preSum
            int sum_R = sum[n] - sum[i];// 不包括x，x以右的preSum
            if (sum_L == sum_R)
                return i-1; // nums_idx = sum_idx(i) - 1
        }
        return -1;
    }
}
