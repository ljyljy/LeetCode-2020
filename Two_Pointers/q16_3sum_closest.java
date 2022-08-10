package Two_Pointers;

import java.util.Arrays;

public class q16_3sum_closest {
    public int threeSumClosest0(int[] nums, int target) {
        Arrays.sort(nums);
        int minGap = Integer.MAX_VALUE, minGapSum = 0;
        int n = nums.length;
        for (int i = 0; i < n-2; i++) {
            if (i-1>=0 && nums[i] == nums[i-1]) continue;
            int lf = i+1, rt = n-1;
            while (lf < rt) {
                int curSum = nums[i] + nums[lf] + nums[rt];
                int curGap = Math.abs(target - curSum);
                // 必须是“绝对值” (∵距离)
                if (curGap == 0) return curSum;
                if (minGap > curGap) {
                    minGap = curGap;
                    minGapSum = curSum;
                }

                if (curSum > target) rt--;
                else lf++;
            }
        }
        return minGapSum;
    }

    // 写法2
    public int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        int minGap = Integer.MAX_VALUE, minGap_sum = 0;
        for (int i = 0; i < n; i++) {
            if (i-1>=0 && nums[i] == nums[i-1]) continue;
            int lf = i+1, rt = n-1;
            while (lf < rt) {
                int sum = nums[i] + nums[lf] + nums[rt];
                int gap = Math.abs(target - sum);
                if (gap == 0) return sum;
                else if (gap < minGap) {
                    minGap = gap;
                    minGap_sum = sum;
                }

                if (sum < target) {
                    lf++;
                } else rt--;
            }
        }
        return minGap_sum;
    }
}
