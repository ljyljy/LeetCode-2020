package Greedy;

import java.util.Arrays;

public class q1005_maximize_sum_of_array_after_k_negations {
    public int largestSumAfterKNegations(int[] nums, int k) {
        int n = nums.length;
        int sum = 0;
        Arrays.sort(nums);
        // 遍历排序后的数组，有负值且还有转换次数就转正
        for (int i = 0; i < n; i++) {
            if (nums[i] < 0 && k > 0) {
                nums[i] = -1 * nums[i];
                k--;
            }
            sum += nums[i];
        }
        // 再排序有三种情况 1. 转换次数已经用完 此时直接返回即可
        //               2.转换次数没用完 还剩偶数次，此时没有负数了，直接返回即可
        //          3.转换次数没用完 还剩偶数次，此时没有负数了，返回sum-2*最小数
        if (k <= 0 || k % 2 == 0) {
            return sum;
        } else { // 此时nums存在负数转为正数，需要再次升序！
            Arrays.sort(nums); // 【勿忘再sort一次】
            return sum - 2*nums[0];
        }
    }

}
