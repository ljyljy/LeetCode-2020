package Two_Pointers;

import java.util.Arrays;

public class q1877_min_max_pair_sum_in_array {
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] sum = new int[n/2];
        // int lf = 0, rt = n-1;
        for (int lf = 0, rt = n-1; lf < rt; lf++, rt--) {
            sum[lf] = nums[lf] + nums[rt];
        }
        int maxSum = Arrays.stream(sum).max().getAsInt();
        return maxSum;
    }
}
