package Two_Pointers;

import java.util.Arrays;

public class q16_3sum_closest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int minGap = Integer.MAX_VALUE, minGapSum = 0;;
        int n = nums.length;
        for (int i = 0; i < n-2; i++) {
            if (i-1>=0 && nums[i] == nums[i-1]) continue;
            int lf = i+1, rt = n-1;
            while (lf < rt) {
                int curSum = nums[i] + nums[lf] + nums[rt];
                int curGap = target - curSum;
                // 必须是“绝对值” (∵距离)
                if (Math.abs(minGap) > Math.abs(curGap)) {
                    minGap = curGap;
                    minGapSum = curSum;
                }
                if (curGap == 0) {
                    return curSum;
                } else if (curSum > target) rt--;
                else lf++;
            }
        }
        return minGapSum;
    }
}
